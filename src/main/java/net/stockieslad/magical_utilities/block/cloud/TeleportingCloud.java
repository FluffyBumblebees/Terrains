package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class TeleportingCloud extends BasicCloud {

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(STABLE)) {
            super.onEntityCollision(state, world, pos, entity);
            return;
        }

        var random = world.getRandom();

        if (world.getRandom().nextInt(100) == 0) return;

        if (entity instanceof LivingEntity livingEntity) {
            double x = entity.getX() + (random.nextDouble() - 0.5) * 64.0;
            double y = entity.getY() + (random.nextInt(64) - 32);
            double z = entity.getZ() + (random.nextDouble() - 0.5) * 64.0;

            BlockPos.Mutable entityPos = new BlockPos.Mutable(x, y, z);

            while(entityPos.getY() > world.getBottomY() && !world.getBlockState(entityPos).blocksMovement()) {
                entityPos.move(Direction.DOWN);
            }

            boolean teleported = livingEntity.teleport(x, y, z, true);
            if (teleported) {
                if (!livingEntity.isSilent()) {
                    world.playSound(null, livingEntity.prevX, livingEntity.prevY, livingEntity.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
}
