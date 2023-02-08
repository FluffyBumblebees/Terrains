package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.fluffybumblebee.terrains.client.render.RenderTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry.Storage;

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
            set.generationEvent();
            set.registryEvent(registry);
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

    public Quickerator<Storage> iterateRegistry(final RegistryTypes types) {
        if (registry.storage.get(types) != null)
            return () -> registry.storage.get(types);
        else return List::of;
    }
    public void iterateRegistry(OnEachRegistryType type) {
        for (RegistryTypes regType : registryTypes)
            type.enMass(iterateRegistry(regType));
    }

    public interface RegistrySetFactory<Types, RegistryConfig extends RegistrySetCreator> {
        RegistryConfig getNewSet(final Types type);
    }

    public interface OnEachRegistryType {
        void enMass(Quickerator<Storage> element);
    }
}
