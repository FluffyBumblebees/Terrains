package net.fluffybumblebee.maple_forest.blocks.custom.tree;

import net.fluffybumblebee.terrains.util.predicates.BlockPredicates;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFLeaves extends LeavesBlock {
    public MFLeaves() {
        super(Settings
                .copy(Blocks.OAK_LEAVES)
                .nonOpaque()
                .ticksRandomly()
                .strength(0.2F)
                .sounds(BlockSoundGroup.GRASS)
                .suffocates(BlockPredicates::never)
                .allowsSpawning(BlockPredicates::canSpawnOnLeaves)
                .blockVision(BlockPredicates::never));
    }
}
