package net.fluffybumblebee.terrains.common.instances.block.plant;

import net.fluffybumblebee.terrains.util.predicates.BlockPredicates;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ConfiguredLeavesBlock extends LeavesBlock {
    public ConfiguredLeavesBlock() {
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
