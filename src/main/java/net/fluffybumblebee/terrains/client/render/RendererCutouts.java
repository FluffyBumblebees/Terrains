package net.fluffybumblebee.terrains.client.render;


import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.util.registration.render.BlockRenderLayerMapUtil.standardCutouts;

public class RendererCutouts {
    public static void registerCutouts() {
        standardCutouts(
                FULL_TREES,
                STAINED_TREES,
                FOLIAGE,
                CRYSTAL_GEODES
        );
    }

}
