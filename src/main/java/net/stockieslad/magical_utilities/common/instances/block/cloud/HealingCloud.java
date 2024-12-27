package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HealingCloud extends BasicCloud {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity player)
            player.heal(0.1f);
        super.onEntityCollision(state, world, pos, entity);
    }
}
