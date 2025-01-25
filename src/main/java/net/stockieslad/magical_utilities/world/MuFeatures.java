package net.stockieslad.magical_utilities.world;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.stockieslad.magical_utilities.MagicalUtilities;

public class MuFeatures {
    public static final Feature<OreFeatureConfig> CLOUD = register(MagicalUtilities.getIdentifier("cloud"), new CloudFeature(OreFeatureConfig.CODEC));

    public static void init() {}

    private static <C extends FeatureConfig, F extends Feature<C>> F register(Identifier id, F feature) {
        return Registry.register(Registries.FEATURE, id, feature);
    }
}
