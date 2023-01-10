package net.fluffybumblebee.maple_forest.util.registration.world.feature;

import com.google.common.base.Preconditions;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;
import net.fluffybumblebee.maple_forest.world.placer.FallenTrunkPlacer;
import net.fluffybumblebee.maple_forest.world.placer.ConeFoliagePlacer;
import net.fluffybumblebee.maple_forest.world.placer.NoneFoliagePlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class MFConfiguredFeatureRegistration {
    public static RegistryEntry<PlacedFeature> createTreeChecker(
            String name,
            String suffix,
            SaplingBlock sapling,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree
    ) {
        return PlacedFeatures.register(MapleForest.NAMESPACE + ":" + name + "_checker" + "_" + suffix, tree,
                PlacedFeatures.wouldSurvive(sapling));
    }

    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> createTreeSpawn(
            String name,
            RegistryEntry<PlacedFeature> treeChecked,
            float checked
    ) {
        return ConfiguredFeatures.register(MapleForest.NAMESPACE + ":" + name + "_spawner", Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(treeChecked, checked)),
                        treeChecked));
    }
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static class BiasedRegistration {
        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> mapleTreeTemplate(
                String name,
                Block log,
                Block logVariant,
                Block leaves,
                int trunkHeight,
                int trunkRandomHeight,
                int trunkSecondRandomHeight,
                int radius,
                int offset,
                int limit,
                int upperLimit,
                int lowerSize,
                int middleSize,
                int upperSize,
                float beeChance,
                OptionalInt minClippedHeight
        ) {
            DataPool<BlockState> randomBlock = DataPool.<BlockState>builder()
                    .add(log.getDefaultState(), 2)
                    .add(logVariant.getDefaultState(), 1)
                    .build();
            return ConfiguredFeatures.register(name + "_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    new WeightedBlockStateProvider(randomBlock),
                    new StraightTrunkPlacer(trunkHeight, trunkRandomHeight, trunkSecondRandomHeight),
                    BlockStateProvider.of(leaves.getDefaultState()),
                    new ConeFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(offset)),
                    new ThreeLayersFeatureSize(limit,upperLimit, lowerSize, middleSize, upperSize, minClippedHeight))
                    .decorators(List.of(new BeehiveTreeDecorator(beeChance))).build());
        }
        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createMapleTree(
                Block leaves,
                String colour,
                String suffix,
                float beeChance
        ) {
            return mapleTreeTemplate
                    (MapleForest.NAMESPACE + ":" + colour + "_" + MFWoodTypes.MAPLE + "_" + suffix,
                            MFRegistry.MAPLE_LOG,
                            MFRegistry.SAPPY_MAPLE_LOG,
                            leaves,
                            6,
                            5,
                            5,
                            2,
                            0,
                            1,
                            1,
                            0,
                            0,
                            2,
                            beeChance,
                            OptionalInt.empty()
                    );
        }
        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createMapleTree(
                Block leaves,
                String colour
        ) {
            return createMapleTree(
                    leaves,
                    colour,
                    "",
                    0.1F
            );
        }
        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createMapleTreeNoBees(
                Block leaves,
                String colour
        ) {
            return createMapleTree(
                    leaves,
                    colour,
                    "bees",
                    0F
            );
        }
        public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> registerMapleTreeSpawner(
                SaplingBlock sapling, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree,
                String colour,
                String suffix,
                Boolean addBees
        ) {
            if (addBees) {
                return MFConfiguredFeatureRegistration.createTreeSpawn
                        (
                                colour + "_" + MFWoodTypes.MAPLE + "_" + suffix,
                                MFConfiguredFeatureRegistration.createTreeChecker
                                        (
                                                colour + "_" + MFWoodTypes.MAPLE,
                                                "",
                                                sapling,
                                                tree
                                        ),
                                0.5f

                        );
            } else {
                return MFConfiguredFeatureRegistration.createTreeSpawn
                        (
                                colour + "_" + MFWoodTypes.MAPLE + "_" + suffix,
                                MFConfiguredFeatureRegistration.createTreeChecker(
                                                colour + "_" + MFWoodTypes.MAPLE,
                                                "no_bees",
                                                sapling,
                                                tree
                                        ),
                                0.5f

                        );
            }
        }
        public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> registerMapleTreeSpawner(
                SaplingBlock sapling,
                RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree,
                String colour
        ) {
            return registerMapleTreeSpawner(
                    sapling,
                    tree,
                    colour,
                    "",
                    true
            );
        }
        public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> registerMapleTreeSpawnerNoBees(
                SaplingBlock sapling,
                RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree,
                String colour
        ) {
            return registerMapleTreeSpawner(
                    sapling,
                    tree,
                    colour,
                    "no_bees",
                    false
            );
        }
        public static <B extends Block> TreeFeatureConfig registerFallenTrunkConfig(B log, B logVariant) {
            DataPool<BlockState> randomBlock = DataPool.<BlockState>builder()
                    .add(log.getDefaultState(), 2)
                    .add(logVariant.getDefaultState(), 1)
                    .build();
            return  new TreeFeatureConfig.Builder(
                    new WeightedBlockStateProvider(randomBlock),
                    new FallenTrunkPlacer(3, 2, 0),
                    BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
                    new NoneFoliagePlacer(),
                    new TwoLayersFeatureSize(0, 0, 0)
            ).build();
        }
        public static <B extends Block> TreeFeatureConfig registerDead(B log, B logVariant) {
            DataPool<BlockState> randomBlock = DataPool.<BlockState>builder()
                    .add(log.getDefaultState(), 2)
                    .add(logVariant.getDefaultState(), 1)
                    .build();
            return  new TreeFeatureConfig.Builder(
                    new WeightedBlockStateProvider(randomBlock),
                    new StraightTrunkPlacer(5, 4, 4),
                    BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
                    new NoneFoliagePlacer(),
                    new TwoLayersFeatureSize(0, 0, 0)
            ).build();
        }
    }
    static  <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return register(id, new ConfiguredFeature<>(feature, config));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, ConfiguredFeature<FC, F> cf) {
        Identifier realId = new Identifier(MapleForest.NAMESPACE, id);
        Preconditions.checkState(!BuiltinRegistries.CONFIGURED_FEATURE.getIds().contains(realId), "Duplicate ID: %s", id);
        return BuiltinRegistries.method_40360(BuiltinRegistries.CONFIGURED_FEATURE, realId.toString(), cf);
    }
}
