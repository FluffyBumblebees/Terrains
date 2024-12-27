package net.stockieslad.magical_utilities.common.instances.block.plant;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.stockieslad.magical_utilities.util.BlockPredicates;
import net.minecraft.block.LeavesBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ConfiguredLeavesBlock extends LeavesBlock {
    public ConfiguredLeavesBlock() {
        super(FabricBlockSettings.create()
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .ticksRandomly()
                .strength(0.2F)
                .suffocates(BlockPredicates::never)
                .allowsSpawning(BlockPredicates::canSpawnOnLeaves)
                .blockVision(BlockPredicates::never));
    }
}
