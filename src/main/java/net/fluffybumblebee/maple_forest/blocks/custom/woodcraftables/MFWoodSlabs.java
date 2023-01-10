package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodSlabs extends SlabBlock {
    public MFWoodSlabs() {
        super(Settings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.WOOD));
    }
}
