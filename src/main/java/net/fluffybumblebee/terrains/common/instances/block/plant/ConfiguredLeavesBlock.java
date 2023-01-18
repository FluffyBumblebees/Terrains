package net.fluffybumblebee.terrains.common.instances.block.plant;

import net.fluffybumblebee.terrains.util.predicates.BlockPredicates;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;

public class ConfiguredLeavesBlock extends LeavesBlock {
    public ConfiguredLeavesBlock() {
        super(Settings
                .copy(Blocks.OAK_LEAVES)
                .nonOpaque()
                .ticksRandomly()
                .strength(0.2F)
                .suffocates(BlockPredicates::never)
                .allowsSpawning(BlockPredicates::canSpawnOnLeaves)
                .blockVision(BlockPredicates::never));
    }
}
