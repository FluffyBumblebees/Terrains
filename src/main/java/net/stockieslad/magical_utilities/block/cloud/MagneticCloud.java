package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class MagneticCloud extends BasicCloud {

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STABLE)) return;
        world.isPlayerInRange(pos.getX(), pos.getY(), pos.getZ(), 10);
        super.randomDisplayTick(state, world, pos, random);
    }
}
