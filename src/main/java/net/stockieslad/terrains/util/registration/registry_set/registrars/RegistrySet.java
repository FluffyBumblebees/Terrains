package net.stockieslad.terrains.util.registration.registry_set.registrars;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.util.registration.mass.SafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.helper.Quickerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RegistrySet<Types, RegistryConfig extends RegistrySetCreator> {
    private final SetRegistry registry;
    private final Quickerator<Types> typesQuickerator;
    private final Object2ObjectMap<Types, RegistryConfig> typeMap;
    private final List<RegistryTypes> registryTypes;
    private List<RenderTypes> renderTypes;
    private Quickerator<RegistryConfig> configQuickerator;

    public RegistrySet(
            final Types[] values,
            final List<RegistryTypes> registryTypes,
            final RegistrySetFactory<Types, RegistryConfig> factory) {
        this(Arrays.asList(values), registryTypes, factory);
    }

    public RegistrySet(
            final List<Types> values,
            final List<RegistryTypes> registryTypes,
            final RegistrySetFactory<Types, RegistryConfig> factory
    ) {
        typeMap = new Object2ObjectLinkedOpenHashMap<>();
        registry = new SetRegistry(registryTypes);
        this.registryTypes = registryTypes;
        typesQuickerator = () -> values;
        typesQuickerator.forEach(element -> {
            RegistryConfig set = factory.getNewSet(element);
            set.generate();
            set.register(registry);
            renderTypes = set.getRenderTypes();
            typeMap.put(element, set);
        });
    }

    public Object2ObjectMap<Types, RegistryConfig> getTypeMap() {
        return typeMap;
    }

    public Quickerator<Types> getTypesQuickerator() {
        return typesQuickerator;
    }

    @SuppressWarnings("unused")
    public List<RegistryTypes> getRegistryTypes() {
        return registryTypes;
    }

    @SuppressWarnings("unused")
    public List<RenderTypes> getRenderType() {
        return renderTypes;
    }

    public Quickerator<RenderTypes> iterateRenderType() {
        return () -> renderTypes;
    }

    public Quickerator<RegistryConfig> getConfigQuickerator() {
        if (configQuickerator == null) {
            final List<RegistryConfig> list = new ArrayList<>();
            getTypesQuickerator().forEach(element -> list.add(typeMap.get(element)));
            configQuickerator = () -> list;
        }
        return configQuickerator;
    }

    public SetRegistry getRegistry() {
        return registry;
    }

    public Quickerator<SafeTriSet> iterateRegistry(final RegistryTypes types) {
        return Quickerator.of(registry.storage.get(types) != null, registry.storage.get(types));
    }
    public void iterateRegistry(final OnEachRegistryType type) {
        Quickerator.of(registryTypes).forEach(element -> type.enMass(iterateRegistry(element)));
    }

    public interface RegistrySetFactory<Types, RegistryConfig extends RegistrySetCreator> {
        RegistryConfig getNewSet(final Types type);
    }

    public interface OnEachRegistryType {
        void enMass(final Quickerator<SafeTriSet> element);
    }
}
