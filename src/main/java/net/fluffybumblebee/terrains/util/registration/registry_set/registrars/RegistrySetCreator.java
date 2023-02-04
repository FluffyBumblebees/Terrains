package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import net.fluffybumblebee.terrains.client.render.RenderTypes;

import java.util.List;

public interface RegistrySetCreator {
    void registryEvent(final SetRegistry registry);

    List<RenderTypes> getRenderType();

    default void generationEvent() {}
}
