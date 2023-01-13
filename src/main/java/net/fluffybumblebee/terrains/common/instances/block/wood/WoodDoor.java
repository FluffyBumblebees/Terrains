package net.fluffybumblebee.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodDoor extends DoorBlock implements Wood {
    public WoodDoor() {
        super(Settings.copy(Blocks.OAK_DOOR).sounds(BlockSoundGroup.WOOD));
    }
}
