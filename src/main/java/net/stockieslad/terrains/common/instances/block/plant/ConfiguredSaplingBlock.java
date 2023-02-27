package net.stockieslad.terrains.common.instances.block.plant;

import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class ConfiguredSaplingBlock extends SaplingBlock {

    public ConfiguredSaplingBlock(SaplingGenerator generator) {
        super(generator, Settings.copy(Blocks.OAK_SAPLING));
    }
}
