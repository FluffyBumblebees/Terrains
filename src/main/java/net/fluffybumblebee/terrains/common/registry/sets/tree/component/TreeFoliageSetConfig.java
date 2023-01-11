package net.fluffybumblebee.terrains.common.registry.sets.tree.component;

import net.fluffybumblebee.terrains.common.instances.block.plant.LeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.SaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;

public final class TreeFoliageSetConfig<E extends Enum<?>, S extends SaplingGenerator> implements FeatureRegistrar<BlockSet<?>> {
    private final BlockSet<?>[] ALL_BLOCKS;
    public final BlockSet<LeavesBlock> LEAVES;
    public final BlockSet<SaplingBlock> SAPLING;
    public final BlockSet<ShortenedFlowerPotBlock> POTTED_SAPLING;

    public TreeFoliageSetConfig(E type, AfterLeaves<S> afterLeaves) {
        final String colour = type.name().toLowerCase();

        LEAVES = buildBlock(new LeavesBlock(), colour + "_leaves");

        SAPLING = buildBlock(new SaplingBlock(afterLeaves.register(LEAVES.BLOCK)),   colour + "_sapling");
        var sapling = SAPLING.BLOCK;

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ShortenedFlowerPotBlock(sapling),
                getIdentifier("potted_" + colour +  "_sapling")
        ).addBlockItem().build();

        ALL_BLOCKS = new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        };
    }

    @Override
    public BlockSet<?>[] getAll() {
        return ALL_BLOCKS;
    }

    public interface AfterLeaves <S extends SaplingGenerator> {
        S register(Block block);
    }
}
