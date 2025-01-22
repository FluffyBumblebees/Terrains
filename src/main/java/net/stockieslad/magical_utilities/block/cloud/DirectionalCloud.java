package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static net.stockieslad.magical_utilities.util.BlockHelper.arrayToPool;

public class DirectionalCloud extends BasicCloud {
    private static final EnumProperty<Direction> FACING = Properties.FACING;
    public final WeightedBlockStateProvider DEFAULT_STATES;

    public DirectionalCloud() {
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
        var state = getDefaultState();
        DEFAULT_STATES = new WeightedBlockStateProvider(arrayToPool(
                state.with(FACING, Direction.UP),
                state.with(FACING, Direction.DOWN),
                state.with(FACING, Direction.NORTH),
                state.with(FACING, Direction.EAST),
                state.with(FACING, Direction.SOUTH),
                state.with(FACING, Direction.WEST)
        ));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(STABLE)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }

        entity.fallDistance = 0.0F;
        Vec3d motion = entity.getVelocity();

        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }

        world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);

        if (world.isReceivingRedstonePower(pos)) switch (state.get(FACING)) {
            case EAST -> entity.setVelocity(-2.0, motion.y, motion.z);
            case WEST -> entity.setVelocity(2.0, motion.y, motion.z);
            case UP -> entity.setVelocity(motion.x, -1.0, motion.z);
            case DOWN -> entity.setVelocity(motion.x, 1.0, motion.z);
            case SOUTH -> entity.setVelocity(motion.x, motion.y, -2.0);
            case NORTH -> entity.setVelocity(motion.x, motion.y, 2.0);
        } else switch (state.get(FACING)) {
            case EAST -> entity.setVelocity(2.0, motion.y, motion.z);
            case WEST -> entity.setVelocity(-2.0, motion.y, motion.z);
            case UP -> entity.setVelocity(motion.x, 1.0, motion.z);
            case DOWN -> entity.setVelocity(motion.x, -1.0, motion.z);
            case SOUTH -> entity.setVelocity(motion.x, motion.y, 2.0);
            case NORTH -> entity.setVelocity(motion.x, motion.y, -2.0);
        }

    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(STABLE)) return;

        float x = pos.getX() + (random.nextFloat() * 0.7f) + 0.15f;
        float y = pos.getY() + (random.nextFloat() * 0.7f) + 0.15f;
        float z = pos.getZ() + (random.nextFloat() * 0.7f) + 0.15f;

        Direction facing = state.get(FACING);

        if (world.isReceivingRedstonePower(pos)) facing = facing.getOpposite();

        switch (facing) {
            case EAST -> x = x + 0.75f;
            case WEST ->  x = x - 0.75f;
            case UP -> y = y + 0.75f;
            case DOWN -> y = y - 0.75f;
            case SOUTH -> z = z + 0.75f;
            case NORTH -> z = z - 0.75f;
        }

        final float motionX = facing.getOffsetX() * ((random.nextFloat() * 0.75f) + 0.45f);
        final float motionZ = facing.getOffsetZ() * ((random.nextFloat() * 0.75f) + 0.45f);
        final float motionY = facing.getOffsetY() * ((random.nextFloat() * 0.15f) + 0.25f);

        world.addParticle(ParticleTypes.CLOUD, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return NO_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }
}
