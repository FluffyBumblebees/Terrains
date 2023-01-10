package net.fluffybumblebee.terrains.common.default_abstract.block;

import net.fluffybumblebee.terrains.util.STUtil;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class LeavesBlock extends net.minecraft.block.LeavesBlock {
    public LeavesBlock() {
        super(Settings
                .copy(Blocks.OAK_LEAVES)
                .nonOpaque()
                .ticksRandomly()
                .strength(0.2F)
                .sounds(BlockSoundGroup.GRASS)
                .suffocates(STUtil::never)
                .allowsSpawning(STUtil::canSpawnOnLeaves)
                .blockVision(STUtil::never));
    }
}
