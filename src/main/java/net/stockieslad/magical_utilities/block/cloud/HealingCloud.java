package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;

public class HealingCloud extends BasicCloud {
    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Items.FERMENTED_SPIDER_EYE);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Items.GHAST_TEAR);
    }

    @Override
    public boolean noShapeWhenActivated() {
        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        var random = world.getRandom();
        if (!world.isClient && !state.get(DORMANT) && random.nextInt(1000) == 0 && entity instanceof PlayerEntity player) {
            player.heal(0.1f);
            if (random.nextInt(1000) == 0)
                world.setBlockState(pos, Cloud.STEAM.block.getDefaultState());
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
