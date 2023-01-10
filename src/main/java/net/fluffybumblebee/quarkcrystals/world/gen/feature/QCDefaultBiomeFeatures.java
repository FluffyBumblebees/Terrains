package net.fluffybumblebee.quarkcrystals.world.gen.feature;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;

public class QCDefaultBiomeFeatures {
    public static void addQCGeodes(GenerationSettings.Builder builder) {
        addBlackGeode(builder);
        addBlueGeode(builder);
        addGreenGeode(builder);
        addIndigoGeode(builder);
        addOrangeGeode(builder);
        addRedGeode(builder);
        addPinkGeode(builder);
        addWhiteGeode(builder);
        addYellowGeode(builder);
        addCyanGeode(builder);
        addPurpleGeode(builder);
        addBrownGeode(builder);
        addRainbowGeode(builder);
        addDarkGeode(builder);
    }
    public static void addBlackGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.BLACK_GEODE_PLACED
        ).build();
    }
    public static void addBlueGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.BLUE_GEODE_PLACED
        ).build();
    }
    public static void addGreenGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.GREEN_GEODE_PLACED
        ).build();
    }
    public static void addIndigoGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.INDIGO_GEODE_PLACED
        ).build();
    }
    public static void addOrangeGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.ORANGE_GEODE_PLACED
        ).build();
    }
    public static void addRedGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.RED_GEODE_PLACED
        ).build();
    }
    public static void addPinkGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.PINK_GEODE_PLACED
        ).build();
    }
    public static void addWhiteGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.WHITE_GEODE_PLACED
        ).build();
    }
    public static void addYellowGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.YELLOW_GEODE_PLACED
        ).build();
    }
    public static void addCyanGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.CYAN_GEODE_PLACED
        ).build();
    }
    public static void addPurpleGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.PURPLE_GEODE_PLACED
        ).build();
    }
    public static void addBrownGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.BROWN_GEODE_PLACED
        ).build();
    }
    public static void addRainbowGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.RAINBOW_GEODE_PLACED
        ).build();
    }
    public static void addDarkGeode(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                QCUndergroundPlacedFeatures.DARK_GEODE_PLACED
        ).build();
    }
}
