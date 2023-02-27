package net.stockieslad.terrains.common.instances.block.wood;

import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WoodPressureplate extends PressurePlateBlock implements Wood {
    public WoodPressureplate() {
        super(ActivationRule.EVERYTHING, Settings.copy(Blocks.OAK_PRESSURE_PLATE).sounds(BlockSoundGroup.WOOD));
    }
}
