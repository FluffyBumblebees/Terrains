package net.fluffybumblebee.terrains.common.registry.sets.stained;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.instances.block.LeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.SaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.common.world.feature.raw.StainedSaplingGenerator;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.*;

public class StainedTreeSetConfig<E extends Enum<?>> implements FeatureRegistrar {
    private final BlockSet<?>[] ALL_BLOCKS;
    public final BlockSet<LeavesBlock> LEAVES;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE_BEES;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE_BEES;
    public final BlockSet<SaplingBlock> SAPLING;
    public final BlockSet<ShortenedFlowerPotBlock> POTTED_SAPLING;
    public final RegistryEntry<PlacedFeature> TREE_PLACED;

    public StainedTreeSetConfig(E type) {
        String colour = type.name().toLowerCase();

        LEAVES = buildBlock(new LeavesBlock(), colour + "_leaves");
        var block = LEAVES.block;

        TREE = defOak(colour, block);
        TREE_BEES = defOakBees(colour + "_bees", block);
        FAT_TREE = fatOak("fat_" + colour, block);
        FAT_TREE_BEES = fatOakBees("fat_" + colour + "_bees", block);

        SAPLING = buildBlock(new SaplingBlock(new StainedSaplingGenerator(
                () -> TREE,
                () -> TREE_BEES,
                () -> FAT_TREE,
                () -> FAT_TREE_BEES
        )),   colour + "_sapling");
        var sapling = SAPLING.block;

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ShortenedFlowerPotBlock(sapling),
                getIdentifier("potted_" + colour +  "_sapling")
        ).addBlockItem().build();

        ALL_BLOCKS = new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        };

        TREE_PLACED = createPlaced(
                TREE,
                TREE_BEES,
                FAT_TREE,
                FAT_TREE_BEES,
                sapling,
                colour
        );
        generateFeature(TREE_PLACED, BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION);
    }

    @Override
    public BlockSet<?>[] getAllBlocks() {
        return ALL_BLOCKS;
    }
}
