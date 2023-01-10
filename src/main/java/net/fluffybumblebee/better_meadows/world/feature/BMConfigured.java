package net.fluffybumblebee.better_meadows.world.feature;

import net.fluffybumblebee.better_meadows.BetterMeadows;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.TreeFeatureConfig.Builder;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class BMConfigured {
    private static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> registerToMod(String id, F feature, FC config) {
        return BuiltinRegistries.method_40360(BuiltinRegistries.CONFIGURED_FEATURE, BetterMeadows.getNamespaceAppendable() + id, new ConfiguredFeature<>(feature, config));
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

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> MEADOW_LAKE = registerToMod(
            "spring_water",
            BMFeatures.LAKE,
            new LakeFeature.Config(BlockStateProvider.of(
                    Fluids.WATER.getDefaultState().getBlockState()),
                    BlockStateProvider.of(Blocks.OBSIDIAN)
            )
    );
}
