package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class ColdCloud extends BasicCloud {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(DORMANT)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }

        entity.fallDistance = 0.0F;
        Vec3d motion = entity.getVelocity();
        BlockPos position = entity.getBlockPos();

        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }

        if ((Math.abs(motion.getY()) > 0.1f) && canFallThrough(world.getBlockState(position.down())) && canFallThrough(world.getBlockState(position.down().down()))) {
            entity.fallDistance = 23F;
            entity.setVelocity(motion.x, -3.9, motion.z);
        }

        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.8999999761581421, 1.5, 0.8999999761581421));
            if (world.isClient) {
                Random random = world.getRandom();
                boolean bl = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (bl && random.nextBoolean()) {
                    world.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), pos.getY() + 1, entity.getZ(), MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F, 0.05000000074505806, MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F);
                }
            }
        }

        entity.setInPowderSnow(true);
        if (!world.isClient) {
            if (entity.isOnFire() && (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity) && entity.canModifyAt(world, pos)) {
                world.breakBlock(pos, false);
            }

            entity.setOnFire(false);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(DORMANT)) return;
        super.randomDisplayTick(state, world, pos, random);

        final double xOffset = pos.getX() + world.random.nextDouble();
        final double yOffset = pos.getY() + world.random.nextDouble() - 0.5;
        final double zOffset = pos.getZ() + world.random.nextDouble();

        world.addParticle(ParticleTypes.CLOUD, xOffset, yOffset, zOffset, 0.0,
                Direction.DOWN.getOffsetY() * ((random.nextFloat() * 0.75f) + 0.45f),
                0.0);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return NO_SHAPE;
    }

    private boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state == this.getDefaultState();
    }
}

