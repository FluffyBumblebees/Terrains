package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodTrapDoor extends TrapdoorBlock {
    public MFWoodTrapDoor() {
        super(Settings.copy(Blocks.OAK_TRAPDOOR).sounds(BlockSoundGroup.WOOD).nonOpaque());

    }
}
