package net.fluffybumblebee.terrains.common.registry.sets.tree.component;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredLeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredSaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;

public final class TreeFoliageSetConfig<E extends Enum<?>, S extends SaplingGenerator> implements FeatureRegistrar<BlockSet<?>> {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final BlockSet<ConfiguredLeavesBlock> LEAVES;
    public final BlockSet<ConfiguredSaplingBlock> SAPLING;
    public final BlockSet<ConfiguredFlowerPotBlock> POTTED_SAPLING;

    public TreeFoliageSetConfig(E type, AfterLeaves<S> afterLeaves) {
        final String leafType = type.name().toLowerCase();
        ALL_BLOCKS = new ArrayList<>();
        LEAVES = buildBlock(new ConfiguredLeavesBlock(), leafType + "_leaves");
        SAPLING = buildBlock(new ConfiguredSaplingBlock(afterLeaves.register(LEAVES.BLOCK)),   leafType + "_sapling");

        var sapling = SAPLING.BLOCK;
        POTTED_SAPLING = new BlockSet.Builder<>(
                new ConfiguredFlowerPotBlock(sapling),
                getIdentifier("potted_" + leafType +  "_sapling")
        ).addBlockItem(new Item.Settings()).build();

        addAll(new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        });
    }

    @Override
    public List<BlockSet<?>> getAll() {
        return ALL_BLOCKS;
    }

    public interface AfterLeaves <S extends SaplingGenerator> {
        S register(Block block);
    }
}
