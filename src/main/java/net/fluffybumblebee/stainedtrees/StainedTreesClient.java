package net.fluffybumblebee.stainedtrees;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks.*;

public class StainedTreesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Leaves
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BLACK_LEAVES_BLOCK,
                BLUE_LEAVES_BLOCK,
                BROWN_LEAVES_BLOCK,
                CYAN_LEAVES_BLOCK,
                GRAY_LEAVES_BLOCK,
                LIGHT_BLUE_LEAVES_BLOCK,
                LIGHT_GRAY_LEAVES_BLOCK,
                LIME_LEAVES_BLOCK,
                MAGENTA_LEAVES_BLOCK,
                ORANGE_LEAVES_BLOCK,
                PINK_LEAVES_BLOCK,
                PURPLE_LEAVES_BLOCK,
                RED_LEAVES_BLOCK,
                WHITE_LEAVES_BLOCK,
                YELLOW_LEAVES_BLOCK,
                RAINBOW_LEAVES_BLOCK,
                BLACK_SAPLING_BLOCK,
                BLUE_SAPLING_BLOCK,
                BROWN_SAPLING_BLOCK,
                CYAN_SAPLING_BLOCK,
                GRAY_SAPLING_BLOCK,
                LIGHT_BLUE_SAPLING_BLOCK,
                LIGHT_GRAY_SAPLING_BLOCK,
                LIME_SAPLING_BLOCK,
                MAGENTA_SAPLING_BLOCK,
                ORANGE_SAPLING_BLOCK,
                PINK_SAPLING_BLOCK,
                PURPLE_SAPLING_BLOCK,
                RED_SAPLING_BLOCK,
                WHITE_SAPLING_BLOCK,
                YELLOW_SAPLING_BLOCK,
                RAINBOW_SAPLING_BLOCK,
                POTTED_BLACK_SAPLING,
                POTTED_BLUE_SAPLING,
                POTTED_BROWN_SAPLING,
                POTTED_CYAN_SAPLING,
                POTTED_GRAY_SAPLING,
                POTTED_LIGHT_BLUE_SAPLING,
                POTTED_LIGHT_GRAY_SAPLING,
                POTTED_LIME_SAPLING,
                POTTED_MAGENTA_SAPLING,
                POTTED_ORANGE_SAPLING,
                POTTED_PINK_SAPLING,
                POTTED_PURPLE_SAPLING,
                POTTED_RED_SAPLING,
                POTTED_WHITE_SAPLING,
                POTTED_YELLOW_SAPLING,
                POTTED_RAINBOW_SAPLING
        );
    }
}
