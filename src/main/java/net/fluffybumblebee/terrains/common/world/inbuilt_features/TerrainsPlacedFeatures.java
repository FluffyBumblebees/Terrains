package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
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

    public static RegistryEntry<PlacedFeature> registerPlacedGeode(RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> geode, String name) {
        return register(
                getNamespaceVar() + name + "_geode_placed",
                geode,
                RarityFilterPlacementModifier.of(1440),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6),
                        YOffset.fixed(30)),
                BiomePlacementModifier.of()
        );
    }
}