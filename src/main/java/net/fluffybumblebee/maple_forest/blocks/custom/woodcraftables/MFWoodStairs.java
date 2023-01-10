package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodStairs extends StairsBlock {
    public MFWoodStairs() {
        super(Blocks.OAK_LOG.getDefaultState(), Settings.copy(Blocks.OAK_STAIRS).sounds(BlockSoundGroup.WOOD));
    }
}
