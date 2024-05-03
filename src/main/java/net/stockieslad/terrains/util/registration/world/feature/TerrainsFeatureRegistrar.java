package net.stockieslad.terrains.util.registration.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.stockieslad.abstractium.util.dynamic.Mimic;

import java.util.List;

import static net.stockieslad.abstractium.library.common.CommonTypeObjects.*;
import static net.stockieslad.terrains.core.TerrainsCommon.ABSTRACTION;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

public class TerrainsFeatureRegistrar {

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>>
    registerConfiguredFeature(String id, F feature, FC config) {
        return ABSTRACTION.getRegistrar().registerConfiguredFeature(
                getIdentifier(id),
                ABSTRACTION.getStructureCreator().createConfiguredFeature(
                        config,
                        feature
                )
        ).cast(registryEntry(configuredFeature(wildcard(), wildcard())));
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return ABSTRACTION.getRegistrar().registerPlacedFeature(
                getIdentifier(id),
                ABSTRACTION.getStructureCreator().createPlacedFeature(
                        new Mimic(registryEntry(configuredFeature(wildcard(), wildcard())), registryEntry),
                        modifiers
                )
        ).cast(registryEntry(placedFeature()));
    }

    public static RegistryEntry<PlacedFeature> registerPlacedFeature(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerPlacedFeature(id, registryEntry, List.of(modifiers));
    }
}
