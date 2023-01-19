package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.MeadowDefaults.EFFECTS;
import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.MeadowDefaults.addLargeMeadowFeatures;
import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.*;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.addDefaultFeatures;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.addVegetalFeatures;


public class MeadowFlats {
    public static final Biome MEADOW_FLATS = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .spawnSettings(spawnSettings())
            .effects(EFFECTS)
            .temperature(0.5F)
            .downfall(0.8F)
            .build();

    private static GenerationSettings generationSettings() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addDefaultFeatures(builder);
        addLargeMeadowFeatures(builder);
        addVegetalFeatures(builder, PATCH_GRASS_JUNGLE, PATCH_GRASS_FOREST, PATCH_GRASS_TALL, FLOWERS_DEFAULT,
                FLOWERS_FOREST, FLOWERS_MEADOW, FLOWER_WARM, PATCH_BERRY_BUSH_COMMON
        );
        return builder.build();
    }

    private static SpawnSettings spawnSettings() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        DefaultBiomeFeatures.addFarmAnimals(builder);
        return builder.build();
    }
}
