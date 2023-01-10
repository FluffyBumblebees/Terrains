package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodDoor extends DoorBlock {
    public MFWoodDoor() {
        super(Settings.copy(Blocks.OAK_DOOR).sounds(BlockSoundGroup.WOOD));
    }
}
