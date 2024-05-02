package net.stockieslad.terrains.common.registry.sets.tree.whole.maple;

import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.StandardSaplingGenerator;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.component.FallenTrunkPlacer;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.component.HemiEllipsoidFoliagePlacer;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.component.NoneFoliagePlacer;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.stockieslad.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.EnumArrayToString;
import static net.stockieslad.terrains.util.registration.world.feature.TreeRegistration.treePlacementModifiers;
import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;

public final class MapleTreeType {
    public static final TreeType<MapleFeatures, UniqueMapleFeatures> MAPLE_TREES =
            new TreeType<>(
                    "maple",
                    EnumArrayToString(MapleTypes.values()),
                    new String[]{
                            "sappy_maple_log"
                    },
                    MapleFeatures::new,
                    UniqueMapleFeatures::new
            );

    public enum MapleTypes {
        RED,
        BROWN,
        ORANGE,
        YELLOW,
        PASTEL_GREEN,
        GREEN,
        BLUE,
        WHITE,
        TYRIAN_PURPLE
    }
    public static final class MapleFeatures implements FeatureCreator {
        private final List<Block> ALL_LOGS;
        public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> CONFIGURED_TREE_NO_BEES;
        public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> CONFIGURED_TREE_BEES;
        public final RegistryEntry<PlacedFeature> PLACED_TREE_NO_BEES;
        public final RegistryEntry<PlacedFeature> PLACED_TREE_BEES;

        private MapleFeatures(final List<Block> allLogs, final LeavesBlock leaves, final String type) {
            ALL_LOGS = allLogs;
            CONFIGURED_TREE_NO_BEES = getConfiguredMaple(type, leaves, false);
            CONFIGURED_TREE_BEES = getConfiguredMaple(type, leaves, true);
            PLACED_TREE_NO_BEES = getPlacedMaple(type, CONFIGURED_TREE_NO_BEES, false);
            PLACED_TREE_BEES = getPlacedMaple(type, CONFIGURED_TREE_BEES, true);
        }

        @Override
        public StandardSaplingGenerator createNew() {
            return new StandardSaplingGenerator(
                    ctx -> CONFIGURED_TREE_NO_BEES,
                    ctx -> CONFIGURED_TREE_BEES
            );
        }

        private RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>>
        getConfiguredMaple(final String type, final LeavesBlock leaves, final boolean bees) {
            final DataPool<BlockState> dataPool = new DataPool.Builder<BlockState>()
                    .add(ALL_LOGS.get(0).getDefaultState(), 15)
                    .add(ALL_LOGS.get(1).getDefaultState(), 1)
                    .build();

            var builder = new TreeFeatureConfig.Builder(
                    new WeightedBlockStateProvider(dataPool),
                    new StraightTrunkPlacer(4, 3, 4),
                    BlockStateProvider.of(leaves),
                    new HemiEllipsoidFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                    new ThreeLayersFeatureSize(1, 1, 0, 0, 1, OptionalInt.empty())
            );

            String beeState = "no_bees";

            if (bees) {
                builder.decorators(List.of(new BeehiveTreeDecorator(0.05F)));
                beeState = "bees";
            }

            return ConfiguredFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + type +  "_tree_" + beeState,
                    Feature.TREE,
                    builder.build());
        }
        private RegistryEntry<PlacedFeature> getPlacedMaple(
                final String type, final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> tree, final boolean bees) {
            String beeState = "no_bees";
            if (bees)
                beeState = "bees";
            return PlacedFeatures.register(
                    type + "_tree_"+ beeState + "_placed",
                    tree,
                    treePlacementModifiers(createCountExtraModifier(1, 0.05F, 1))
            );
        }
    }

    public static final class UniqueMapleFeatures {
        private final DataPool<BlockState> MAPLE_LOGS;

        public final RegistryEntry<PlacedFeature> DEAD_MAPLE_TREE;
        public final RegistryEntry<PlacedFeature> FALLEN_MAPLE_TRUNK;

        private UniqueMapleFeatures(final List<Block> allLogs, final List<Block> allLeaves, final String type) {
            MAPLE_LOGS = DataPool.<BlockState>builder()
                    .add(allLogs.get(0).getDefaultState(), 2)
                    .add(allLogs.get(1).getDefaultState(), 1)
                    .build();

            final var DEAD_MAPLE_TREE_CONFIG = ConfiguredFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + "dead_" + type + "_tree",
                    Feature.TREE,
                    getDeadMapleTree(new StraightTrunkPlacer(5, 4, 4))
            );

            final var FALLEN_MAPLE_TREE_CONFIG = ConfiguredFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + "fallen_" + type + "_trunk",
                    Feature.TREE,
                    getDeadMapleTree(new FallenTrunkPlacer(3, 2, 0))
            );

            DEAD_MAPLE_TREE = PlacedFeatures.register(
                    "dead_maple_tree_placed",
                    DEAD_MAPLE_TREE_CONFIG,
                    treePlacementModifiers(createCountExtraModifier(0, 0.5F, 1))
            );

            FALLEN_MAPLE_TRUNK = PlacedFeatures.register(
                    "fallen_maple_tree_placed",
                    FALLEN_MAPLE_TREE_CONFIG,
                    treePlacementModifiers(createCountExtraModifier(0, 0.5F, 1))
            );
        }

        private TreeFeatureConfig getDeadMapleTree(TrunkPlacer trunkPlacer) {
            return new TreeFeatureConfig.Builder(
                    new WeightedBlockStateProvider(MAPLE_LOGS),
                    trunkPlacer,
                    BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
                    new NoneFoliagePlacer(),
                    new TwoLayersFeatureSize(0, 0, 0)
            ).build();
        }
    }
}
