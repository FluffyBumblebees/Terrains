package net.fluffybumblebee.terrains.util.registration.category;

import net.fluffybumblebee.terrains.TerrainsDefaults;
import net.fluffybumblebee.terrains.common.default_abstract.block.LeavesBlock;
import net.fluffybumblebee.terrains.common.default_abstract.block.SaplingBlock;
import net.fluffybumblebee.terrains.common.default_abstract.block.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.common.registry.world.feature.tree.STSaplingGenerator;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import static net.fluffybumblebee.terrains.util.TreeRegistration.*;
import static net.fluffybumblebee.terrains.util.registration.block.BlockBuilder.buildBlock;

public class StainedCollection <E extends Enum<?>> {
    public final BlockBuilder<?>[] ALL_BLOCKS;
    public final BlockBuilder<LeavesBlock> LEAVES;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE_BEES;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FAT_TREE_BEES;
    public final BlockBuilder<SaplingBlock> SAPLING;
    public final BlockBuilder<ShortenedFlowerPotBlock> POTTED_SAPLING;
    public final RegistryEntry<PlacedFeature> TREE_PLACED;

    public StainedCollection(E type) {
        String colour = type.name().toLowerCase();

        LEAVES = buildBlock(new LeavesBlock(), colour + "_leaves");
        var block = LEAVES.getBlock();

        TREE = defOak(colour, block);
        TREE_BEES = defOakBees(colour + "_bees", block);
        FAT_TREE = fatOak("fat_" + colour, block);
        FAT_TREE_BEES = fatOakBees("fat_" + colour + "_bees", block);

        SAPLING = buildBlock(new SaplingBlock(new STSaplingGenerator(
                () -> TREE,
                () -> TREE_BEES,
                () -> FAT_TREE,
                () -> FAT_TREE_BEES
        )),   colour + "_sapling");
        var sapling = SAPLING.getBlock();

        POTTED_SAPLING = new BlockBuilder<>(new ShortenedFlowerPotBlock(sapling), TerrainsDefaults.getIdentifier("potted_" + colour +  "_sapling")).build();

        ALL_BLOCKS = new BlockBuilder[] {
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

        generateTree(TREE_PLACED);
    }
}
