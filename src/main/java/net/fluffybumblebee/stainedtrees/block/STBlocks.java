package net.fluffybumblebee.stainedtrees.block;

import net.fluffybumblebee.stainedtrees.util.STUtil;
import net.fluffybumblebee.stainedtrees.world.feature.STConfiguredFeatures;
import net.fluffybumblebee.stainedtrees.world.feature.tree.STSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;


public class STBlocks {
    public static void initClass() {
    }
    private static final FlammableBlockRegistry INSTANCE = FlammableBlockRegistry.getDefaultInstance();
    private static  <B extends Block> B registerBlocks(B block, String colour, String suffix) {
        if (block instanceof ShortenedFlowerPotBlock) return STUtil.registerPottedSapling(block, "potted_" + colour + "_" + suffix);
        if (block instanceof Leaves) INSTANCE.add(block, 30, 60);
        return STUtil.registerBlocks(block, colour + "_" + suffix);
    }
    public static final Leaves BLACK_LEAVES = registerBlocks(new Leaves(), "black", "leaves");
    public static final Leaves BLUE_LEAVES = registerBlocks(new Leaves(), "blue", "leaves");
    public static final Leaves BROWN_LEAVES = registerBlocks(new Leaves(), "brown", "leaves");
    public static final Leaves CYAN_LEAVES = registerBlocks(new Leaves(), "cyan", "leaves");
    public static final Leaves GRAY_LEAVES = registerBlocks(new Leaves(), "gray", "leaves");
    public static final Leaves LIGHT_BLUE_LEAVES = registerBlocks(new Leaves(), "light_blue", "leaves");
    public static final Leaves LIGHT_GRAY_LEAVES = registerBlocks(new Leaves(), "light_gray", "leaves");
    public static final Leaves LIME_LEAVES = registerBlocks(new Leaves(), "lime", "leaves");
    public static final Leaves MAGENTA_LEAVES = registerBlocks(new Leaves(), "magenta", "leaves");
    public static final Leaves ORANGE_LEAVES = registerBlocks(new Leaves(), "orange", "leaves");
    public static final Leaves PINK_LEAVES = registerBlocks(new Leaves(), "pink", "leaves");
    public static final Leaves PURPLE_LEAVES = registerBlocks(new Leaves(), "purple", "leaves");
    public static final Leaves RED_LEAVES = registerBlocks(new Leaves(), "red", "leaves");
    public static final Leaves WHITE_LEAVES = registerBlocks(new Leaves(), "white", "leaves");
    public static final Leaves YELLOW_LEAVES = registerBlocks(new Leaves(), "yellow", "leaves");
    public static final Leaves RAINBOW_LEAVES = registerBlocks(new Leaves(), "rainbow", "leaves");

    public static final Sapling BLACK_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.BLACK_TREE,
            () -> STConfiguredFeatures.BLACK_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BLACK_TREE,
            () -> STConfiguredFeatures.FAT_BLACK_TREE_BEES
    )),   "black", "sapling");
    public static final Sapling BLUE_SAPLING =  registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.BLUE_TREE,
            () -> STConfiguredFeatures.BLUE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BLUE_TREE,
            () -> STConfiguredFeatures.FAT_BLUE_TREE_BEES
    )),   "blue", "sapling");
    public static final Sapling BROWN_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.BROWN_TREE,
            () -> STConfiguredFeatures.BROWN_TREE_BEES,
            () -> STConfiguredFeatures.FAT_BROWN_TREE,
            () -> STConfiguredFeatures.FAT_BROWN_TREE_BEES
    )),   "brown", "sapling");
    public static final Sapling CYAN_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.CYAN_TREE,
            () -> STConfiguredFeatures.CYAN_TREE_BEES,
            () -> STConfiguredFeatures.FAT_CYAN_TREE,
            () -> STConfiguredFeatures.FAT_CYAN_TREE_BEES
    )),   "cyan", "sapling");
    public static final Sapling GRAY_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.GRAY_TREE,
            () -> STConfiguredFeatures.GRAY_TREE_BEES,
            () -> STConfiguredFeatures.FAT_GRAY_TREE,
            () -> STConfiguredFeatures.FAT_GRAY_TREE_BEES
    )),   "gray", "sapling");
    public static final Sapling LIGHT_BLUE_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIGHT_BLUE_TREE,
            () -> STConfiguredFeatures.LIGHT_BLUE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIGHT_BLUE_TREE,
            () -> STConfiguredFeatures.FAT_LIGHT_BLUE_TREE_BEES
    )),   "light_blue", "sapling");
    public static final Sapling LIGHT_GRAY_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIGHT_GRAY_TREE,
            () -> STConfiguredFeatures.LIGHT_GRAY_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIGHT_GRAY_TREE,
            () -> STConfiguredFeatures.FAT_LIGHT_GRAY_TREE_BEES
    )),   "light_gray", "sapling");
    public static final Sapling LIME_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.LIME_TREE,
            () -> STConfiguredFeatures.LIME_TREE_BEES,
            () -> STConfiguredFeatures.FAT_LIME_TREE,
            () -> STConfiguredFeatures.FAT_LIME_TREE_BEES
    )),   "lime", "sapling");
    public static final Sapling MAGENTA_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.MAGENTA_TREE,
            () -> STConfiguredFeatures.MAGENTA_TREE_BEES,
            () -> STConfiguredFeatures.FAT_MAGENTA_TREE,
            () -> STConfiguredFeatures.FAT_MAGENTA_TREE_BEES
    )),   "magenta", "sapling");
    public static final Sapling ORANGE_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.ORANGE_TREE,
            () -> STConfiguredFeatures.ORANGE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_ORANGE_TREE,
            () -> STConfiguredFeatures.FAT_ORANGE_TREE_BEES
    )),   "orange", "sapling");
    public static final Sapling PINK_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.PINK_TREE,
            () -> STConfiguredFeatures.PINK_TREE_BEES,
            () -> STConfiguredFeatures.FAT_PINK_TREE,
            () -> STConfiguredFeatures.FAT_PINK_TREE_BEES
    )),   "pink", "sapling");
    public static final Sapling PURPLE_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.PURPLE_TREE,
            () -> STConfiguredFeatures.PURPLE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_PURPLE_TREE,
            () -> STConfiguredFeatures.FAT_PURPLE_TREE_BEES
    )),   "purple", "sapling");
    public static final Sapling RED_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.RED_TREE,
            () -> STConfiguredFeatures.RED_TREE_BEES,
            () -> STConfiguredFeatures.FAT_RED_TREE,
            () -> STConfiguredFeatures.FAT_RED_TREE_BEES
    )),   "red", "sapling");
    public static final Sapling WHITE_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.WHITE_TREE,
            () -> STConfiguredFeatures.WHITE_TREE_BEES,
            () -> STConfiguredFeatures.FAT_WHITE_TREE,
            () -> STConfiguredFeatures.FAT_WHITE_TREE_BEES
    )),   "white", "sapling");
    public static final Sapling YELLOW_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.YELLOW_TREE,
            () -> STConfiguredFeatures.YELLOW_TREE_BEES,
            () -> STConfiguredFeatures.FAT_YELLOW_TREE,
            () -> STConfiguredFeatures.FAT_YELLOW_TREE_BEES
    )),   "yellow", "sapling");
    public static final Sapling RAINBOW_SAPLING = registerBlocks(new Sapling(new STSaplingGenerator(
            () -> STConfiguredFeatures.RAINBOW_TREE,
            () -> STConfiguredFeatures.RAINBOW_TREE_BEES,
            () -> STConfiguredFeatures.FAT_RAINBOW_TREE,
            () -> STConfiguredFeatures.FAT_RAINBOW_TREE_BEES
    )),   "rainbow", "sapling");

    public static final Block POTTED_BLACK_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(BLACK_SAPLING), "black", "sapling");
    public static final Block POTTED_BLUE_SAPLING =  registerBlocks(new ShortenedFlowerPotBlock(BLUE_SAPLING), "blue", "sapling");
    public static final Block POTTED_BROWN_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(BROWN_SAPLING), "brown", "sapling");
    public static final Block POTTED_CYAN_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(CYAN_SAPLING), "cyan", "sapling");
    public static final Block POTTED_GRAY_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(GRAY_SAPLING), "gray", "sapling");
    public static final Block POTTED_LIGHT_BLUE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIGHT_BLUE_SAPLING), "light_blue", "sapling");
    public static final Block POTTED_LIGHT_GRAY_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIGHT_GRAY_SAPLING), "light_gray", "sapling");
    public static final Block POTTED_LIME_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(LIME_SAPLING), "lime", "sapling");
    public static final Block POTTED_MAGENTA_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(MAGENTA_SAPLING), "magenta", "sapling");
    public static final Block POTTED_ORANGE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(ORANGE_SAPLING), "orange", "sapling");
    public static final Block POTTED_PINK_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(PINK_SAPLING), "pink", "sapling");
    public static final Block POTTED_PURPLE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(PURPLE_SAPLING), "purple", "sapling");
    public static final Block POTTED_RED_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(RED_SAPLING), "red", "sapling");
    public static final Block POTTED_WHITE_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(WHITE_SAPLING), "white", "sapling");
    public static final Block POTTED_YELLOW_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(YELLOW_SAPLING), "yellow", "sapling");
    public static final Block POTTED_RAINBOW_SAPLING = registerBlocks(new ShortenedFlowerPotBlock(RAINBOW_SAPLING), "rainbow", "sapling");


    private static class ShortenedFlowerPotBlock extends FlowerPotBlock {
        public ShortenedFlowerPotBlock(Block content) {
            super(content, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
        }
    }}
