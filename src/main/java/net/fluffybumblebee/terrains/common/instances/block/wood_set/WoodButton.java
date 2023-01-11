package net.fluffybumblebee.terrains.common.instances.block.wood_set;

import net.minecraft.block.Blocks;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodButton extends WoodenButtonBlock {
    public WoodButton() {
        super(Settings.copy(Blocks.OAK_BUTTON).sounds(BlockSoundGroup.WOOD));

    }
}
