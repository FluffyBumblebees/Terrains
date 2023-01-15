package net.fluffybumblebee.terrains.util.registration.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature;

public class TreeRegistration {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void generateFeature(RegistryEntry<PlacedFeature> feature, Predicate<BiomeSelectionContext> predicate, GenerationStep.Feature featureType) {
        addFeature(predicate, featureType, feature.getKey().get());
    }
}
