package net.fluffybumblebee.terrains.common.world.feature;

import net.fluffybumblebee.terrains.common.world.feature.raw.MeadowLakeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;

public class TerrainsFeatures {

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
