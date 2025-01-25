package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@SuppressWarnings("deprecation")
public class RedstoneCloud extends BasicCloud {
    private static final BooleanProperty POWERED = Properties.POWERED;
    public static final Box BOX = new Box(0, 0, 0, 1, 1, 1);

    public RedstoneCloud() {
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(POWERED);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(DORMANT)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }
        super.onEntityCollision(state, world, pos, entity);
        if (!world.isClient) {
            if (!state.get(POWERED)) {
                world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);
                world.emitGameEvent(null, GameEvent.BLOCK_ACTIVATE, pos);
                world.setBlockState(pos, state.with(POWERED, true));
            } else world.updateNeighborsAlways(pos, this);
            world.scheduleBlockTick(pos, this, 5);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.get(DORMANT)) return;
        if (!moved && !state.isOf(newState.getBlock())) {
            if (this.getRedstoneOutput(state) > 0)
                world.updateNeighborsAlways(pos, this);
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(DORMANT)) return;
        boolean noEntity = world.getEntitiesByClass(Entity.class, BOX.offset(pos),
                EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> !entity.canAvoidTraps())).isEmpty();
        if (state.get(POWERED) && noEntity) {
            world.playSound(null, pos, BlockSoundGroup.WOOL.getBreakSound(), SoundCategory.BLOCKS);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
            world.setBlockState(pos, state.with(POWERED, false));
        } else world.updateNeighborsAlways(pos, this);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return this.getRedstoneOutput(state);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? this.getRedstoneOutput(state) : 0;
    }

    protected int getRedstoneOutput(BlockState state) {
        return state.get(POWERED) ? 15 : 0;
    }
}
