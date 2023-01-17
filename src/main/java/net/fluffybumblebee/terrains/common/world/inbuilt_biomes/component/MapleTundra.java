package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.BLUE;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.WHITE;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class MapleTundra {
    public static final Biome MAPLE_TUNDRA = new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .generationSettings(generationSettings())
            .category(Biome.Category.TAIGA)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(0x00703C)
                    .foliageColor(0x00703C).build()
            )
            .temperature(-0.5F)
            .downfall(0.25F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(WHITE));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(BLUE));
        DefaultBiomeFeatures.addTaigaGrass(builder);
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
