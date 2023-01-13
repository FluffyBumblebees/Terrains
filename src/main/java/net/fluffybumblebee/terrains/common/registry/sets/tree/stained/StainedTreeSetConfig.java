package net.fluffybumblebee.terrains.common.registry.sets.tree.stained;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.TreeFoliageSetConfig;
import net.fluffybumblebee.terrains.common.world.feature.raw.StainedSaplingGenerator;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.SetRegistrar;
import net.minecraft.item.Item;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.*;

public final class StainedTreeSetConfig<E extends Enum<?>> implements SetRegistrar {
    private final List<BlockSet<?>> ALL_BLOCKS;
    private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE;
    private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE_BEES;
    private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE;
    private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE_BEES;
    public final TreeFoliageSetConfig<E, StainedSaplingGenerator> FOLIAGE;
    public final RegistryEntry<PlacedFeature> TREE_PLACED;

    public StainedTreeSetConfig(E type) {
        final String colour = type.name().toLowerCase();
        ALL_BLOCKS = new ArrayList<>();

        FOLIAGE = new TreeFoliageSetConfig<>(type, block -> {
            TREE = defOak(colour, block);
            TREE_BEES = defOakBees(colour + "_bees", block);
            FAT_TREE = fatOak("fat_" + colour, block);
            FAT_TREE_BEES = fatOakBees("fat_" + colour + "_bees", block);
            return new StainedSaplingGenerator(
                    () -> TREE,
                    () -> TREE_BEES,
                    () -> FAT_TREE,
                    () -> FAT_TREE_BEES
            );
        });

        var sapling = FOLIAGE.SAPLING.BLOCK;
        addAllBlocks(new BlockSet[] {
                FOLIAGE.LEAVES,
                FOLIAGE.SAPLING,
                FOLIAGE.POTTED_SAPLING
        });

        TREE_PLACED = createPlaced(
                TREE,
                TREE_BEES,
                FAT_TREE,
                FAT_TREE_BEES,
                sapling,
                colour
        );
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }

    @Override
    public void generationEvent() {
        generateFeature(TREE_PLACED, BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION);

    }
}