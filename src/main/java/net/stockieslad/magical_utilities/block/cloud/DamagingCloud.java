package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DamagingCloud extends BasicCloud {
    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Items.GHAST_TEAR);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Items.FERMENTED_SPIDER_EYE);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!state.get(DORMANT) && entity instanceof LivingEntity)
            entity.damage(world.getDamageSources().inWall(), 1.0f);
        super.onEntityCollision(state, world, pos, entity);
    }
}
