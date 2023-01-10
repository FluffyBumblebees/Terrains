package net.fluffybumblebee.maple_forest.blocks.custom.tree;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;


public class MFSaplings extends SaplingBlock {

    public MFSaplings(SaplingGenerator generator) {
        super(generator, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).sounds(BlockSoundGroup.GRASS).nonOpaque());
    }

}

