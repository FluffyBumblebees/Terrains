package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.TreeFeatureConfig.Builder;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

import static net.minecraft.world.gen.feature.ConfiguredFeatures.register;

@SuppressWarnings("deprecation")
public class TerrainsConfiguredFeatures {
    private static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> registerToMod(String id, F feature, FC config) {
        return BuiltinRegistries.method_40360(BuiltinRegistries.CONFIGURED_FEATURE, TerrainsDefaults.getNamespaceVar() + id, new ConfiguredFeature<>(feature, config));
    }

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEADOW_BUSH = registerToMod(
            "meadow_bush", Feature.TREE,
            new Builder(
                    BlockStateProvider.of(Blocks.OAK_LOG),
                    new StraightTrunkPlacer(1, 0, 0),
                    BlockStateProvider.of(Blocks.OAK_LEAVES),
                    new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                    new TwoLayersFeatureSize(0, 0, 0)
            ).build()
    );

    public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> MEADOW_LAKE = registerToMod(
            "spring_water",
            TerrainsFeatures.LAKE,
            new LakeFeature.Config(BlockStateProvider.of(
                    Fluids.WATER.getDefaultState().getBlockState()),
                    BlockStateProvider.of(Blocks.OBSIDIAN)
            )
    );

    public static <B extends Block, C extends AmethystClusterBlock> RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> registerGeode(
            B corundum,
            B crystal,
            C cluster,
            String colour
    ) {
        WeightedBlockStateProvider outerLayers = new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                .add(crystal.getDefaultState(), 5000)
                .add(corundum.getDefaultState(), 100)
                .add(Blocks.EMERALD_BLOCK.getDefaultState(), 2)
                .add(Blocks.DIAMOND_BLOCK.getDefaultState(), 1)
                .build());

        return register(
                TerrainsDefaults.getNamespaceVar() + colour + "_geode",
                Feature.GEODE,
                new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                BlockStateProvider.of(Blocks.AIR),
                                BlockStateProvider.of(crystal),
                                BlockStateProvider.of(corundum),
                                outerLayers,
                                outerLayers,
                                List.of(cluster.getDefaultState()),
                                BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                        new GeodeLayerThicknessConfig(3.4, 5.6, 7.2, 9.4),
                        new GeodeCrackConfig(0.95, 2.0, 2), 0.35, 0.083, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16,
                        16,
                        0.05,
                        1)
        );
    }
}
