package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;

public class TerrainsBiomeTags extends FabricTagProvider.DynamicRegistryTagProvider<Biome> {
    public TerrainsBiomeTags(FabricDataGenerator dataGenerator) {
        super(dataGenerator, BuiltinRegistries.BIOME.getKey(), "worldgen/biome", "Biome Tags");
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.IN_OVERWORLD.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.LUSH_MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA)
                .add(TerrainsBiomeRegistry.MEADOW_FLATS)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES)
                .add(TerrainsBiomeRegistry.STAINED_FOREST);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_FOREST.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES)
                .add(TerrainsBiomeRegistry.STAINED_FOREST);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_TAIGA.id()))
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.SAVANNA.id()))
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.FOREST.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES)
                .add(TerrainsBiomeRegistry.STAINED_FOREST);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.PLAINS.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.MAPLE_MEADOW)
                .add(TerrainsBiomeRegistry.MEADOW_FLATS);


        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.CLIMATE_TEMPERATE.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.LUSH_MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_BLOSSOM);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.CLIMATE_HOT.id()))
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), ConventionalBiomeTags.CLIMATE_DRY.id()))
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IGLOO_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.MINESHAFT_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.LUSH_MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA)
                .add(TerrainsBiomeRegistry.MEADOW_FLATS)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES)
                .add(TerrainsBiomeRegistry.STAINED_FOREST);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.PILLAGER_OUTPOST_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.LUSH_MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA)
                .add(TerrainsBiomeRegistry.MEADOW_FLATS);


        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.STRONGHOLD_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_FOREST)
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.LUSH_MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_BLOSSOM)
                .add(TerrainsBiomeRegistry.MAPLE_GHOST_FOREST)
                .add(TerrainsBiomeRegistry.MAPLE_TUNDRA)
                .add(TerrainsBiomeRegistry.MEADOW_FLATS)
                .add(TerrainsBiomeRegistry.PUMPKIN_RIDGES)
                .add(TerrainsBiomeRegistry.STAINED_FOREST);

        this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE.id()))
                .add(TerrainsBiomeRegistry.JACARANDA_PLAINS)
                .add(TerrainsBiomeRegistry.MAPLE_MEADOW);

    }
}
