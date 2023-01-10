package net.fluffybumblebee.maple_forest.blocks.custom.tree;

import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWood extends PillarBlock {
    public MFWood() {
        super(Settings.copy(Blocks.OAK_WOOD).sounds(BlockSoundGroup.WOOD));
    }
}
