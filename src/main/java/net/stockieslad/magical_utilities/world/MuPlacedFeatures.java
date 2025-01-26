package net.stockieslad.magical_utilities.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.stockieslad.magical_utilities.core.Cloud;
import net.stockieslad.magical_utilities.util.FeatureHelper;

import java.util.List;

import static net.minecraft.world.gen.heightprovider.UniformHeightProvider.create;
import static net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier.of;

public class MuPlacedFeatures {
    public static void bootstrap(Registerable<PlacedFeature> ctx) {
        var lookup = ctx.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        for (Cloud value : Cloud.values()) {
            var entry = lookup.getOrThrow(value.configuredFeature);
            ctx.register(value.aetherPlacedFeature, new PlacedFeature(entry, FeatureHelper.modifiersWithRarity(512, of(create(YOffset.BOTTOM, YOffset.TOP)))));
            if (value.placedFeature != null) {
                ctx.register(value.placedFeature, new PlacedFeature(entry,
                        List.copyOf(value.modifiers)));
            }
        }
    }
}
