package net.fluffybumblebee.terrains.common.instances.block.wood;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class WoodFence extends FenceBlock implements Wood {
    public WoodFence() {
        super(Settings.copy(Blocks.OAK_FENCE).sounds(BlockSoundGroup.WOOD));
    }

}
