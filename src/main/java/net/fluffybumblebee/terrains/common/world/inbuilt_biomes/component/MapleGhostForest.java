package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getUniqueMapleFeatures;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.*;
import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.PATCH_DEAD_BUSH;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class MapleGhostForest {
    private static final int BIOME_COLOUR =  0xE69600;
    public static final Biome MAPLE_GHOST_FOREST = new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .generationSettings(generationSettings())
            .category(Biome.Category.FOREST)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(BIOME_COLOUR)
                    .foliageColor(BIOME_COLOUR)
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
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(ORANGE));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(YELLOW));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(BROWN));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getUniqueMapleFeatures().FALLEN_MAPLE_TRUNK);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getUniqueMapleFeatures().DEAD_MAPLE_TREE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_DEAD_BUSH);
        DefaultBiomeFeatures.addSavannaGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        DefaultBiomeFeatures.addDesertMobs(builder);
        return builder.build();
    }
}