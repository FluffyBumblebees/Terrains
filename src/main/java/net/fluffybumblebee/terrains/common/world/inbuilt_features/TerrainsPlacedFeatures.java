package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;
import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiersWithWouldSurvive;

public class TerrainsPlacedFeatures {
    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return register(getNamespaceVar() +  id, registryEntry, modifiers);
    }

    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerToMod(id, registryEntry, List.of(modifiers));
    }

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE = registerToMod(
            "meadow_spruce",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.25f, 2))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE_SPARSE = registerToMod(
            "meadow_spruce_sparse",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.5f, 1))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_BUSH = registerToMod(
            "meadow_bush",
            TerrainsConfiguredFeatures.MEADOW_BUSH,
            modifiersWithWouldSurvive(createCountExtraModifier(1, 0.5f, 2), Blocks.OAK_SAPLING)
    );


    public static final RegistryEntry<PlacedFeature> MEADOW_LAKE = registerToMod(
            "meadow_lake",
            TerrainsConfiguredFeatures.MEADOW_LAKE,
            modifiers(createCountExtraModifier(0, 0.1f, 20))
    );

    public static final RegistryEntry<PlacedFeature> HUGE_RED_MUSHROOM = registerToMod(
            "huge_red_mushroom",
            TreeConfiguredFeatures.HUGE_RED_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

    public static final RegistryEntry<PlacedFeature> HUGE_BROWN_MUSHROOM = registerToMod(
            "huge_brown_mushroom",
            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

    public static final RegistryEntry<PlacedFeature> SCATTERED_PUMPKINS = registerToMod(
            "scattered_pumpkins",
            TerrainsConfiguredFeatures.SCATTERED_PUMPKIN,
            createCountExtraModifier(3, 0.05F, 1),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FLOWER_MEADOW = registerToMod(
            "meadow_flowers",
            VegetationConfiguredFeatures.FLOWER_MEADOW,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_JUNGLE = registerToMod(
            "patch_grass_forest",
            VegetationConfiguredFeatures.PATCH_GRASS_JUNGLE,
            modifiers(2)
    );

    public static final RegistryEntry<PlacedFeature> PATCH_DEAD_BUSH = registerToMod(
            "patch_dead_bush",
            VegetationConfiguredFeatures.PATCH_DEAD_BUSH,
            SquarePlacementModifier.of(),
            PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FOREST_FLOWERS = registerToMod(
            "forest_flowers",
            VegetationConfiguredFeatures.FOREST_FLOWERS,
            RarityFilterPlacementModifier.of(7),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> FOREST_GRASS_PATCH = registerToMod(
            "forest_grass_patch",
            VegetationConfiguredFeatures.PATCH_GRASS,
            modifiers(2)
    );



}
