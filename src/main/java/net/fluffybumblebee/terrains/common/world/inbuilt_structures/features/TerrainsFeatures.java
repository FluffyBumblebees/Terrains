package net.fluffybumblebee.terrains.common.world.inbuilt_structures.features;

import net.fluffybumblebee.terrains.common.world.inbuilt_structures.features.component.MeadowLakeFeature;
import net.fluffybumblebee.terrains.common.world.inbuilt_structures.features.component.ScatteredBlockFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;

import static net.fluffybumblebee.terrains.util.registration.world.feature.TerrainsFeatureRegistrar.registerFeature;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;

public class TerrainsFeatures {

    @SuppressWarnings("deprecation")
    public static final Feature<LakeFeature.Config> LAKE = registerFeature(
            getNamespaceVar() + "meadow_lake",
            new MeadowLakeFeature(LakeFeature.Config.CODEC)
    );

    public static final Feature<RandomPatchFeatureConfig> SCATTERED_BLOCk = registerFeature(
            getNamespaceVar() + "scattered_block",
            new ScatteredBlockFeature(RandomPatchFeatureConfig.CODEC)
    );



}
