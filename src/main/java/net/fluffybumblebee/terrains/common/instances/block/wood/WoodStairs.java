package net.fluffybumblebee.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodStairs extends StairsBlock implements Wood {
    public WoodStairs() {
        super(Blocks.OAK_LOG.getDefaultState(), Settings.copy(Blocks.OAK_STAIRS).sounds(BlockSoundGroup.WOOD));
    }
}
