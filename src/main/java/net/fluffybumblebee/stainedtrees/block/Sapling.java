package net.fluffybumblebee.stainedtrees.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class Sapling extends SaplingBlock {

    public Sapling(SaplingGenerator generator) {
        super(generator, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
    }
}
