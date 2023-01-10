package net.fluffybumblebee.terrains.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.minecraft.client.render.RenderLayer;

public class RendererTranslucents {
    public static void registerTranslucents() {
        BlockRenderLayerMap.INSTANCE.putItems(RenderLayer.getTranslucent(),
                MFRegistry.MAPLE_SAP
        );
    }
}

