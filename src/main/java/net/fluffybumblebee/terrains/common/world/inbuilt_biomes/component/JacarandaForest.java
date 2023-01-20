package net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

import static net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageAccess.accessDefaultPlacedFeature;
import static net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.FlowerTypes.LAVENDER;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeAccess.getFeature;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaTypes.PINK;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaTypes.PURPLE;
import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry.WARM_WATER;
import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.*;


public class JacarandaForest {
    private static final int BIOME_COLOUR = 0x96DD4F;

    public static final Biome JACARANDA_FOREST = new Biome.Builder()
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
            .temperature(0.8F)
            .downfall(0.5F)
            .build();

    private static GenerationSettings generationSettings() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addVegetalFeatures(builder,
                getFeature(PINK).COMMON_PLACED_TREE_BEES,
                getFeature(PURPLE).COMMON_PLACED_TREE_BEES,
                accessDefaultPlacedFeature(LAVENDER)
        );
        return builder.build();
    }

    private static SpawnSettings spawnSettings() {
        SpawnSettings.Builder builder = createDefaultSpawnSettings();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        return builder.build();
    }
}
