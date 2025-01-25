package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;

public class RandomCloud extends BasicCloud {
    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Cloud.DENSE.item);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Cloud.STEAM.item);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(DORMANT)) {
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
}
