package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RisingCloud extends BasicCloud {
    public RisingCloud() {
        super();
    }

    @Override
    public void onEntityCollision(
            final BlockState state, final World world, final BlockPos pos, final Entity entity
    ) {
        entity.fallDistance = 0.0F;
        final Vec3d motion = entity.getVelocity();

        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }

        if (motion.y < 2) {
            if (-1.4 < motion.y) {
                entity.setVelocity(motion.x, 2.0, motion.z);
            } else {
                entity.setVelocity(motion.x, -1.4 * motion.y, motion.z);
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        final double xOffset = pos.getX() + world.random.nextDouble();
        final double yOffset = pos.getY() + world.random.nextDouble() + 0.5;
        final double zOffset = pos.getZ() + world.random.nextDouble();

        world.addParticle(ParticleTypes.CLOUD, xOffset, yOffset, zOffset, 0.0,
                Direction.UP.getOffsetY() * ((random.nextFloat() * 0.75f) + 0.45f),
                0.0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}
