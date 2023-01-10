package net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables;

import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.sound.BlockSoundGroup;

public class MFWoodPressureplate extends PressurePlateBlock {
    public MFWoodPressureplate() {
        super(ActivationRule.EVERYTHING, Settings.copy(Blocks.OAK_PRESSURE_PLATE).sounds(BlockSoundGroup.WOOD));
    }
}
