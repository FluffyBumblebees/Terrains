package net.fluffybumblebee.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodSlabs extends SlabBlock implements Wood {
    public WoodSlabs() {
        super(Settings.copy(Blocks.OAK_SLAB).sounds(BlockSoundGroup.WOOD));
    }
}
