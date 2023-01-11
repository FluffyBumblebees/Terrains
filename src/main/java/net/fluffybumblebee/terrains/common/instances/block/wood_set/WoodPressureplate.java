package net.fluffybumblebee.terrains.common.instances.block.wood_set;

import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodPressureplate extends PressurePlateBlock {
    public WoodPressureplate() {
        super(ActivationRule.EVERYTHING, Settings.copy(Blocks.OAK_PRESSURE_PLATE).sounds(BlockSoundGroup.WOOD));
    }
}
