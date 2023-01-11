package net.fluffybumblebee.terrains.common.instances.block.plant;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {

    public SaplingBlock(SaplingGenerator generator) {
        super(generator, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
    }
}
