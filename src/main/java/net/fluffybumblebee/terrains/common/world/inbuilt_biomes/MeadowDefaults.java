package net.fluffybumblebee.terrains.common.world.inbuilt_biomes;

import net.fluffybumblebee.terrains.mixins.intercomm.OverworldBiomeCreatorMixinInterface;
import net.minecraft.client.sound.MusicType;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;

import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.*;

public class MeadowDefaults {
    private static int getSkyColor(@SuppressWarnings("SameParameterValue") float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static OverworldBiomeCreatorMixinInterface ACCESS;

    public static final BiomeEffects EFFECTS =
        new BiomeEffects.Builder()
            .waterColor(937679)
            .foliageColor(0x63B26D)
            .grassColor(0x63B26D)
            .waterFogColor(329011)
            .fogColor(12638463)
            .skyColor(getSkyColor(0.5F))
            .moodSound(BiomeMoodSound.CAVE)
            .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_MEADOW))
            .build();

    public static void addLargeMeadowFeatures(GenerationSettings.Builder generationBuilder) {
        generationBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MEADOW_LAKE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HUGE_BROWN_MUSHROOM);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HUGE_RED_MUSHROOM);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_SPRUCE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_SPRUCE_SPARSE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_BUSH);
    }
}
