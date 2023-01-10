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
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;


public class MFMapleBlossom {
    public static final Biome MAPLE_BLOSSOM = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(MFBiomes.createDefaultBiomeEffects()
                    .grassColor(0x78BE21)
                    .foliageColor(0x78BE21).build()
            )
            .temperature(0.8F)
            .downfall(0.5F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        MFBiomes.addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.GREEN_MAPLE_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.RED_MAPLE_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFVegetationPlacedFeatures.FLOWER_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MFVegetationPlacedFeatures.PATCH_GRASS_JUNGLE);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = MFBiomes.createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
