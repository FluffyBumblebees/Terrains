package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import static net.fluffybumblebee.terrains.util.registration.world.feature.TerrainsFeatureRegistrar.registerPlacedFeature;
import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiersWithWouldSurvive;

public class TerrainsPlacedFeatures {
    //Lakes
    public static final RegistryEntry<PlacedFeature> LAKE_SMALL = registerPlacedFeature(
            "lake_small",
            TerrainsConfiguredFeatures.LAKE,
            modifiers(createCountExtraModifier(0, 0.1f, 20))
    );

    //Trees
    public static final RegistryEntry<PlacedFeature> TREES_SPRUCE_PATCH = registerPlacedFeature(
            "trees_spruce_patch",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.25f, 2))
    );

    public static final RegistryEntry<PlacedFeature> TREES_SPRUCE_SPARSE = registerPlacedFeature(
            "trees_spruce_sparse",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.5f, 1))
    );

    public static final RegistryEntry<PlacedFeature> TREES_MUSHROOM_RED = registerPlacedFeature(
            "trees_mushroom_red",
            TreeConfiguredFeatures.HUGE_RED_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

    public static final RegistryEntry<PlacedFeature> TREES_MUSHROOM_BROWN = registerPlacedFeature(
            "trees_mushroom_brown",
            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

    public static final RegistryEntry<PlacedFeature> TREES_OAK_BUSH_UNCOMMON = registerPlacedFeature(
            "trees_oak_bush_uncommon",
            TerrainsConfiguredFeatures.OAK_BUSH,
            modifiersWithWouldSurvive(createCountExtraModifier(0, 0.5f, 1), Blocks.OAK_SAPLING)
    );

    public static final RegistryEntry<PlacedFeature> TREES_OAK_BUSH_COMMON = registerPlacedFeature(
            "trees_oak_bush_common",
            TerrainsConfiguredFeatures.OAK_BUSH,
            modifiersWithWouldSurvive(createCountExtraModifier(1, 0.5f, 2), Blocks.OAK_SAPLING)
    );

    public static final RegistryEntry<PlacedFeature> SCATTERED_PUMPKINS = registerPlacedFeature(
            "scattered_pumpkins",
            TerrainsConfiguredFeatures.SCATTERED_PUMPKIN,
            createCountExtraModifier(3, 0.05F, 1),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    //Grass Patches
    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_JUNGLE = registerPlacedFeature(
            "patch_grass_jungle",
            VegetationConfiguredFeatures.PATCH_GRASS_JUNGLE,
            modifiers(2)
    );

    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_FOREST = registerPlacedFeature(
            "patch_grass_forest",
            VegetationConfiguredFeatures.PATCH_GRASS,
            modifiers(2)
    );

    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_PLAIN = registerPlacedFeature(
            "patch_grass_plain",
            VegetationConfiguredFeatures.PATCH_GRASS,
            NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
            SquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_TALL = PlacedFeatures.register(
            "patch_grass_tall",
            VegetationConfiguredFeatures.PATCH_TALL_GRASS,
            RarityFilterPlacementModifier.of(5),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_SAVANNA = registerPlacedFeature(
            "patch_grass_savanna",
            VegetationConfiguredFeatures.PATCH_GRASS,
            modifiers(20)
    );


    //Dead Bush
    public static final RegistryEntry<PlacedFeature> PATCH_DEAD_BUSH = registerPlacedFeature(
            "patch_dead_bush",
            VegetationConfiguredFeatures.PATCH_DEAD_BUSH,
            SquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    //Ferns
    public static final RegistryEntry<PlacedFeature> PATCH_FERN_LARGE = registerPlacedFeature(
            "patch_fern_large",
            VegetationConfiguredFeatures.PATCH_LARGE_FERN,
            RarityFilterPlacementModifier.of(5),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    //Flowers
    public static final RegistryEntry<PlacedFeature> FLOWERS_DEFAULT = registerPlacedFeature(
            "flowers_default",
            VegetationConfiguredFeatures.FLOWER_DEFAULT,
            RarityFilterPlacementModifier.of(32),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FLOWERS_FOREST = registerPlacedFeature(
            "flowers_forest",
            VegetationConfiguredFeatures.FOREST_FLOWERS,
            RarityFilterPlacementModifier.of(7),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FLOWERS_MEADOW = registerPlacedFeature(
            "flowers_meadow",
            VegetationConfiguredFeatures.FLOWER_MEADOW,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FLOWERS_PLAINS = registerPlacedFeature(
            "flowers_plains",
            VegetationConfiguredFeatures.FLOWER_PLAIN,
            NoiseThresholdCountPlacementModifier.of(-0.8, 15, 4),
            RarityFilterPlacementModifier.of(32),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FLOWER_WARM = registerPlacedFeature(
            "flower_warm",
            VegetationConfiguredFeatures.FLOWER_DEFAULT,
            RarityFilterPlacementModifier.of(16),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    //Bonemeal
    public static final RegistryEntry<PlacedFeature> BONEMEAL = registerPlacedFeature(
            "grass_bonemeal",
            VegetationConfiguredFeatures.SINGLE_PIECE_OF_GRASS,
            PlacedFeatures.isAir()
    );

    //Berry Bush
    public static final RegistryEntry<PlacedFeature> PATCH_BERRY_COMMON = registerPlacedFeature(
            "patch_berry_common",
            VegetationConfiguredFeatures.PATCH_BERRY_BUSH,
            RarityFilterPlacementModifier.of(32),
            SquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
            BiomePlacementModifier.of()
    );
}
