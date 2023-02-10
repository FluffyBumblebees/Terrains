package net.fluffybumblebee.terrains.util.registration.world.feature;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;

public class TerrainsFeatureRegistrar {
    public static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }
    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>>
    registerConfiguredFeature(String id, F feature, FC config) {
        return OldFeatureRegistrar.registerConfigured(getNamespaceVar() + id, feature, config);
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return OldFeatureRegistrar.registerPlaced(getNamespaceVar() +  id, registryEntry, modifiers);
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerPlacedFeature(id, registryEntry, List.of(modifiers));
    }
}
