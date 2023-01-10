package net.fluffybumblebee.quarkcrystals.world.gen.feature;

import net.fluffybumblebee.terrains.common.default_abstract.block.CorundumCluster;
import net.fluffybumblebee.terrains.util.QCUtil;
import net.minecraft.block.Block;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;


public class QCUndergroundConfiguredFeatures  {
    private static <B extends Block, C extends CorundumCluster> RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> register(B corundum, B crystal, C cluster, String colour) {
        return QCUtil.registerGeode(corundum, crystal, cluster, colour);
    }
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BLACK_GEODE = register(CrystalBlocks.BLACK_CORUNDUM, CrystalBlocks.BLACK_CRYSTAL, CrystalBlocks.BLACK_CORUNDUM_CLUSTER, "black");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BLUE_GEODE = register(CrystalBlocks.BLUE_CORUNDUM, CrystalBlocks.BLUE_CRYSTAL, CrystalBlocks.BLUE_CORUNDUM_CLUSTER, "blue");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> GREEN_GEODE = register(CrystalBlocks.GREEN_CORUNDUM, CrystalBlocks.GREEN_CRYSTAL, CrystalBlocks.GREEN_CORUNDUM_CLUSTER, "green");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> INDIGO_GEODE = register(CrystalBlocks.INDIGO_CORUNDUM, CrystalBlocks.INDIGO_CRYSTAL, CrystalBlocks.INDIGO_CORUNDUM_CLUSTER, "indigo");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> ORANGE_GEODE = register(CrystalBlocks.ORANGE_CORUNDUM, CrystalBlocks.ORANGE_CRYSTAL, CrystalBlocks.ORANGE_CORUNDUM_CLUSTER, "orange");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> RED_GEODE = register(CrystalBlocks.RED_CORUNDUM, CrystalBlocks.RED_CRYSTAL, CrystalBlocks.RED_CORUNDUM_CLUSTER, "red");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> PINK_GEODE = register(CrystalBlocks.PINK_CORUNDUM, CrystalBlocks.PINK_CRYSTAL, CrystalBlocks.PINK_CORUNDUM_CLUSTER, "pink");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> WHITE_GEODE = register(CrystalBlocks.WHITE_CORUNDUM, CrystalBlocks.WHITE_CRYSTAL, CrystalBlocks.WHITE_CORUNDUM_CLUSTER, "white");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> YELLOW_GEODE = register(CrystalBlocks.YELLOW_CORUNDUM, CrystalBlocks.YELLOW_CRYSTAL, CrystalBlocks.YELLOW_CORUNDUM_CLUSTER, "yellow");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CYAN_GEODE = register(CrystalBlocks.CYAN_CORUNDUM, CrystalBlocks.CYAN_CRYSTAL, CrystalBlocks.CYAN_CORUNDUM_CLUSTER, "cyan");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> PURPLE_GEODE = register(CrystalBlocks.PURPLE_CORUNDUM, CrystalBlocks.PURPLE_CRYSTAL, CrystalBlocks.PURPLE_CORUNDUM_CLUSTER, "purple");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BROWN_GEODE = register(CrystalBlocks.BROWN_CORUNDUM, CrystalBlocks.BROWN_CRYSTAL, CrystalBlocks.BROWN_CORUNDUM_CLUSTER, "brown");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> RAINBOW_GEODE = register(CrystalBlocks.RAINBOW_CORUNDUM, CrystalBlocks.RAINBOW_CRYSTAL, CrystalBlocks.RAINBOW_CORUNDUM_CLUSTER, "rainbow");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> DARK_GEODE = register(CrystalBlocks.DARK_CORUNDUM, CrystalBlocks.DARK_CRYSTAL, CrystalBlocks.DARK_CORUNDUM_CLUSTER, "dark");

}