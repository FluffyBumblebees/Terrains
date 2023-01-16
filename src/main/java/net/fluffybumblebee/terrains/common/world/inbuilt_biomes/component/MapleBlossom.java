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

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.GREEN;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.RED;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class MapleBlossom {
    public static final Biome MAPLE_BLOSSOM = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(0x78BE21)
                    .foliageColor(0x78BE21).build()
            )
            .temperature(0.8F)
            .downfall(0.5F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedBees(GREEN));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedBees(RED));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrainsPlacedFeatures.FLOWER_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrainsPlacedFeatures.PATCH_GRASS_JUNGLE);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
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
