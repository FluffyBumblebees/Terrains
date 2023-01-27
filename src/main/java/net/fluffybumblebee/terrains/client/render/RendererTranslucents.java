package net.fluffybumblebee.terrains.client.render;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.ITEMS;
import static net.fluffybumblebee.terrains.util.registration.render.BlockRenderLayerMapUtil.standardTranslucents;

public class RendererTranslucents {
    public static void registerTranslucents() {
        standardTranslucents(
                CRYSTAL_GEODES,
                ITEMS
        );
    }
}

