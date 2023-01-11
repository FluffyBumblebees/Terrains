package net.fluffybumblebee.terrains.common.instances.block.wood_set;

import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodTrapDoor extends TrapdoorBlock {
    public WoodTrapDoor() {
        super(Settings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.WOOD).nonOpaque());

    }
}
