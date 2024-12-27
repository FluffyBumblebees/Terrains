package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DamagingCloud extends BasicCloud implements Damaging {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Damaging.super.onEntityCollision(state, world, pos, entity);
        super.onEntityCollision(state, world, pos, entity);
    }
}
