package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DamagingCloud extends BasicCloud {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!state.get(DORMANT))
            entity.damage(world.getDamageSources().inWall(), 1.0f);
        super.onEntityCollision(state, world, pos, entity);
    }
}
