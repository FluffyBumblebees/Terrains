package net.fluffybumblebee.maple_forest.world.feature;

import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import static net.fluffybumblebee.maple_forest.util.registration.world.feature.MFConfiguredFeatureRegistration.BiasedRegistration.*;


@SuppressWarnings("unused")
public class MFConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RED_MAPLE_TREE_BEES;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> RED_MAPLE_SPAWN_BEES;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GREEN_MAPLE_TREE_BEES;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> GREEN_MAPLE_SPAWN_BEES;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_MAPLE_TREE_BEES;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> YELLOW_MAPLE_SPAWN_BEES;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ORANGE_MAPLE_TREE_BEES;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> ORANGE_MAPLE_SPAWN_BEES;

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RED_MAPLE_TREE;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> RED_MAPLE_SPAWN;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> GREEN_MAPLE_TREE;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> GREEN_MAPLE_SPAWN;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YELLOW_MAPLE_TREE;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> YELLOW_MAPLE_SPAWN;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ORANGE_MAPLE_TREE;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> ORANGE_MAPLE_SPAWN;
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BROWN_MAPLE_TREE;
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> BROWN_MAPLE_SPAWN;

    public static final TreeFeatureConfig DEAD_MAPLE_TREE;
    public static TreeFeatureConfig FALLEN_MAPLE_TREE_CONFIG;


    static {
        RED_MAPLE_TREE_BEES = createMapleTree(MFRegistry.RED_MAPLE_LEAVES, "red");
        RED_MAPLE_SPAWN_BEES = registerMapleTreeSpawner(MFRegistry.RED_MAPLE_SAPLING, RED_MAPLE_TREE_BEES, "red");
        GREEN_MAPLE_TREE_BEES = createMapleTree(MFRegistry.GREEN_MAPLE_LEAVES, "green");
        GREEN_MAPLE_SPAWN_BEES = registerMapleTreeSpawner(MFRegistry.GREEN_MAPLE_SAPLING, GREEN_MAPLE_TREE_BEES, "green");
        YELLOW_MAPLE_TREE_BEES = createMapleTree(MFRegistry.YELLOW_MAPLE_LEAVES, "yellow");
        YELLOW_MAPLE_SPAWN_BEES = registerMapleTreeSpawner(MFRegistry.YELLOW_MAPLE_SAPLING, YELLOW_MAPLE_TREE_BEES, "yellow");
        ORANGE_MAPLE_TREE_BEES = createMapleTree(MFRegistry.ORANGE_MAPLE_LEAVES, "orange");
        ORANGE_MAPLE_SPAWN_BEES = registerMapleTreeSpawner(MFRegistry.ORANGE_MAPLE_SAPLING, ORANGE_MAPLE_TREE_BEES, "orange");

        RED_MAPLE_TREE = createMapleTreeNoBees(MFRegistry.RED_MAPLE_LEAVES, "red");
        RED_MAPLE_SPAWN = registerMapleTreeSpawnerNoBees(MFRegistry.RED_MAPLE_SAPLING, RED_MAPLE_TREE, "red");
        GREEN_MAPLE_TREE = createMapleTreeNoBees(MFRegistry.GREEN_MAPLE_LEAVES, "green");
        GREEN_MAPLE_SPAWN = registerMapleTreeSpawnerNoBees(MFRegistry.GREEN_MAPLE_SAPLING, GREEN_MAPLE_TREE, "green");
        YELLOW_MAPLE_TREE = createMapleTreeNoBees(MFRegistry.YELLOW_MAPLE_LEAVES, "yellow");
        YELLOW_MAPLE_SPAWN = registerMapleTreeSpawnerNoBees(MFRegistry.YELLOW_MAPLE_SAPLING, YELLOW_MAPLE_TREE, "yellow");
        ORANGE_MAPLE_TREE = createMapleTreeNoBees(MFRegistry.ORANGE_MAPLE_LEAVES, "orange");
        ORANGE_MAPLE_SPAWN = registerMapleTreeSpawnerNoBees(MFRegistry.ORANGE_MAPLE_SAPLING, ORANGE_MAPLE_TREE, "orange");
        BROWN_MAPLE_TREE = createMapleTreeNoBees(MFRegistry.BROWN_MAPLE_LEAVES, "brown");
        BROWN_MAPLE_SPAWN = registerMapleTreeSpawnerNoBees(MFRegistry.BROWN_MAPLE_SAPLING, BROWN_MAPLE_TREE, "brown");

        FALLEN_MAPLE_TREE_CONFIG = registerFallenTrunkConfig(MFRegistry.MAPLE_LOG, MFRegistry.SAPPY_MAPLE_LOG);
        DEAD_MAPLE_TREE = registerDead(MFRegistry.MAPLE_LOG, MFRegistry.SAPPY_MAPLE_LOG);
    }
}
