package net.stockieslad.terrains.common.world.inbuilt_biomes.component;

import net.stockieslad.terrains.common.world.inbuilt_biomes.MeadowDefaults;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.GREEN;
import static net.stockieslad.terrains.common.world.inbuilt_structures.features.TerrainsPlacedFeatures.*;
import static net.stockieslad.terrains.util.registration.world.biome.BiomeRegistryTools.addDefaultFeatures;
import static net.stockieslad.terrains.util.registration.world.biome.BiomeRegistryTools.addVegetalFeatures;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addFarmAnimals;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addMelons;


public class MapleMeadow {
    public static final Biome MAPLE_MEADOW = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .spawnSettings(spawnSettings())
            .effects(MeadowDefaults.EFFECTS)
            .temperature(0.5F)
            .downfall(0.8F)
            .build();

    private static GenerationSettings generationSettings() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addDefaultFeatures(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(GREEN));
        addVegetalFeatures(builder, TREES_OAK_BUSH_UNCOMMON, SCATTERED_MELON, PATCH_GRASS_JUNGLE, PATCH_GRASS_FOREST,
                PATCH_GRASS_TALL, FLOWERS_FOREST, FLOWERS_MEADOW, FLOWERS_PLAINS, FLOWER_WARM);
        addMelons(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        addFarmAnimals(builder);
        return builder.build();
    }
}
