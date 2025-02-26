package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MagmaticCloud extends LiquidCloud {
    public MagmaticCloud() {
        super(Items.LAVA_BUCKET, SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
    }

    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Items.SNOWBALL);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Items.BLAZE_POWDER);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!state.get(DORMANT)) {
            if (!entity.isOnFire() && entity instanceof LivingEntity)
                entity.setOnFireFromLava();
            entity.setVelocity(entity.getVelocity().multiply(0.1, 0.1, 0.1));
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(DORMANT)) return;
        double x = pos.getX() + random.nextDouble();
        double y = pos.getY() + random.nextDouble();
        double z = pos.getZ() + random.nextDouble();
        world.addParticle(ParticleTypes.LAVA, x, y, z, - 0.01 + random.nextFloat() / 50, random.nextFloat() / 50, - 0.01 + random.nextFloat() / 50);
    }
}
