package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodButton extends WoodenButtonBlock {
    public MFWoodButton() {
        super(Settings.copy(Blocks.OAK_BUTTON).sounds(BlockSoundGroup.WOOD));

    }
}
