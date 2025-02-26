package net.stockieslad.magical_utilities.block.crystals;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.BlockState;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;


@SuppressWarnings({"deprecation"})
public class CrystalBlock extends AmethystBlock {
    public CrystalBlock() {
        super(FabricBlockSettings.create()
                .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                .nonOpaque()
                .luminance(10)
                .lightLevel(5)
                .ticksRandomly()
                .strength(1.2F)
                .requiresTool()
        );
    }
    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
}
