package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.wood_set.WoodBlock;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;
import net.fluffybumblebee.terrains.common.world.feature.raw.component.ConeFoliagePlacer;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureConfigEntries;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public final class AllTreeTypes {
    private AllTreeTypes() {}
    public static final TreeType<MapleSaplingGenerator> MAPLE = new TreeType<>(
            List.of(BlockSet.buildFlammableBlock(new WoodBlock(), "sappy_maple_log")),
            "maple",
            new String[] {
                    "yellow",
                    "orange",
                    "red",
                    "green",
                    "brown"
            },
            new String[] {
                    "no_bees",
                    "bees"
            },
            new String[] {
                    "no_bees",
                    "bees"
            },
            (type, log, leaves) -> {
                DataPool.Builder<BlockState> dataBuilder = DataPool.builder();
                for (BlockSet<?> set : log) {
                    dataBuilder.add(set.BLOCK.getDefaultState(), 1);
                }
                return new TreeFeatureConfig.Builder(
                        new WeightedBlockStateProvider(dataBuilder.build()),
                        new StraightTrunkPlacer(6, 5, 5),
                        BlockStateProvider.of(leaves.getDefaultState()),
                        new ConeFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 0, 2, OptionalInt.empty())
                );
            },
            (type, log, leaves, treePattern, configuredFeatureHolder, configuredVariants, placedFeatureHolder, placedVariants) -> {
                for (String variants : configuredVariants) {
                    configuredFeatureHolder.put(variants, ConfiguredFeatures.register(
                            type.toLowerCase() + "_" + variants.toLowerCase(),
                            Feature.TREE,
                            treePattern.build(
                                    type,
                                    log,
                                    leaves
                            ).build()));
                }
                for (int i = 0; i < placedVariants.length; i++) {
                    placedFeatureHolder.put(placedVariants[i], PlacedFeatures.register(
                            type + "_" + placedVariants[i] + "_placed",
                            RegistryEntry.of(configuredFeatureHolder.get(configuredVariants[i]).value()),
                            PlacedFeatures.wouldSurvive(Blocks.DIRT)
                    ));
                }
            },
            (configuredFeatures, configuredVariants) ->
                    new MapleSaplingGenerator(() -> configuredFeatures.get(configuredVariants[0]))
    );

    private static final FeatureConfigEntries<TreeType<?>> ENTRIES = new FeatureConfigEntries<TreeType<?>>(new TreeType[] {
            MAPLE
    });
    public static FeatureConfigEntries<TreeType<?>> getTypes() {
        return ENTRIES;
    }
}
