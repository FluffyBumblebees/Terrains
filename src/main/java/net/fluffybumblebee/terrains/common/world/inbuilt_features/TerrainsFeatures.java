package net.fluffybumblebee.terrains.common.world.inbuilt_features;

import net.fluffybumblebee.terrains.common.world.inbuilt_features.raw.MeadowLakeFeature;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.raw.ScatteredBlockFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;

public class TerrainsFeatures {

    @SuppressWarnings("deprecation")
    public static final Feature<MeadowLakeFeature.Config> LAKE = register(
            getNamespaceVar() + "meadow_lake",
            new MeadowLakeFeature(LakeFeature.Config.CODEC)
    );

    public static final Feature<RandomPatchFeatureConfig> SCATTERED_BLOCk = register(
            getNamespaceVar() + "scattered_block",
            new ScatteredBlockFeature(RandomPatchFeatureConfig.CODEC)
    );


    @SuppressWarnings("SameParameterValue")
    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }
}
