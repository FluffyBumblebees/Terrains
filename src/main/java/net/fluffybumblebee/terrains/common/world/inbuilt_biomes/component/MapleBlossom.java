package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedBees;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.GREEN;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.RED;
import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry.WARM_WATER;
import static net.fluffybumblebee.terrains.common.world.inbuilt_features.TerrainsPlacedFeatures.*;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.*;


public class MapleBlossom {
    private static final int BIOME_COLOUR = 0x78BE21;

    public static final Biome MAPLE_BLOSSOM = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .generationSettings(generationSettings())
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

    private static GenerationSettings generationSettings() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addDefaultFlowers(builder);

        addVegetalFeatures(builder, getPlacedBees(RED), getPlacedBees(GREEN), PATCH_GRASS_FOREST, PATCH_GRASS_TALL,
                FLOWERS_DEFAULT, FLOWERS_FOREST, FLOWERS_FLOWER_FOREST, FLOWERS_MEADOW, FLOWER_WARM
        );
        addDefaultGrass(builder);
        addForestGrass(builder);
        addMeadowFlowers(builder);
        addJungleGrass(builder);
        return builder.build();
    }

    private static SpawnSettings spawnSettings() {
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
