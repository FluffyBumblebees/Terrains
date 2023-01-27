package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

public interface RegistrySetCreator {
    void registryEvent(final SetRegistry registry);

    default void generationEvent() {}
}
