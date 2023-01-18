package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.BLUE;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.WHITE;
import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.*;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class MapleTundra {
    private static final int BIOME_COLOUR =  0x00703C;

    public static final Biome MAPLE_TUNDRA = new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .generationSettings(generationSettings())
            .category(Biome.Category.TAIGA)
            .spawnSettings(spawnSettings())
            .effects(createDefaultBiomeEffects()
                    .grassColor(BIOME_COLOUR)
                    .foliageColor(BIOME_COLOUR)
                    .build())
            .temperature(-0.5F)
            .downfall(0.25F)
            .build();

    private static GenerationSettings generationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addDefaultFeatures(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(WHITE));
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(BLUE));
        addVegetalFeatures(builder, PATCH_GRASS_TAIGA, PATCH_FERN_LARGE, PATCH_BERRY_BUSH_COMMON, TAIGA_MUSHROOM_RED,
                TAIGA_MUSHROOM_BROWN);
        return builder.build();
    }

    private static SpawnSettings spawnSettings(){
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
