package net.fluffybumblebee.quarkcrystals.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class CorundumCluster extends AmethystClusterBlock {
    public CorundumCluster() {
        super   (
                10,
                3,
                FabricBlockSettings
                        .copy(Blocks.AMETHYST_CLUSTER)
                        .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                        .nonOpaque()
                        .strength(1.2F)
                        .requiresTool()
        );
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        if  (   world.getBlockState(blockPos).getBlock() instanceof CorundumBlock ||
                world.getBlockState(blockPos).getBlock() instanceof CrystalBlock
        ) {
            return true;
        }
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
    }

}
