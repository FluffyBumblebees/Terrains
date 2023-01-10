package net.fluffybumblebee.terrains.common.registry.blocks;

import net.fluffybumblebee.terrains.common.default_abstract.block.LeavesBlock;
import net.fluffybumblebee.terrains.common.default_abstract.block.SaplingBlock;
import net.fluffybumblebee.terrains.util.STUtil;
import net.fluffybumblebee.terrains.common.registry.world.feature.STConfiguredFeatures;
import net.fluffybumblebee.terrains.common.registry.world.feature.tree.STSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;


public class StainedBlocks {
    public static void initClass() {
    }
    private static final FlammableBlockRegistry INSTANCE = FlammableBlockRegistry.getDefaultInstance();
    private static  <B extends Block> B registerBlocks(B block, String colour, String suffix) {
        if (block instanceof ShortenedFlowerPotBlock) return STUtil.registerPottedSapling(block, "potted_" + colour + "_" + suffix);
        if (block instanceof LeavesBlock) INSTANCE.add(block, 30, 60);
        return STUtil.registerBlocks(block, colour + "_" + suffix);
    }
    public static final LeavesBlock BLACK_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "black", "leaves");
    public static final LeavesBlock BLUE_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "blue", "leaves");
    public static final LeavesBlock BROWN_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "brown", "leaves");
    public static final LeavesBlock CYAN_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "cyan", "leaves");
    public static final LeavesBlock GRAY_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "gray", "leaves");
    public static final LeavesBlock LIGHT_BLUE_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "light_blue", "leaves");
    public static final LeavesBlock LIGHT_GRAY_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "light_gray", "leaves");
    public static final LeavesBlock LIME_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "lime", "leaves");
    public static final LeavesBlock MAGENTA_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "magenta", "leaves");
    public static final LeavesBlock ORANGE_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "orange", "leaves");
    public static final LeavesBlock PINK_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "pink", "leaves");
    public static final LeavesBlock PURPLE_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "purple", "leaves");
    public static final LeavesBlock RED_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "red", "leaves");
    public static final LeavesBlock WHITE_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "white", "leaves");
    public static final LeavesBlock YELLOW_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "yellow", "leaves");
    public static final LeavesBlock RAINBOW_LEAVES_BLOCK = registerBlocks(new LeavesBlock(), "rainbow", "leaves");

    public static final SaplingBlock BLACK_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.BLACK_TREE,
            () -> STConfiguredFeatures.BLACK_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BLACK_TREE,
            () -> STConfiguredFeatures.FAT_BLACK_TREE_BEES
    )),   "black", "sapling");
    public static final SaplingBlock BLUE_SAPLING_BLOCK =  registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.BLUE_TREE,
            () -> STConfiguredFeatures.BLUE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BLUE_TREE,
            () -> STConfiguredFeatures.FAT_BLUE_TREE_BEES
    )),   "blue", "sapling");
    public static final SaplingBlock BROWN_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.BROWN_TREE,
            () -> STConfiguredFeatures.BROWN_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BROWN_TREE,
            () -> STConfiguredFeatures.FAT_BROWN_TREE_BEES
    )),   "brown", "sapling");
    public static final SaplingBlock CYAN_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.CYAN_TREE,
            () -> STConfiguredFeatures.CYAN_TREE_BEES,
            () -> STConfiguredFeatures.FAT_CYAN_TREE,
            () -> STConfiguredFeatures.FAT_CYAN_TREE_BEES
    )),   "cyan", "sapling");
    public static final SaplingBlock GRAY_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.GRAY_TREE,
            () -> STConfiguredFeatures.GRAY_TREE_BEES,
            () -> STConfiguredFeatures.FAT_GRAY_TREE,
            () -> STConfiguredFeatures.FAT_GRAY_TREE_BEES
    )),   "gray", "sapling");
    public static final SaplingBlock LIGHT_BLUE_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIGHT_BLUE_TREE,
            () -> STConfiguredFeatures.LIGHT_BLUE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIGHT_BLUE_TREE,
            () -> STConfiguredFeatures.FAT_LIGHT_BLUE_TREE_BEES
    )),   "light_blue", "sapling");
    public static final SaplingBlock LIGHT_GRAY_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIGHT_GRAY_TREE,
            () -> STConfiguredFeatures.LIGHT_GRAY_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIGHT_GRAY_TREE,
            () -> STConfiguredFeatures.FAT_LIGHT_GRAY_TREE_BEES
    )),   "light_gray", "sapling");
    public static final SaplingBlock LIME_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIME_TREE,
            () -> STConfiguredFeatures.LIME_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIME_TREE,
            () -> STConfiguredFeatures.FAT_LIME_TREE_BEES
    )),   "lime", "sapling");
    public static final SaplingBlock MAGENTA_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.MAGENTA_TREE,
            () -> STConfiguredFeatures.MAGENTA_TREE_BEES,
            () -> STConfiguredFeatures.FAT_MAGENTA_TREE,
            () -> STConfiguredFeatures.FAT_MAGENTA_TREE_BEES
    )),   "magenta", "sapling");
    public static final SaplingBlock ORANGE_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.ORANGE_TREE,
            () -> STConfiguredFeatures.ORANGE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_ORANGE_TREE,
            () -> STConfiguredFeatures.FAT_ORANGE_TREE_BEES
    )),   "orange", "sapling");
    public static final SaplingBlock PINK_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.PINK_TREE,
            () -> STConfiguredFeatures.PINK_TREE_BEES,
            () -> STConfiguredFeatures.FAT_PINK_TREE,
            () -> STConfiguredFeatures.FAT_PINK_TREE_BEES
    )),   "pink", "sapling");
    public static final SaplingBlock PURPLE_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.PURPLE_TREE,
            () -> STConfiguredFeatures.PURPLE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_PURPLE_TREE,
            () -> STConfiguredFeatures.FAT_PURPLE_TREE_BEES
    )),   "purple", "sapling");
    public static final SaplingBlock RED_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.RED_TREE,
            () -> STConfiguredFeatures.RED_TREE_BEES,
            () -> STConfiguredFeatures.FAT_RED_TREE,
            () -> STConfiguredFeatures.FAT_RED_TREE_BEES
    )),   "red", "sapling");
    public static final SaplingBlock WHITE_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.WHITE_TREE,
            () -> STConfiguredFeatures.WHITE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_WHITE_TREE,
            () -> STConfiguredFeatures.FAT_WHITE_TREE_BEES
    )),   "white", "sapling");
    public static final SaplingBlock YELLOW_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.YELLOW_TREE,
            () -> STConfiguredFeatures.YELLOW_TREE_BEES,
            () -> STConfiguredFeatures.FAT_YELLOW_TREE,
            () -> STConfiguredFeatures.FAT_YELLOW_TREE_BEES
    )),   "yellow", "sapling");
    public static final SaplingBlock RAINBOW_SAPLING_BLOCK = registerBlocks(new SaplingBlock(new STSaplingGenerator(
            () -> STConfiguredFeatures.RAINBOW_TREE,
            () -> STConfiguredFeatures.RAINBOW_TREE_BEES,
            () -> STConfiguredFeatures.FAT_RAINBOW_TREE,
            () -> STConfiguredFeatures.FAT_RAINBOW_TREE_BEES
    )),   "rainbow", "sapling");

    public static final Block POTTED_BLACK_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(BLACK_SAPLING_BLOCK), "black", "sapling");
    public static final Block POTTED_BLUE_SAPLING =  registerBlocks(new ShortenedFlowerPotBlock(BLUE_SAPLING_BLOCK), "blue", "sapling");
    public static final Block POTTED_BROWN_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(BROWN_SAPLING_BLOCK), "brown", "sapling");
    public static final Block POTTED_CYAN_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(CYAN_SAPLING_BLOCK), "cyan", "sapling");
    public static final Block POTTED_GRAY_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(GRAY_SAPLING_BLOCK), "gray", "sapling");
    public static final Block POTTED_LIGHT_BLUE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIGHT_BLUE_SAPLING_BLOCK), "light_blue", "sapling");
    public static final Block POTTED_LIGHT_GRAY_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIGHT_GRAY_SAPLING_BLOCK), "light_gray", "sapling");
    public static final Block POTTED_LIME_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIME_SAPLING_BLOCK), "lime", "sapling");
    public static final Block POTTED_MAGENTA_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(MAGENTA_SAPLING_BLOCK), "magenta", "sapling");
    public static final Block POTTED_ORANGE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(ORANGE_SAPLING_BLOCK), "orange", "sapling");
    public static final Block POTTED_PINK_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(PINK_SAPLING_BLOCK), "pink", "sapling");
    public static final Block POTTED_PURPLE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(PURPLE_SAPLING_BLOCK), "purple", "sapling");
    public static final Block POTTED_RED_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(RED_SAPLING_BLOCK), "red", "sapling");
    public static final Block POTTED_WHITE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(WHITE_SAPLING_BLOCK), "white", "sapling");
    public static final Block POTTED_YELLOW_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(YELLOW_SAPLING_BLOCK), "yellow", "sapling");
    public static final Block POTTED_RAINBOW_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(RAINBOW_SAPLING_BLOCK), "rainbow", "sapling");


    private static class ShortenedFlowerPotBlock extends FlowerPotBlock {
        public ShortenedFlowerPotBlock(Block content) {
            super(content, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
        }
    }}
