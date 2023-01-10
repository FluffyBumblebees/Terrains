package net.fluffybumblebee.stainedtrees;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.stainedtrees.block.STBlocks.*;

public class StainedTreesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Leaves
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BLACK_LEAVES,
                BLUE_LEAVES,
                BROWN_LEAVES,
                CYAN_LEAVES,
                GRAY_LEAVES,
                LIGHT_BLUE_LEAVES,
                LIGHT_GRAY_LEAVES,
                LIME_LEAVES,
                MAGENTA_LEAVES,
                ORANGE_LEAVES,
                PINK_LEAVES,
                PURPLE_LEAVES,
                RED_LEAVES,
                WHITE_LEAVES,
                YELLOW_LEAVES,
                RAINBOW_LEAVES,
                BLACK_SAPLING,
                BLUE_SAPLING,
                BROWN_SAPLING,
                CYAN_SAPLING,
                GRAY_SAPLING,
                LIGHT_BLUE_SAPLING,
                LIGHT_GRAY_SAPLING,
                LIME_SAPLING,
                MAGENTA_SAPLING,
                ORANGE_SAPLING,
                PINK_SAPLING,
                PURPLE_SAPLING,
                RED_SAPLING,
                WHITE_SAPLING,
                YELLOW_SAPLING,
                RAINBOW_SAPLING,
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
