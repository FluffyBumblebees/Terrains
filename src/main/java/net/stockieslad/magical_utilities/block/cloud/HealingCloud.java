package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Clouds;

public class HealingCloud extends BasicCloud {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        var random = world.getRandom();
        if (!world.isClient && !state.get(STABLE) && random.nextInt(1000) == 0 && entity instanceof PlayerEntity player) {
            player.heal(0.1f);
            if (random.nextInt(1000) == 0)
                world.setBlockState(pos, Clouds.STEAM.block.getDefaultState());
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
