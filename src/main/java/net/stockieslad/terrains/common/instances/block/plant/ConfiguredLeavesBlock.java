package net.stockieslad.terrains.common.instances.block.plant;

import net.stockieslad.terrains.util.predicates.BlockPredicates;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class ConfiguredLeavesBlock extends LeavesBlock {
    public ConfiguredLeavesBlock() {
        super(Settings
                .of(Material.LEAVES)
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
                .ticksRandomly()
                .strength(0.2F)
                .suffocates(BlockPredicates::never)
                .allowsSpawning(BlockPredicates::canSpawnOnLeaves)
                .blockVision(BlockPredicates::never));
    }
}
