package net.fluffybumblebee.quarkcrystals.world.gen.feature;

import net.fluffybumblebee.quarkcrystals.util.QCUtil;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public class QCUndergroundPlacedFeatures {
    public static RegistryEntry<PlacedFeature> register(RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> geode, String name) {
        return QCUtil.registerPlacedGeode(geode, name);
    }
    public static final RegistryEntry<PlacedFeature> BLACK_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.BLACK_GEODE, "black");
    public static final RegistryEntry<PlacedFeature> BLUE_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.BLUE_GEODE, "blue");
    public static final RegistryEntry<PlacedFeature> GREEN_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.GREEN_GEODE, "green");
    public static final RegistryEntry<PlacedFeature> INDIGO_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.INDIGO_GEODE, "indigo");
    public static final RegistryEntry<PlacedFeature> ORANGE_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.ORANGE_GEODE, "orange");
    public static final RegistryEntry<PlacedFeature> RED_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.RED_GEODE, "red");
    public static final RegistryEntry<PlacedFeature> PINK_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.PINK_GEODE, "pink");
    public static final RegistryEntry<PlacedFeature> WHITE_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.WHITE_GEODE, "white");
    public static final RegistryEntry<PlacedFeature> YELLOW_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.YELLOW_GEODE, "yellow");
    public static final RegistryEntry<PlacedFeature> CYAN_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.CYAN_GEODE, "cyan");
    public static final RegistryEntry<PlacedFeature> PURPLE_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.PURPLE_GEODE, "purple");
    public static final RegistryEntry<PlacedFeature> BROWN_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.BROWN_GEODE, "brown");
    public static final RegistryEntry<PlacedFeature> RAINBOW_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.RAINBOW_GEODE, "rainbow");
    public static final RegistryEntry<PlacedFeature> DARK_GEODE_PLACED = register(QCUndergroundConfiguredFeatures.DARK_GEODE, "dark");
}
