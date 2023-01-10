package net.fluffybumblebee.better_meadows.world.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;

public class BMFeatures {

    @SuppressWarnings("deprecation")
    public static final Feature<MeadowLakeFeature.Config> LAKE = register(
            "meadow_lake",
            new MeadowLakeFeature(LakeFeature.Config.CODEC)
    );

    @SuppressWarnings("SameParameterValue")
    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }
}
