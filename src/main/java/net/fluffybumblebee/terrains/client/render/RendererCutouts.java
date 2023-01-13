package net.fluffybumblebee.terrains.client.render;


import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.terrains.common.instances.block.crystals.CorundumCluster;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.*;
import static net.fluffybumblebee.terrains.util.registration.feature_set.EasyIf.onIf;

public class RendererCutouts {
    public static void registerCutouts() {
        /*BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
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
        );*/
        FULL_TREE_SETS.forEach(element -> onIf(element.BLOCK instanceof LeavesBlock || element.BLOCK instanceof DoorBlock
                        || element.BLOCK instanceof TrapdoorBlock,
                () -> putBlock(element.BLOCK))
        );

        CRYSTAL_GEODES.forEach(element ->
                onIf(element.BLOCK instanceof CorundumCluster, () -> putBlock(element.BLOCK))
        );

        STAINED_TREES.forEach(element -> putBlock(element.BLOCK));

    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
