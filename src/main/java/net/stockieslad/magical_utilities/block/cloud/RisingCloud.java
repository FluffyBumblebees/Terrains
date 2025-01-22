package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class RisingCloud extends BasicCloud {
    public RisingCloud() {
        super();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(STABLE)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }

        entity.fallDistance = 0.0F;
        final Vec3d motion = entity.getVelocity();
        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }
        world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);
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
        if (state.get(STABLE)) return;
        super.randomDisplayTick(state, world, pos, random);

        final double xOffset = pos.getX() + world.random.nextDouble();
        final double yOffset = pos.getY() + world.random.nextDouble() + 0.5;
        final double zOffset = pos.getZ() + world.random.nextDouble();

        world.addParticle(ParticleTypes.CLOUD, xOffset, yOffset, zOffset, 0.0,
                Direction.UP.getOffsetY() * ((random.nextFloat() * 0.75f) + 0.45f),
                0.0);
    }
}
