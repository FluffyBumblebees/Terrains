package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.TreeFeatureConfig.Builder;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

import static net.fluffybumblebee.terrains.util.registration.world.feature.TerrainsFeatureRegistrar.registerConfiguredFeature;
import static net.minecraft.world.gen.feature.ConfiguredFeatures.createRandomPatchFeatureConfig;
import static net.minecraft.world.gen.feature.ConfiguredFeatures.register;

@SuppressWarnings("deprecation")
public class TerrainsConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> OAK_BUSH = registerConfiguredFeature(
            "meadow_bush",
            Feature.TREE,
            new Builder(
                    BlockStateProvider.of(Blocks.OAK_LOG),
                    new StraightTrunkPlacer(1, 0, 0),
                    BlockStateProvider.of(Blocks.OAK_LEAVES),
                    new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                    new TwoLayersFeatureSize(0, 0, 0)
            ).build()
    );

    public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> LAKE = registerConfiguredFeature(
            "spring_water",
            TerrainsFeatures.LAKE,
            new LakeFeature.Config(BlockStateProvider.of(
                    Fluids.WATER.getDefaultState().getBlockState()),
                    BlockStateProvider.of(Blocks.OBSIDIAN)
            )
    );

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SCATTERED_PUMPKIN = register(
            "scattered_pumpkin",
            TerrainsFeatures.SCATTERED_BLOCk,
            createRandomPatchFeatureConfig(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.PUMPKIN)),
                    List.of(Blocks.GRASS_BLOCK)
            )
    );

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SCATTERED_MELON = register(
            "scattered_melon",
            TerrainsFeatures.SCATTERED_BLOCk,
            createRandomPatchFeatureConfig(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.MELON)),
                    List.of(Blocks.GRASS_BLOCK)
            )
    );
}
