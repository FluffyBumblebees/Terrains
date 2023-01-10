package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodFence extends FenceBlock {
    public MFWoodFence() {
        super(Settings.copy(Blocks.OAK_FENCE).sounds(BlockSoundGroup.WOOD));
    }

}
