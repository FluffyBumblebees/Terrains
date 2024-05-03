package net.stockieslad.terrains.common.world.inbuilt_biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.stockieslad.abstractium.util.dynamic.Mimic;
import net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.*;
import static net.stockieslad.terrains.common.registry.sets.foliage.flower.FlowerFoliageAccess.accessDefaultPlacedFeature;
import static net.stockieslad.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.FlowerTypes.LAVENDER;
import static net.stockieslad.terrains.common.registry.sets.tree.primitive.stained.StainedTreeAccess.addStainedTrees;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeAccess.getFeature;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeAccess.getPlacedBees;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaTypes.PINK;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaTypes.PURPLE;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getPlacedNoBees;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeAccess.getUniqueMapleFeatures;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes.*;
import static net.stockieslad.terrains.common.world.inbuilt_biomes.MeadowDefaults.EFFECTS;
import static net.stockieslad.terrains.common.world.inbuilt_biomes.MeadowDefaults.addLargeMeadowFeatures;
import static net.stockieslad.terrains.common.world.inbuilt_structures.features.TerrainsPlacedFeatures.*;
import static net.stockieslad.terrains.util.registration.world.biome.BiomeRegistryTools.*;

public class TerrainsBiomes {

    public static final Biome
            JACARANDA_FOREST,
            JACARANDA_PLAINS,
            LUSH_MAPLE_BLOSSOM,
            MAPLE_BLOSSOM,
            MAPLE_GHOST_FOREST,
            MAPLE_MEADOW,
            MAPLE_TUNDRA,
            MEADOW_FLATS,
            PUMPKIN_RIDGES,
            STAINED_FOREST;

    public static final Mimic
            JACARANDA_FOREST_KEY,
            JACARANDA_PLAINS_KEY,
            LUSH_MAPLE_BLOSSOM_KEY,
            MAPLE_BLOSSOM_KEY,
            MAPLE_GHOST_FOREST_KEY,
            MAPLE_MEADOW_KEY,
            MAPLE_TUNDRA_KEY,
            MEADOW_FLATS_KEY,
            PUMPKIN_RIDGES_KEY,
            STAINED_FOREST_KEY;

    static {
        {
            JACARANDA_FOREST = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(
                            addVegetalFeatures(new GenerationSettings.Builder(),
                                    getFeature(PINK).COMMON_PLACED_TREE_BEES,
                                    getFeature(PURPLE).COMMON_PLACED_TREE_BEES,
                                    accessDefaultPlacedFeature(LAVENDER)
                            ).build()
                    )
                    .category(Biome.Category.FOREST)
                    .spawnSettings(
                            defaultSpawnSettingsWithWolf()
                    )
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0x96DD4F)
                            .foliageColor(0x96DD4F)
                            .waterColor(WARM_WATER)
                            .waterFogColor(WARM_WATER)
                            .build())
                    .temperature(0.8F)
                    .downfall(0.5F)
                    .build();

            JACARANDA_FOREST_KEY = createRegistryKey("jacaranda_forest", JACARANDA_FOREST);
        }
        {
            JACARANDA_PLAINS = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(
                            addVegetalFeatures(
                                    new GenerationSettings.Builder(),
                                    getPlacedBees(PINK),
                                    getPlacedBees(PURPLE),
                                    accessDefaultPlacedFeature(LAVENDER)
                            ).build()
                    )
                    .category(Biome.Category.PLAINS)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0x96DD4F)
                            .foliageColor(0x96DD4F)
                            .waterColor(WARM_WATER)
                            .waterFogColor(WARM_WATER)
                            .build())
                    .temperature(0.8F)
                    .downfall(0.5F)
                    .build();

            JACARANDA_PLAINS_KEY = createRegistryKey("jacaranda_plains", JACARANDA_PLAINS);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFlowers(builder);
            addVegetalFeatures(builder, MapleTreeAccess.getPlacedBees(TYRIAN_PURPLE), MapleTreeAccess.getPlacedBees(PASTEL_GREEN), PATCH_GRASS_FOREST, PATCH_GRASS_TALL,
                    FLOWERS_DEFAULT, FLOWERS_FOREST, FLOWERS_FLOWER_FOREST, FLOWERS_MEADOW, FLOWER_WARM);
            addDefaultGrass(builder);
            addForestGrass(builder);
            addMeadowFlowers(builder);
            addJungleGrass(builder);

            LUSH_MAPLE_BLOSSOM = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(builder.build())
                    .category(Biome.Category.FOREST)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0xA3E529)
                            .foliageColor(0xA3E529)
                            .waterColor(WARM_WATER)
                            .waterFogColor(WARM_WATER)
                            .build())
                    .temperature(0.8F)
                    .downfall(0.5F)
                    .build();

            LUSH_MAPLE_BLOSSOM_KEY = createRegistryKey("lush_maple_blossom", LUSH_MAPLE_BLOSSOM);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFlowers(builder);
            addVegetalFeatures(builder, MapleTreeAccess.getPlacedBees(RED), MapleTreeAccess.getPlacedBees(GREEN), PATCH_GRASS_JUNGLE, PATCH_GRASS_FOREST,
                    PATCH_GRASS_TALL, FLOWERS_DEFAULT, FLOWERS_FOREST, FLOWERS_FLOWER_FOREST, FLOWERS_MEADOW, FLOWER_WARM
            );

            MAPLE_BLOSSOM = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(builder.build())
                    .category(Biome.Category.FOREST)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0x78BE21)
                            .foliageColor(0x78BE21)
                            .waterColor(WARM_WATER)
                            .waterFogColor(WARM_WATER)
                            .build())
                    .temperature(0.8F)
                    .downfall(0.5F)
                    .build();

            MAPLE_BLOSSOM_KEY = createRegistryKey("maple_blossom", MAPLE_BLOSSOM);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(ORANGE));
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(YELLOW));
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(BROWN));
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getUniqueMapleFeatures().FALLEN_MAPLE_TRUNK);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getUniqueMapleFeatures().DEAD_MAPLE_TREE);
            addVegetalFeatures(builder, PATCH_GRASS_FOREST, PATCH_GRASS_TALL, PATCH_GRASS_SAVANNA, PATCH_DEAD_BUSH);

            MAPLE_GHOST_FOREST = new Biome.Builder()
                    .precipitation(Biome.Precipitation.NONE)
                    .generationSettings(builder.build())
                    .category(Biome.Category.FOREST)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0xE69600)
                            .foliageColor(0xE69600)
                            .waterColor(HOT_WATER)
                            .waterFogColor(HOT_WATER)
                            .build()
                    )
                    .temperature(0.9F)
                    .downfall(0F)
                    .build();

            MAPLE_GHOST_FOREST_KEY = createRegistryKey("maple_ghost_forest", MAPLE_GHOST_FOREST);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(GREEN));
            addVegetalFeatures(builder, TREES_OAK_BUSH_UNCOMMON, SCATTERED_MELON, PATCH_GRASS_JUNGLE, PATCH_GRASS_FOREST,
                    PATCH_GRASS_TALL, FLOWERS_FOREST, FLOWERS_MEADOW, FLOWERS_PLAINS, FLOWER_WARM);
            addMelons(builder);

            MAPLE_MEADOW = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(builder.build())
                    .category(Biome.Category.PLAINS)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(MeadowDefaults.EFFECTS)
                    .temperature(0.5F)
                    .downfall(0.8F)
                    .build();

            MAPLE_MEADOW_KEY = createRegistryKey("maple_meadow", MAPLE_MEADOW);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(WHITE));
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, getPlacedNoBees(BLUE));
            addVegetalFeatures(builder, PATCH_GRASS_TAIGA, PATCH_FERN_LARGE, PATCH_BERRY_BUSH_COMMON, TAIGA_MUSHROOM_RED,
                    TAIGA_MUSHROOM_BROWN);

            MAPLE_TUNDRA = new Biome.Builder()
                    .precipitation(Biome.Precipitation.SNOW)
                    .generationSettings(builder.build())
                    .category(Biome.Category.TAIGA)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0x00A54A)
                            .foliageColor(0x00A54A)
                            .waterColor(COLD_WATER)
                            .waterFogColor(COLD_WATER)
                            .build())
                    .temperature(-0.5F)
                    .downfall(0.25F)
                    .build();

            MAPLE_TUNDRA_KEY = createRegistryKey("maple_tundra", MAPLE_TUNDRA);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            addLargeMeadowFeatures(builder);
            addVegetalFeatures(builder, PATCH_GRASS_JUNGLE, PATCH_GRASS_FOREST, PATCH_GRASS_TALL, FLOWERS_DEFAULT,
                    FLOWERS_FOREST, FLOWERS_MEADOW, FLOWER_WARM, PATCH_BERRY_BUSH_COMMON
            );

            MEADOW_FLATS = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(builder.build())
                    .category(Biome.Category.PLAINS)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(EFFECTS)
                    .temperature(0.5F)
                    .downfall(0.8F)
                    .build();

            MEADOW_FLATS_KEY = createRegistryKey("meadow_flats", MEADOW_FLATS);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            addVegetalFeatures(builder, getPlacedNoBees(GREEN), getPlacedNoBees(ORANGE), getPlacedNoBees(YELLOW),
                    getPlacedNoBees(RED), getUniqueMapleFeatures().FALLEN_MAPLE_TRUNK, SCATTERED_PUMPKINS,
                    PATCH_GRASS_FOREST, PATCH_GRASS_TALL, PATCH_GRASS_SAVANNA
            );

            PUMPKIN_RIDGES = new Biome.Builder()
                    .precipitation(Biome.Precipitation.NONE)
                    .generationSettings(builder.build())
                    .category(Biome.Category.FOREST)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0xB2B207)
                            .foliageColor(0xB2B207)
                            .waterColor(HOT_WATER)
                            .waterFogColor(HOT_WATER)
                            .build())
                    .temperature(0.9F)
                    .downfall(0F)
                    .build();

            PUMPKIN_RIDGES_KEY = createRegistryKey("pumpkin_ridges", PUMPKIN_RIDGES);
        }
        {
            GenerationSettings.Builder builder = new GenerationSettings.Builder();
            addDefaultFeatures(builder);
            addStainedTrees(builder);
            addVegetalFeatures(builder, PATCH_GRASS_PLAINS, PATCH_GRASS_FOREST, FLOWERS_DEFAULT, FLOWERS_FOREST,
                    FLOWERS_FLOWER_FOREST, BONEMEAL);

            STAINED_FOREST = new Biome.Builder()
                    .precipitation(Biome.Precipitation.RAIN)
                    .generationSettings(builder.build())
                    .category(Biome.Category.FOREST)
                    .spawnSettings(defaultSpawnSettingsWithWolf())
                    .effects(createDefaultBiomeEffects()
                            .grassColor(0x2CB2B2)
                            .foliageColor(0x2CB2B2)
                            .waterColor(WARM_WATER)
                            .waterFogColor(WARM_WATER)
                            .build())
                    .temperature(0.5F)
                    .downfall(0.5F)
                    .build();

            STAINED_FOREST_KEY = createRegistryKey("stained_forest", STAINED_FOREST);
        }
    }

    public static void init() {
    }
}
