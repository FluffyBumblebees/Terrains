package net.fluffybumblebee.terrains.client.render;


import static net.fluffybumblebee.terrains.common.registry.sets.RegistrySetManager.ALL_SETS;
import static net.fluffybumblebee.terrains.util.registration.render.BlockRenderLayerMapUtil.standardCutouts;
import static net.fluffybumblebee.terrains.util.registration.render.BlockRenderLayerMapUtil.standardTranslucents;

public class RegisterRenderLayers {
    public static void registerCutouts() {
        standardCutouts(ALL_SETS);
    }

    public static void registerTranslucents() {
        standardTranslucents(ALL_SETS);
    }

}
