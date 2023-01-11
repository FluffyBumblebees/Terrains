package net.fluffybumblebee.terrains.client.render;


import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.terrains.common.instances.block.CorundumCluster;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.STAINED_TREES;

public class RendererCutouts {
    public static void registerCutouts() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MFRegistry.MAPLE_TRAPDOOR,
                MFRegistry.RED_MAPLE_SAPLING,
                MFRegistry.GREEN_MAPLE_SAPLING,
                MFRegistry.YELLOW_MAPLE_SAPLING,
                MFRegistry.ORANGE_MAPLE_SAPLING,
                MFRegistry.BROWN_MAPLE_SAPLING,
                MFRegistry.POTTED_RED_MAPLE_SAPLING,
                MFRegistry.POTTED_GREEN_MAPLE_SAPLING,
                MFRegistry.POTTED_YELLOW_MAPLE_SAPLING,
                MFRegistry.POTTED_ORANGE_MAPLE_SAPLING,
                MFRegistry.POTTED_BROWN_MAPLE_SAPLING
        );
        for (BlockBuilder<?> builder : CRYSTAL_GEODES.getAllRegistryEntries()) {
            Block block = builder.getBlock();
            if (block instanceof CorundumCluster) {
                putBlock(block);
            }
        }

        for (BlockBuilder<?> builder : STAINED_TREES.getAllRegistryEntries()) {
            Block block = builder.getBlock();
            putBlock(block);
        }
    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }
}
