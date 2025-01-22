package net.stockieslad.magical_utilities.block.crystals;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;

public class CorundumCrystalSlab extends SlabBlock {
    public CorundumCrystalSlab() {
        super(FabricBlockSettings.create()
                .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                .nonOpaque()
                .luminance(10)
                .strength(1.2F)
                .ticksRandomly()
                .requiresTool());
    }
}