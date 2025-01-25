package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;

public class HydroCloud extends LiquidCloud {
    public HydroCloud() {
        super(Items.WATER_BUCKET);
    }

    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Cloud.STEAM.item);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Cloud.GELID.item);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!state.get(DORMANT)) entity.setVelocity(entity.getVelocity().multiply(0.1, 0.1, 0.1));
        super.onEntityCollision(state, world, pos, entity);
    }
}
