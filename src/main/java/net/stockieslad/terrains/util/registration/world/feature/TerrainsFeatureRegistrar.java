package net.stockieslad.terrains.util.registration.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.stockieslad.abstractium.library.common.AbstractCommonCalls;
import net.stockieslad.abstractium.util.dynamic.Mimic;

import java.util.List;

import static net.stockieslad.abstractium.init.AbstractiumCommon.COMMON_ABSTRACTION_HANDLER;
import static net.stockieslad.abstractium.library.common.CommonTypeObjects.*;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

public class TerrainsFeatureRegistrar {
    public static final AbstractCommonCalls ABSTRACT_COMMON_CALLS = COMMON_ABSTRACTION_HANDLER.abstraction;

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>>
    registerConfiguredFeature(String id, F feature, FC config) {
        return ABSTRACT_COMMON_CALLS.getRegistrar().registerConfiguredFeature(
                               getIdentifier(id),
                               ABSTRACT_COMMON_CALLS
                                       .getStructureCreator()
                                       .createConfiguredFeature(
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
        return ABSTRACT_COMMON_CALLS.getRegistrar().registerPlacedFeature(
                getIdentifier(id),
                ABSTRACT_COMMON_CALLS.getStructureCreator().createPlacedFeature(
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
