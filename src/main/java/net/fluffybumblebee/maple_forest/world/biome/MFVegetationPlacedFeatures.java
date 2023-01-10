package net.fluffybumblebee.maple_forest.world.biome;

import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;

public class MFVegetationPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> PUMPKINS;
    public static final RegistryEntry<PlacedFeature> FLOWER_MEADOW;
    public static final RegistryEntry<PlacedFeature> PATCH_GRASS_JUNGLE;
    public static final RegistryEntry<PlacedFeature> PATCH_DEAD_BUSH;


    static {
        PUMPKINS = PlacedFeatures.register(
                MapleForest.NAMESPACE + ":pumpkins",
                VegetationConfiguredFeatures.PATCH_PUMPKIN,
                RarityFilterPlacementModifier.of(4),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());
        FLOWER_MEADOW = PlacedFeatures.register(
                MapleForest.NAMESPACE + ":meadow_flowers",
                VegetationConfiguredFeatures.FLOWER_MEADOW,
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());
        PATCH_GRASS_JUNGLE = PlacedFeatures.register(
                MapleForest.NAMESPACE + ":patch_grass_forest",
                VegetationConfiguredFeatures.PATCH_GRASS_JUNGLE,
                modifiers(2));
        PATCH_DEAD_BUSH = PlacedFeatures.register(
                MapleForest.NAMESPACE + "patch_dead_bush",
                VegetationConfiguredFeatures.PATCH_DEAD_BUSH,
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());
    }
}

