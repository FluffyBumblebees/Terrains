package net.fluffybumblebee.maple_forest.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import static net.fluffybumblebee.maple_forest.util.registration.world.feature.MFPlacedFeatureRegistration.BiasedRegistration.*;


public class MFPlacedFeatures {
    public static void initClass() {}
    public static final RegistryEntry<PlacedFeature> RED_MAPLE_PLACED = registerMaplePlacer(MFConfiguredFeatures.RED_MAPLE_SPAWN_BEES, "red", "");
    public static final RegistryEntry<PlacedFeature> GREEN_MAPLE_PLACED = registerMaplePlacer(MFConfiguredFeatures.GREEN_MAPLE_SPAWN_BEES, "green", "");
    public static final RegistryEntry<PlacedFeature> RED_MAPLE_PLACED_NO_BEES = registerMaplePlacer(MFConfiguredFeatures.RED_MAPLE_SPAWN, "red");
    public static final RegistryEntry<PlacedFeature> GREEN_MAPLE_PLACED_NO_BEES = registerMaplePlacer(MFConfiguredFeatures.GREEN_MAPLE_SPAWN, "green");
    public static final RegistryEntry<PlacedFeature> YELLOW_MAPLE_PLACED_NO_BEES = registerMaplePlacer(MFConfiguredFeatures.YELLOW_MAPLE_SPAWN, "yellow");
    public static final RegistryEntry<PlacedFeature> ORANGE_MAPLE_PLACED_NO_BEES = registerMaplePlacer(MFConfiguredFeatures.ORANGE_MAPLE_SPAWN, "orange");
    public static final RegistryEntry<PlacedFeature> BROWN_MAPLE_PLACED_NO_BEES = registerMaplePlacer(MFConfiguredFeatures.BROWN_MAPLE_SPAWN, "brown");

    public static final RegistryEntry<PlacedFeature> DEAD_MAPLE = registryEntryOfPF("dead_maple", MFConfiguredFeatures.DEAD_MAPLE_TREE, 0.005F, 1);
    public static final RegistryEntry<PlacedFeature> MAPLE_TRUNK = registryEntryOfPF("maple", MFConfiguredFeatures.FALLEN_MAPLE_TREE_CONFIG, 0.005F, 2);
}
