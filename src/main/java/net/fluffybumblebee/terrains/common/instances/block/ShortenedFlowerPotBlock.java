package net.fluffybumblebee.terrains.common.instances.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;

public class ShortenedFlowerPotBlock extends FlowerPotBlock {
    public ShortenedFlowerPotBlock(Block content) {
        super(content, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
    }
}