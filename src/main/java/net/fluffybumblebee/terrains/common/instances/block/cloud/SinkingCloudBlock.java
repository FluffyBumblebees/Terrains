package net.fluffybumblebee.terrains.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class SinkingCloudBlock extends BasicCloudBlock {

    protected static VoxelShape SHAPE = VoxelShapes.empty();

    public SinkingCloudBlock(Settings properties) {
        super(properties);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
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
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
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
        return SHAPE;
    }

    private boolean canFallThrough(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.isIn(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable() || state == this.getDefaultState();
    }
}
