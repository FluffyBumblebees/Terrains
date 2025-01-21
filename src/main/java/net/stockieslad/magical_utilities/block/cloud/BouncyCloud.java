package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BouncyCloud extends BasicCloud {

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(STABLE)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }
        if (entity.bypassesLandingEffects())
            super.onEntityCollision(state, world, pos, entity);
        else {
            Vec3d entityVelocity = entity.getVelocity();
            BlockPos entityPos = entity.getBlockPos();
            double speedModifier = entity instanceof LivingEntity ? 1.0 : 0.8;

            var velocityX = (pos.getX() - entityPos.getX() == 0 ? entityVelocity.x : -entityVelocity.x) * speedModifier;
            var velocityY = (pos.getY() - entityPos.getY() == 0 ? entityVelocity.y : -entityVelocity.y) * speedModifier;
            var velocityZ = (pos.getZ() - entityPos.getZ() == 0 ? entityVelocity.z : -entityVelocity.z) * speedModifier;

            entity.setVelocity(velocityX, velocityY, velocityZ);
        }
    }
}
