package net.fluffybumblebee.quarkcrystals.world.gen.feature;

import net.fluffybumblebee.quarkcrystals.block.QCBlocks;
import net.fluffybumblebee.quarkcrystals.block.custom.CorundumCluster;
import net.fluffybumblebee.quarkcrystals.util.QCUtil;
import net.minecraft.block.Block;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;


public class QCUndergroundConfiguredFeatures  {
    private static <B extends Block, C extends CorundumCluster> RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> register(B corundum, B crystal, C cluster, String colour) {
        return QCUtil.registerGeode(corundum, crystal, cluster, colour);
    }
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BLACK_GEODE = register(QCBlocks.BLACK_CORUNDUM, QCBlocks.BLACK_CRYSTAL, QCBlocks.BLACK_CORUNDUM_CLUSTER, "black");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BLUE_GEODE = register(QCBlocks.BLUE_CORUNDUM, QCBlocks.BLUE_CRYSTAL, QCBlocks.BLUE_CORUNDUM_CLUSTER, "blue");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> GREEN_GEODE = register(QCBlocks.GREEN_CORUNDUM, QCBlocks.GREEN_CRYSTAL, QCBlocks.GREEN_CORUNDUM_CLUSTER, "green");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> INDIGO_GEODE = register(QCBlocks.INDIGO_CORUNDUM, QCBlocks.INDIGO_CRYSTAL, QCBlocks.INDIGO_CORUNDUM_CLUSTER, "indigo");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> ORANGE_GEODE = register(QCBlocks.ORANGE_CORUNDUM, QCBlocks.ORANGE_CRYSTAL, QCBlocks.ORANGE_CORUNDUM_CLUSTER, "orange");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> RED_GEODE = register(QCBlocks.RED_CORUNDUM, QCBlocks.RED_CRYSTAL, QCBlocks.RED_CORUNDUM_CLUSTER, "red");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> PINK_GEODE = register(QCBlocks.PINK_CORUNDUM, QCBlocks.PINK_CRYSTAL, QCBlocks.PINK_CORUNDUM_CLUSTER, "pink");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> WHITE_GEODE = register(QCBlocks.WHITE_CORUNDUM, QCBlocks.WHITE_CRYSTAL, QCBlocks.WHITE_CORUNDUM_CLUSTER, "white");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> YELLOW_GEODE = register(QCBlocks.YELLOW_CORUNDUM, QCBlocks.YELLOW_CRYSTAL, QCBlocks.YELLOW_CORUNDUM_CLUSTER, "yellow");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CYAN_GEODE = register(QCBlocks.CYAN_CORUNDUM, QCBlocks.CYAN_CRYSTAL, QCBlocks.CYAN_CORUNDUM_CLUSTER, "cyan");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> PURPLE_GEODE = register(QCBlocks.PURPLE_CORUNDUM, QCBlocks.PURPLE_CRYSTAL, QCBlocks.PURPLE_CORUNDUM_CLUSTER, "purple");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BROWN_GEODE = register(QCBlocks.BROWN_CORUNDUM, QCBlocks.BROWN_CRYSTAL, QCBlocks.BROWN_CORUNDUM_CLUSTER, "brown");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> RAINBOW_GEODE = register(QCBlocks.RAINBOW_CORUNDUM, QCBlocks.RAINBOW_CRYSTAL, QCBlocks.RAINBOW_CORUNDUM_CLUSTER, "rainbow");
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> DARK_GEODE = register(QCBlocks.DARK_CORUNDUM, QCBlocks.DARK_CRYSTAL, QCBlocks.DARK_CORUNDUM_CLUSTER, "dark");

}