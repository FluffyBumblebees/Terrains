package net.fluffybumblebee.terrains.util.registration.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.function.Predicate;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature;

public class TreeRegistration {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void generateFeature(RegistryEntry<PlacedFeature> feature, Predicate<BiomeSelectionContext> predicate, GenerationStep.Feature featureType) {
        addFeature(predicate, featureType, feature.getKey().get());
    }

    public static RegistryEntry<PlacedFeature> registerGenericPlaced(String type, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree, int countMod) {
        return PlacedFeatures.register(
                type,
                tree,
                CountPlacementModifier.of(countMod),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(BlockTags.DIRT, Direction.DOWN.getVector()))
        );
    }
}
