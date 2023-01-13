package net.fluffybumblebee.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodBlock extends PillarBlock implements Wood {
    public WoodBlock() {
        super(Settings.copy(Blocks.OAK_WOOD).sounds(BlockSoundGroup.WOOD));
    }
}
