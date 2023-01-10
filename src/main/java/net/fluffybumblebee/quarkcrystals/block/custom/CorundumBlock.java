package net.fluffybumblebee.quarkcrystals.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BuddingAmethystBlock;
import net.minecraft.block.Material;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;


@SuppressWarnings({"deprecation"})
public class CorundumBlock extends BuddingAmethystBlock {
    private final List<Direction> DIRECTIONS = Arrays.asList(Direction.values());
    private final boolean waxed;
    private final Supplier<CorundumCluster> corundum;

    public CorundumBlock(boolean waxed, Supplier<CorundumCluster> corundum) {
        super(FabricBlockSettings
                        .of(Material.AMETHYST)
                        .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                        .nonOpaque()
                        .luminance(10)
                        .strength(1.2F)
                        .ticksRandomly()
                        .requiresTool()
        );
        this.waxed = waxed;
        this.corundum = corundum;

    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int bound = pos.getY() + 64;
        if (bound == 0) bound++;
        if (!waxed && random.nextInt(bound) <= 2) {
            IntegerHolder holder = new IntegerHolder(0);
            int connectedBlockAmount = (384 - (pos.getY() + 64)) / 96;
            scanForLimit(world, pos, null, holder, connectedBlockAmount);
            if (holder.i < connectedBlockAmount) {
                Direction direction00 = Direction.random(random);
                Direction direction01 = Direction.random(random);
                Direction direction02 = Direction.random(random);
                Direction direction03 = Direction.random(random);

                BlockPos offPosBase = pos.offset(direction00);
                BlockPos offPos1 = offPosBase.offset(direction01);
                BlockPos offPos2 = offPosBase.offset(direction02);
                BlockPos offPos3 = offPosBase.offset(direction03);

                if (world.isAir(offPosBase)) {
                    if (random.nextInt(3) == 0) {
                        world.setBlockState(offPosBase, this.getDefaultState());
                        if (world.isAir(offPos1) && random.nextInt(2) == 0)
                            world.setBlockState(offPos1, corundum.get().getDefaultState().with(AmethystClusterBlock.FACING, direction01));
                        if (world.isAir(offPos2) && random.nextInt(4) == 0)
                            world.setBlockState(offPos2, corundum.get().getDefaultState().with(AmethystClusterBlock.FACING, direction02));
                        if (world.isAir(offPos3) && random.nextInt(8) == 0)
                            world.setBlockState(offPos3, corundum.get().getDefaultState().with(AmethystClusterBlock.FACING, direction03));
                    } else
                        world.setBlockState(offPosBase, corundum.get().getDefaultState().with(AmethystClusterBlock.FACING, direction00));
                }
            }
        }
    }

    private Direction scanForLimit(ServerWorld world, BlockPos pos, Direction toExclude, IntegerHolder holder, int limit) {
        if (holder.i >= limit) {
            return null;
        }
        Collections.shuffle(DIRECTIONS);
        for (Direction direction : DIRECTIONS) {
            if (direction != toExclude && world.getBlockState(pos.offset(direction)).isOf(this)) {
                holder.add();
                return scanForLimit(world, pos.offset(direction), direction.getOpposite(), holder, limit);
            }
        }
        return null;
    }

    private static class IntegerHolder {
        private int i;
        protected IntegerHolder(int i) {
            this.i = i;
        }
        protected void add() {
            i++;
        }
    }
}
