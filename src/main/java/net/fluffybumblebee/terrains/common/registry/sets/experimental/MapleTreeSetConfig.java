package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.wood_set.WoodBlock;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;
import net.fluffybumblebee.terrains.common.world.feature.raw.component.ConeFoliagePlacer;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
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

public class MapleTreeSetConfig {
    public static final ComplexTreeSetConfig<
            WoodTypes,
            TreeVariant,
            ConfiguredVariants,
            PlacedVariants,
            MapleSaplingGenerator> MAPLE =
            new ComplexTreeSetConfig<>(
                    List.of(BlockSet.buildFlammableBlock(new WoodBlock(), "sappy_maple_log")),
                    WoodTypes.MAPLE,
                    TreeVariant.values(),
                    ConfiguredVariants.values(),
                    PlacedVariants.values(),
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
                        for (ConfiguredVariants variants : configuredVariants) {
                            configuredFeatureHolder.put(variants, ConfiguredFeatures.register(
                                    type.toLowerCase() + "_" + variants.name().toLowerCase(),
                                    Feature.TREE,
                                    treePattern.build(
                                            type,
                                            log,
                                            leaves
                                    ).build()));
                        }
                        for (int i = 0; i < placedVariants.length; i++) {
                            placedFeatureHolder.put(placedVariants[i], PlacedFeatures.register(
                                    type + "_" + placedVariants[i].name().toLowerCase() + "_placed",
                                    RegistryEntry.of(configuredFeatureHolder.get(configuredVariants[i]).value()),
                                    PlacedFeatures.wouldSurvive(Blocks.DIRT)
                            ));
                        }
                    },
                    (configuredFeatures, configuredVariants) ->
                            new MapleSaplingGenerator(() -> configuredFeatures.get(configuredVariants[0]))
            );

    enum WoodTypes {
        MAPLE
    }

    enum TreeVariant {
        YELLOW,
        GREEN,
        BROWN,
        RED,
        ORANGE
    }

    enum ConfiguredVariants {
        TREES_NO_BEES,
        TREES_BEES,
    }

    enum PlacedVariants {
        TREES_NO_BEES,
        TREES_BEES
    }

    public static void register() {

    }
}
