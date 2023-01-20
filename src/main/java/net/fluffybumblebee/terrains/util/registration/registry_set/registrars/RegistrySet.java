package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry.Storage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RegistrySet<Types, RegistryConfig extends RegistrySetCreator> {
    private final SetRegistry registry;
    private final Quickerator<Types> typesQuickerator;
    private final Map<Types, RegistryConfig> typeMap;


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
        typeMap = new HashMap<>();
        registry = new SetRegistry(registryTypes);
        typesQuickerator = () -> values;
        typesQuickerator.forEach(element -> {
            RegistryConfig set = factory.getNewSet(element);
            set.generationEvent();
            set.registryEvent(registry);
            typeMap.put(element, set);
        });
    }

    public Map<Types, RegistryConfig> getTypeMap() {
        return typeMap;
    }

    @SuppressWarnings("unused")
    public Quickerator<Types> getTypesQuickerator() {
        return typesQuickerator;
    }

    public SetRegistry getRegistry() {
        return registry;
    }

    public Quickerator<Storage> iterateRegistry(RegistryTypes types) {
        return () -> registry.storage.get(types);
    }

    public interface RegistrySetFactory<Types, RegistryConfig extends RegistrySetCreator> {
        RegistryConfig getNewSet(Types type);
    }
}
