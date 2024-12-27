package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RedstoneCloud extends BasicCloud {
    public static final BooleanProperty POWERED = Properties.POWERED;

    public RedstoneCloud() {
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    protected int getRedstoneOutput(BlockState state) {
        return state.get(POWERED) ? 15 : 0;
    }

    protected BlockState setRedstoneOutput(BlockState state, int rsOut) {
        return state.with(POWERED, rsOut > 0);
    }

    protected int getRedstoneOutput(World world, BlockPos pos) {
        return getRedstoneOutput(world.getBlockState(pos));
    }
}
