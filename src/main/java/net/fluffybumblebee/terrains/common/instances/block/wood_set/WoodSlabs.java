package net.fluffybumblebee.terrains.common.instances.block.wood_set;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodSlabs extends SlabBlock {
    public WoodSlabs() {
        super(Settings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.WOOD));
    }
}
