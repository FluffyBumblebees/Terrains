package net.stockieslad.magical_utilities.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.stockieslad.magical_utilities.core.Cloud;

import java.util.List;

public class MuPlacedFeatures {
    public static void bootstrap(Registerable<PlacedFeature> ctx) {
        var lookup = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        for (Cloud value : Cloud.values()) {
            if (value.placedFeature != null) {
                ctx.register(value.placedFeature, new PlacedFeature(lookup.getOrThrow(value.configuredFeature),
                        List.copyOf(value.modifiers)));
            }
        }
    }
}
