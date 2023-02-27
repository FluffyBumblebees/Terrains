package net.stockieslad.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodButton extends WoodenButtonBlock implements Wood {
    public WoodButton() {
        super(Settings.copy(Blocks.OAK_BUTTON).sounds(BlockSoundGroup.WOOD));

    }
}
