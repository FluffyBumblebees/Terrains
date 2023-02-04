package net.fluffybumblebee.terrains.util.registration.world.modification;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.addCarver;
import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature;

public class EasyBiomeModification {
    public static void generateCarver(
            RegistryEntry<ConfiguredCarver<?>> carver,
            Predicate<BiomeSelectionContext> predicate,
            GenerationStep.Carver carverStep) {
        addCarver(predicate, carverStep, carver.getKey().orElseThrow());
    }

    public static void generateFeature(RegistryEntry<PlacedFeature> feature, Predicate<BiomeSelectionContext> predicate, GenerationStep.Feature featureStep) {
        addFeature(predicate, featureStep, feature.getKey().orElseThrow());
    }
}
