package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.StainedTreeAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.*;


public class StainedForest {
    public static final Biome STAINED_FOREST = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(0x2CB2B2)
                    .foliageColor(0x2CB2B2).build()
            )
            .temperature(0.5F)
            .downfall(0.5F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
        addDefaultFlowers(builder);
        addForestFlowers(builder);
        addDefaultOres(builder);
        addDefaultDisks(builder);
        StainedTreeAccess.addStainedTrees(builder);
        addDefaultGrass(builder);
        addForestGrass(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
