package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodFenceGate extends FenceGateBlock {
    public MFWoodFenceGate() {
        super(Settings.copy(Blocks.OAK_FENCE_GATE).sounds(BlockSoundGroup.WOOD));
    }
}
