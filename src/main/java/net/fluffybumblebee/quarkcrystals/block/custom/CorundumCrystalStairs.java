package net.fluffybumblebee.quarkcrystals.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.sound.BlockSoundGroup;

public class CorundumCrystalStairs extends StairsBlock {
    public CorundumCrystalStairs() {
        super(Blocks.OAK_STAIRS.getDefaultState(),
                FabricBlockSettings
                        .of(Material.AMETHYST)
                        .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                        .nonOpaque()
                        .luminance(10)
                        .strength(1.2F)
                        .ticksRandomly()
                        .requiresTool());
    }
}
