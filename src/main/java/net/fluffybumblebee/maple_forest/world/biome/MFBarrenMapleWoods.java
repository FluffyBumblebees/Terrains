package net.fluffybumblebee.maple_forest.world.biome;

import net.fluffybumblebee.maple_forest.init.MFBiomes;
import net.fluffybumblebee.maple_forest.world.feature.MFPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;


public class MFBarrenMapleWoods {
    private static final int biomeColour =  0xE69600;
    public static final Biome BARREN_MAPLE_WOODS = new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(MFBiomes.createDefaultBiomeEffects()
                    .grassColor(biomeColour)
                    .foliageColor(biomeColour)
                    .build()
            )
            .temperature(0.9F)
            .downfall(0F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        MFBiomes.addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.ORANGE_MAPLE_PLACED_NO_BEES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.YELLOW_MAPLE_PLACED_NO_BEES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.BROWN_MAPLE_PLACED_NO_BEES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.MAPLE_TRUNK);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.DEAD_MAPLE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFVegetationPlacedFeatures.PATCH_DEAD_BUSH);
        DefaultBiomeFeatures.addSavannaGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = MFBiomes.createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        DefaultBiomeFeatures.addDesertMobs(builder);
        return builder.build();
    }
}