package net.stockieslad.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodTrapDoor extends TrapdoorBlock implements Wood {
    public WoodTrapDoor() {
        super(Settings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.WOOD).nonOpaque());

    }
}
