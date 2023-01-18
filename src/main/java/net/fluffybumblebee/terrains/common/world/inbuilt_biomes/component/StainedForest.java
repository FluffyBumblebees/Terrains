package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.StainedTreeAccess.addStainedTrees;
import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry.WARM_WATER;
import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.*;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class StainedForest {
    private static final int BIOME_COLOUR =  0x2CB2B2;

    public static final Biome STAINED_FOREST = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(BIOME_COLOUR)
                    .foliageColor(BIOME_COLOUR)
                    .waterColor(WARM_WATER)
                    .waterFogColor(WARM_WATER)
                    .build())
            .temperature(0.5F)
            .downfall(0.5F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addDefaultFeatures(builder);
        addStainedTrees(builder);
        addVegetalFeatures(builder, PATCH_GRASS_PLAINS, PATCH_GRASS_FOREST, FLOWERS_DEFAULT, FLOWERS_FOREST,
                FLOWERS_FLOWER_FOREST, BONEMEAL);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
