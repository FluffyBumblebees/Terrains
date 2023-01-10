package net.fluffybumblebee.terrains.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.terrains.common.default_abstract.block.CorundumCluster;
import net.fluffybumblebee.terrains.common.registry.category.CrystalGeodes;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class RendererTranslucents {
    public static void registerTranslucents() {
        BlockRenderLayerMap.INSTANCE.putItem(MFRegistry.MAPLE_SAP, RenderLayer.getTranslucent());
        for (BlockBuilder<?> builder : CrystalGeodes.getAllRegistryEntries()) {
            Block block = builder.getBlock();
            if (!(block instanceof CorundumCluster)) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
            }
        }
    }
}

