package net.fluffybumblebee.terrains.common.instances.block.wood_set;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodFenceGate extends FenceGateBlock {
    public WoodFenceGate() {
        super(Settings.copy(Blocks.OAK_FENCE_GATE).sounds(BlockSoundGroup.WOOD));
    }
}
