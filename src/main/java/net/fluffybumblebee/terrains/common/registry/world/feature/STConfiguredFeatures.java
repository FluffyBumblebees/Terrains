package net.fluffybumblebee.terrains.common.registry.world.feature;

import net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import static net.fluffybumblebee.terrains.util.STUtil.*;

public class STConfiguredFeatures {

    private static final String colour0 = "black";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLACK_TREE = defOak(colour0, StainedBlocks.BLACK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLACK_TREE_BEES = defOakBees(colour0 + "_bees", StainedBlocks.BLACK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BLACK_TREE = fatOak("fat_" + colour0, StainedBlocks.BLACK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BLACK_TREE_BEES = fatOakBees("fat_" + colour0 + "_bees", StainedBlocks.BLACK_LEAVES_BLOCK);

    private static final String colour1 = "blue";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLUE_TREE = defOak(colour1, StainedBlocks.BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLUE_TREE_BEES = defOakBees(colour1 + "_bees", StainedBlocks.BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BLUE_TREE = fatOak("fat_" + colour1, StainedBlocks.BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BLUE_TREE_BEES = fatOakBees("fat_" + colour1 + "_bees", StainedBlocks.BLUE_LEAVES_BLOCK);

    private static final String colour2 = "brown";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BROWN_TREE = defOak(colour2, StainedBlocks.BROWN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BROWN_TREE_BEES = defOakBees(colour2 + "_bees", StainedBlocks.BROWN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BROWN_TREE = fatOak("fat_" + colour2, StainedBlocks.BROWN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_BROWN_TREE_BEES = fatOakBees("fat_" + colour2 + "_bees", StainedBlocks.BROWN_LEAVES_BLOCK);

    private static final String colour3 = "cyan";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CYAN_TREE = defOak(colour3, StainedBlocks.CYAN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CYAN_TREE_BEES = defOakBees(colour3 + "_bees", StainedBlocks.CYAN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_CYAN_TREE = fatOak("fat_" + colour3, StainedBlocks.CYAN_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_CYAN_TREE_BEES = fatOakBees("fat_" + colour3 + "_bees", StainedBlocks.CYAN_LEAVES_BLOCK);

    private static final String colour4 = "gray";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GRAY_TREE = defOak(colour4, StainedBlocks.GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GRAY_TREE_BEES = defOakBees(colour4 + "_bees", StainedBlocks.GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_GRAY_TREE = fatOak("fat_" + colour4, StainedBlocks.GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_GRAY_TREE_BEES = fatOakBees("fat_" + colour4 + "_bees", StainedBlocks.GRAY_LEAVES_BLOCK);

    private static final String colour5 = "light_blue";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIGHT_BLUE_TREE = defOak(colour5, StainedBlocks.LIGHT_BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIGHT_BLUE_TREE_BEES = defOakBees(colour5 + "_bees", StainedBlocks.LIGHT_BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIGHT_BLUE_TREE = fatOak("fat_" + colour5, StainedBlocks.LIGHT_BLUE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIGHT_BLUE_TREE_BEES = fatOakBees("fat_" + colour5 + "_bees", StainedBlocks.LIGHT_BLUE_LEAVES_BLOCK);

    private static final String colour6 = "light_gray";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIGHT_GRAY_TREE = defOak(colour6, StainedBlocks.LIGHT_GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIGHT_GRAY_TREE_BEES = defOakBees(colour6 + "_bees", StainedBlocks.LIGHT_GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIGHT_GRAY_TREE = fatOak("fat_" + colour6, StainedBlocks.LIGHT_GRAY_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIGHT_GRAY_TREE_BEES = fatOakBees("fat_" + colour6 + "_bees", StainedBlocks.LIGHT_GRAY_LEAVES_BLOCK);

    private static final String colour7 = "lime";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIME_TREE = defOak(colour7, StainedBlocks.LIME_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LIME_TREE_BEES = defOakBees(colour7 + "_bees", StainedBlocks.LIME_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIME_TREE = fatOak("fat_" + colour7, StainedBlocks.LIME_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_LIME_TREE_BEES = fatOakBees("fat_" + colour7 + "_bees", StainedBlocks.LIME_LEAVES_BLOCK);

    private static final String colour8 = "magenta";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAGENTA_TREE = defOak(colour8, StainedBlocks.MAGENTA_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAGENTA_TREE_BEES = defOakBees(colour8 + "_bees", StainedBlocks.MAGENTA_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_MAGENTA_TREE = fatOak("fat_" + colour8, StainedBlocks.MAGENTA_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_MAGENTA_TREE_BEES = fatOakBees("fat_" + colour8 + "_bees", StainedBlocks.MAGENTA_LEAVES_BLOCK);

    private static final String colour9 = "orange";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ORANGE_TREE = defOak(colour9, StainedBlocks.ORANGE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ORANGE_TREE_BEES = defOakBees(colour9 + "_bees", StainedBlocks.ORANGE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_ORANGE_TREE = fatOak("fat_" + colour9, StainedBlocks.ORANGE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_ORANGE_TREE_BEES = fatOakBees("fat_" + colour9 + "_bees", StainedBlocks.ORANGE_LEAVES_BLOCK);

    private static final String colour10 = "pink";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_TREE = defOak(colour10, StainedBlocks.PINK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_TREE_BEES = defOakBees(colour10 + "_bees", StainedBlocks.PINK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_PINK_TREE = fatOak("fat_" + colour10, StainedBlocks.PINK_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_PINK_TREE_BEES = fatOakBees("fat_" + colour10 + "_bees", StainedBlocks.PINK_LEAVES_BLOCK);

    private static final String colour11 = "purple";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PURPLE_TREE = defOak(colour11, StainedBlocks.PURPLE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PURPLE_TREE_BEES = defOakBees(colour11 + "_bees", StainedBlocks.PURPLE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_PURPLE_TREE = fatOak("fat_" + colour11, StainedBlocks.PURPLE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_PURPLE_TREE_BEES = fatOakBees("fat_" + colour11 + "_bees", StainedBlocks.PURPLE_LEAVES_BLOCK);

    private static final String colour12 = "red";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RED_TREE = defOak(colour12, StainedBlocks.RED_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RED_TREE_BEES = defOakBees(colour12 + "_bees", StainedBlocks.RED_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_RED_TREE = fatOak("fat_" + colour12, StainedBlocks.RED_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_RED_TREE_BEES = fatOakBees("fat_" + colour12 + "_bees", StainedBlocks.RED_LEAVES_BLOCK);

    private static final String colour13 = "white";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WHITE_TREE = defOak(colour13, StainedBlocks.WHITE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WHITE_TREE_BEES = defOakBees(colour13 + "_bees", StainedBlocks.WHITE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_WHITE_TREE = fatOak("fat_" + colour13, StainedBlocks.WHITE_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_WHITE_TREE_BEES = fatOakBees("fat_" + colour13 + "_bees", StainedBlocks.WHITE_LEAVES_BLOCK);

    private static final String colour14 = "yellow";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_TREE = defOak(colour14, StainedBlocks.YELLOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_TREE_BEES = defOakBees(colour14 + "_bees", StainedBlocks.YELLOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_YELLOW_TREE = fatOak("fat_" + colour14, StainedBlocks.YELLOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_YELLOW_TREE_BEES = fatOakBees("fat_" + colour14 + "_bees", StainedBlocks.YELLOW_LEAVES_BLOCK);

    private static final String colour15 = "rainbow";
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RAINBOW_TREE = defOak(colour15, StainedBlocks.RAINBOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RAINBOW_TREE_BEES = defOakBees(colour15 + "_bees", StainedBlocks.RAINBOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_RAINBOW_TREE = fatOak("fat_" + colour15, StainedBlocks.RAINBOW_LEAVES_BLOCK);
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_RAINBOW_TREE_BEES = fatOakBees("fat_" + colour15 + "_bees", StainedBlocks.RAINBOW_LEAVES_BLOCK);
}
