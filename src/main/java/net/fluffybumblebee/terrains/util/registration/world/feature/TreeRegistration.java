package net.fluffybumblebee.terrains.util.registration.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.common.default_abstract.block.SaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class TreeRegistration {
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> defOak(String colour, Block block) {
        return ConfiguredFeatures.register(
                TerrainsDefaults.NAMESPACE + ":" + colour + "_tree",
                Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.of(block.getDefaultState()),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                )
                        .build()
        );
    }
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> defOakBees(String colour, Block block) {
        return ConfiguredFeatures.register(
                TerrainsDefaults.NAMESPACE + ":" + colour + "_tree",
                Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.of(block.getDefaultState()),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                )
                        .decorators(List.of(new BeehiveTreeDecorator(0.05F)))
                        .build()
        );
    }
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fatOak(String colour, Block block) {
        return ConfiguredFeatures.register(
                TerrainsDefaults.NAMESPACE + ":" + colour + "_tree",
                Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                        new LargeOakTrunkPlacer(3, 11, 0),
                        BlockStateProvider.of(block.getDefaultState()),
                        new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                )
                        .ignoreVines()
                        .build()
        );
    }
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fatOakBees(String colour, Block block) {
        return ConfiguredFeatures.register(
                TerrainsDefaults.NAMESPACE + ":" + colour + "_tree",
                Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                        new LargeOakTrunkPlacer(3, 11, 0),
                        BlockStateProvider.of(block.getDefaultState()),
                        new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                )
                        .ignoreVines()
                        .decorators(List.of(new BeehiveTreeDecorator(0.05F)))
                        .build()
        );
    }
    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> registerTreeSpawn(
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> def,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> def_bees,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fat,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fat_bees,
            String colour,
            SaplingBlock saplingBlock
    ) {
        PlacedFeatures.register(TerrainsDefaults.NAMESPACE + ":" + colour + "_checked", def, PlacedFeatures.wouldSurvive(saplingBlock));
        PlacedFeatures.register(TerrainsDefaults.NAMESPACE + ":" + "fat_" + colour + "_checked", fat, PlacedFeatures.wouldSurvive(saplingBlock));
        RegistryEntry<PlacedFeature> DEF_BEES = PlacedFeatures.register(TerrainsDefaults.NAMESPACE + ":" + colour + "_bees_checked", def_bees, PlacedFeatures.wouldSurvive(saplingBlock));
        RegistryEntry<PlacedFeature> FAT_BEES = PlacedFeatures.register(TerrainsDefaults.NAMESPACE + ":" + "fat" + colour + "_fat_checked", fat_bees, PlacedFeatures.wouldSurvive(saplingBlock));

        return ConfiguredFeatures.register(colour + "_spawn", Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        List.of(
                                new RandomFeatureEntry(FAT_BEES, 0.1f)
                        ), DEF_BEES));
    }
    public static RegistryEntry<PlacedFeature> createPlaced(
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> def,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> def_bees,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fat,
            RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> fat_bees,
            SaplingBlock saplingBlock,
            String colour
    ) {
        return PlacedFeatures.register(TerrainsDefaults.NAMESPACE + ":" + colour + "_placed",
                registerTreeSpawn(def, def_bees, fat, fat_bees, colour, saplingBlock),
                VegetationPlacedFeatures.modifiers(
                        PlacedFeatures.createCountExtraModifier(0, 0.01f, 1)
                ));
    }
    public static void generateTree(RegistryEntry<PlacedFeature> tree) {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                tree.getKey().get());
    }
}
