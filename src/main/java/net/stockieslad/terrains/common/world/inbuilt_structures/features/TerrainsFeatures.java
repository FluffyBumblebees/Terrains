package net.stockieslad.terrains.common.world.inbuilt_structures.features;

import net.feltmc.abstractium.init.AbstractiumCommon;
import net.feltmc.abstractium.library.common.registration.AbstractRegistrar;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.MeadowLakeFeature;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.ScatteredBlockFeature;

import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

public class TerrainsFeatures {
    private static final AbstractRegistrar REGISTRAR = AbstractiumCommon.COMMON_ABSTRACTION_HANDLER.abstraction.getRegistrar();

    @SuppressWarnings("deprecation")
    public static final Feature<LakeFeature.Config> LAKE = REGISTRAR.registerFeature(
            getIdentifier("meadow_lake"),
            new MeadowLakeFeature(LakeFeature.Config.CODEC)
    );

    public static final Feature<RandomPatchFeatureConfig> SCATTERED_BLOCk = REGISTRAR.registerFeature(
            getIdentifier("scattered_block"),
            new ScatteredBlockFeature(RandomPatchFeatureConfig.CODEC)
    );



}
