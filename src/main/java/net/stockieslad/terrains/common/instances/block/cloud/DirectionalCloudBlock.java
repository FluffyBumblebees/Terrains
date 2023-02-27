package net.stockieslad.terrains.common.instances.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;


public class DirectionalCloudBlock extends BasicCloudBlock {

    protected static VoxelShape SHAPE;
    private static final EnumProperty<Direction> FACING;
    public final BlockState DEFAULT_STATE;
    public final BlockState[] ALL_STATES;
    public final WeightedBlockStateProvider DEFAULT_STATES;

    static {
        SHAPE = VoxelShapes.empty();
        FACING = Properties.FACING;
    }

    public DirectionalCloudBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP));
        DEFAULT_STATE = this.getDefaultState();
        ALL_STATES = new BlockState[] {
                DEFAULT_STATE.with(FACING, Direction.UP),
                DEFAULT_STATE.with(FACING, Direction.DOWN),
                DEFAULT_STATE.with(FACING, Direction.NORTH),
                DEFAULT_STATE.with(FACING, Direction.EAST),
                DEFAULT_STATE.with(FACING, Direction.SOUTH),
                DEFAULT_STATE.with(FACING, Direction.WEST)
        };
        DEFAULT_STATES = new WeightedBlockStateProvider(arrayToPool(ALL_STATES));
    }

    @SuppressWarnings("SameParameterValue")
    public static <T> DataPool<T> arrayToPool(T[] array) {
        DataPool.Builder<T> builder = DataPool.builder();
        for (T element : array) {
            builder.add(element, 1);
        }
        return builder.build();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.fallDistance = 0.0F;
        Vec3d motion = entity.getVelocity();

        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }

        switch (state.get(FACING)) {
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
        super.randomDisplayTick(state, world, pos, random);
        final Direction facing = state.get(FACING);

        switch (facing) {
            case EAST -> pos = new BlockPos(pos.getX() + 0.5, pos.getY(), pos.getZ());
            case WEST ->  new BlockPos(pos.getX() - 0.5, pos.getY(), pos.getZ());
            case UP -> pos = new BlockPos(pos.getX() , pos.getY() + 0.5, pos.getZ());
            case DOWN -> pos = new BlockPos(pos.getX() , pos.getY() - 0.5, pos.getZ());
            case SOUTH -> pos = new BlockPos(pos.getX() , pos.getY(), pos.getZ() + 0.5);
            case NORTH -> pos = new BlockPos(pos.getX() , pos.getY(), pos.getZ() - 0.5);
        }

        float x = pos.getX() + (random.nextFloat() * 0.7f) + 0.15f;
        float y = pos.getY() + (random.nextFloat() * 0.7f) + 0.15f;
        float z = pos.getZ() + (random.nextFloat() * 0.7f) + 0.15f;

        final float offset = 0.25F;
        switch (facing) {
            case EAST -> x = x + offset;
            case WEST ->  x = x - offset;
            case UP -> y = y + offset;
            case DOWN -> y = y - offset;
            case SOUTH -> z = z + offset;
            case NORTH -> z = z - offset;
        }

        final float motionX = facing.getOffsetX() * ((random.nextFloat() * 0.75f) + 0.45f);
        final float motionZ = facing.getOffsetZ() * ((random.nextFloat() * 0.75f) + 0.45f);
        final float motionY = facing.getOffsetY() * ((random.nextFloat() * 0.15f) + 0.25f);

        world.addParticle(ParticleTypes.CLOUD, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }
}
