package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RandomCloud extends BasicCloud {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(STABLE)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }

        entity.fallDistance = 0.0F;
        Direction facing = DIRECTIONS[world.getRandom().nextInt(DIRECTIONS.length)];
        Vec3d motion = entity.getVelocity();

        if (entity.isSneaking()) {
            if (motion.y < 0) {
                entity.setVelocity(motion.multiply(1.0, 0.005, 1.0));
            }
            return;
        }

        world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);

        entity.setVelocity(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return NO_SHAPE;
    }
}
