package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class AttractionCloud extends BasicCloud {

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.isPlayerInRange(pos.getX(), pos.getY(), pos.getZ(), 10);
        super.randomDisplayTick(state, world, pos, random);
    }
}
