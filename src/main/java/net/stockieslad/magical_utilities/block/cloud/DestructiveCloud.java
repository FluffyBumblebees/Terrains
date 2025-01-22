package net.stockieslad.magical_utilities.block.cloud;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import static net.stockieslad.magical_utilities.block.cloud.RedstoneCloud.BOX;

@SuppressWarnings("deprecation")
public class DestructiveCloud extends BasicCloud {
    private static final int RANGE = 5;

    public DestructiveCloud() {
        super(FabricBlockSettings.create().strength(0.2f).ticksRandomly().luminance(15));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STABLE)) return;
        if (world.isReceivingRedstonePower(pos)) {
            var x = pos.getX() + (random.nextBoolean() ? random.nextInt(RANGE) : -random.nextInt(RANGE));
            var y = pos.getY() + (random.nextBoolean() ? random.nextInt(RANGE) : -random.nextInt(RANGE));
            var z = pos.getZ() + (random.nextBoolean() ? random.nextInt(RANGE) : -random.nextInt(RANGE));
            var newPos = new BlockPos(x, y, z);
            var newState = world.getBlockState(newPos);
            world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25f, random.nextFloat());
            world.getEntitiesByClass(Entity.class, BOX.offset(pos), EntityPredicates.EXCEPT_SPECTATOR).forEach(Entity::kill);
            if (!newState.isAir())
                world.breakBlock(newPos, true);
            else if (random.nextInt(5) == 0) {
                world.breakBlock(pos, false);
            }
        }
    }
}
