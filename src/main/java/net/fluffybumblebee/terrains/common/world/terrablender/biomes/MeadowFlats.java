package net.fluffybumblebee.terrains.common.world.terrablender.biomes;

import net.fluffybumblebee.terrains.util.MeadowDefaults;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.*;


public class MeadowFlats {
    public static final Biome MEADOW_FLATS = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .category(Biome.Category.PLAINS)
            .spawnSettings(spawnSettings())
            .effects(MeadowDefaults.EFFECTS)
            .temperature(0.5F)
            .downfall(0.8F)
            .build();

    private static GenerationSettings generationSettings() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        MeadowDefaults.ACCESS.basicFeatures(builder);
        MeadowDefaults.addLargeMeadowFeatures(builder);
        addPlainsTallGrass(builder);
        addForestFlowers(builder);
        addDefaultGrass(builder);
        addDefaultOres(builder);
        addDefaultDisks(builder);
        addMeadowFlowers(builder);
        addExtraDefaultFlowers(builder);
        addEmeraldOre(builder);
        addInfestedStone(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        DefaultBiomeFeatures.addFarmAnimals(builder);
        return builder.build();
    }
}
