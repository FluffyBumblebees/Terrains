package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getUniqueMapleFeatures;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.*;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class PumpkinRidges {
    public static final Biome PUMPKIN_RIDGES = new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(0xB2B207)
                    .foliageColor(0xB2B207)
                    .build()
            )
            .temperature(0.9F)
            .downfall(0F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(GREEN));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(ORANGE));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(YELLOW));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(RED));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getUniqueMapleFeatures().FALLEN_MAPLE_TRUNK);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrainsPlacedFeatures.SCATTERED_PUMPKINS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
