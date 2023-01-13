package net.fluffybumblebee.terrains.common.instances.block.plant;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;

public class ConfiguredFlowerPotBlock extends FlowerPotBlock {
    public ConfiguredFlowerPotBlock(Block content) {
        super(content, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
    }
}