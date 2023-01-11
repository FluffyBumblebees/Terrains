package net.fluffybumblebee.terrains.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.terrains.common.instances.block.CorundumCluster;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.CRYSTAL_GEODES;

public class RendererTranslucents {
    public static void registerTranslucents() {
        BlockRenderLayerMap.INSTANCE.putItem(MFRegistry.MAPLE_SAP, RenderLayer.getTranslucent());
        CRYSTAL_GEODES.forEach(element -> {
            Block block = element.block;
            if (!(block instanceof CorundumCluster)) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
            }
        });
    }
}

