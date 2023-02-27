package net.stockieslad.terrains.util.registration.world.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;
import static net.minecraft.util.registry.BuiltinRegistries.addCasted;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;
import static net.stockieslad.terrains.core.TerrainsDefaults.getNamespaceVar;

public class TerrainsFeatureRegistrar {
    public static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }
    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>>
    registerConfiguredFeature(String id, F feature, FC config) {
        return addCasted(CONFIGURED_FEATURE, getNamespaceVar() + id, new ConfiguredFeature<>(feature, config));
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return register(getNamespaceVar() +  id, registryEntry, modifiers);
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerPlacedFeature(id, registryEntry, List.of(modifiers));
    }
}
