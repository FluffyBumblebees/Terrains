package net.stockieslad.terrains.client.render;


import static net.stockieslad.terrains.common.registry.sets.RegistrySets.ALL_SETS;
import static net.stockieslad.terrains.util.registration.render.BlockRenderLayerMapUtil.standardCutouts;
import static net.stockieslad.terrains.util.registration.render.BlockRenderLayerMapUtil.standardTranslucents;

public class RegisterRenderLayers {
    public static void registerCutouts() {
        standardCutouts(ALL_SETS);
    }

    public static void registerTranslucents() {
        standardTranslucents(ALL_SETS);
    }

}
