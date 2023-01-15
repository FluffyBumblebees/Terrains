package net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;
import net.fluffybumblebee.terrains.common.world.feature.raw.component.ConeFoliagePlacer;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.EnumArrayToString;

public final class MapleTreeType implements FeatureCreator<MapleSaplingGenerator> {
    public enum MapleTypes {
        RED,
        BROWN,
        ORANGE,
        YELLOW,
        GREEN
    }

    public static final TreeType<MapleSaplingGenerator, MapleTreeType> MAPLE_TREES =
            new TreeType<>(
                    "maple",
                    EnumArrayToString(MapleTypes.values()),
                    new String[] {
                            "sappy_maple_log"
                    },
                    MapleTreeType::new
            );

    private final List<Block> ALL_LOGS;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> CONFIGURED_TREE_NO_BEES;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> CONFIGURED_TREE_BEES;
    public final RegistryEntry<PlacedFeature> PLACED_TREE_NO_BEES;
    public final RegistryEntry<PlacedFeature> PLACED_TREE_BEES;

    private MapleTreeType(final List<Block> allLogs, final LeavesBlock leaves, final String type) {
        ALL_LOGS = allLogs;
        CONFIGURED_TREE_NO_BEES = getConfiguredMaple(type, leaves, false);
        CONFIGURED_TREE_BEES = getConfiguredMaple(type, leaves, true);
        PLACED_TREE_NO_BEES = getPlacedMaple(type, CONFIGURED_TREE_NO_BEES, false);
        PLACED_TREE_BEES = getPlacedMaple(type, CONFIGURED_TREE_BEES, true);
    }

    @Override
    public MapleSaplingGenerator createNew() {
        return new MapleSaplingGenerator(
                ctx -> CONFIGURED_TREE_NO_BEES,
                ctx -> CONFIGURED_TREE_BEES
        );
    }

    private RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>>
    getConfiguredMaple(final String type, final LeavesBlock leaves, final boolean bees) {
        final DataPool<BlockState> dataPool = new DataPool.Builder<BlockState>()
                .add(ALL_LOGS.get(0).getDefaultState(), 2)
                .add(ALL_LOGS.get(1).getDefaultState(), 1)
                .build();

        var builder = new TreeFeatureConfig.Builder(
                new WeightedBlockStateProvider(dataPool),
                new StraightTrunkPlacer(6, 5, 5),
                BlockStateProvider.of(leaves),
                new ConeFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 0, 2, OptionalInt.empty())
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
                PlacedFeatures.createCountExtraModifier(1, 0.05F, 1),
                PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING)

        );
    }
}
