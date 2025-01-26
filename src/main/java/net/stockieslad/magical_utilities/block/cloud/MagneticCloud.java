package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static net.stockieslad.magical_utilities.block.cloud.EntityDiscriminatingCloud.EntityMode.ITEM;
import static net.stockieslad.magical_utilities.util.MoreMath.clamp;

@SuppressWarnings("deprecation")
public class MagneticCloud extends EntityDiscriminatingCloud {
    private static final int RANGE = 10;

    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Items.LAPIS_LAZULI);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        world.scheduleBlockTick(pos, this, 1);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isClient) return;

        world.scheduleBlockTick(pos, this, 1);

        if (state.get(DORMANT)) return;

        var mode = state.get(ENTITY_MODE);

        var x = pos.getX();
        var y = pos.getY();
        var z = pos.getZ();
        var box = new Box(x - RANGE, y - RANGE, z - RANGE,
                x + RANGE, y + RANGE, z + RANGE);
        var entities = world.getEntitiesByClass(Entity.class, box, mode.entityPredicate);

        if (entities.size() > (mode.equals(ITEM) ? RANGE * 10 : RANGE)) return;

        for (Entity entity : entities) {
            if (entity instanceof PlayerEntity player && player.isCreative() && player.getAbilities().flying) continue;
            var entityPos = entity.getBlockPos();
            var velocity = entity.getVelocity();

            var diffX = entityPos.getX() - x;
            var diffY = entityPos.getY() - y;
            var diffZ = entityPos.getZ() - z;

            if (world.isReceivingRedstonePower(pos)) {
                diffX = -diffX;
                diffY = -diffY;
                diffZ = -diffZ;
                if (velocity.y > 1) {
                    entity.fallDistance = entity.fallDistance + 0.01f;
                }
            } else if (velocity.y < 0.25) {
                entity.fallDistance = Math.min(0, entity.fallDistance - 0.001f);
            }

            if (entityPos.equals(pos)) entity.setVelocity(velocity.multiply(0.1, 0.1, 0.1));
            else {
                var newVel = new Vec3d(
                        clamp(velocity.x + Integer.compare(0, diffX) / 100d, -2, 2),
                        clamp(velocity.y + Integer.compare(0, diffY) / 100d, -2, 2),
                        clamp(velocity.z + Integer.compare(0, diffZ) / 100d, -2, 2)
                );
                entity.setVelocity(newVel);
                entity.velocityDirty = true;
                entity.velocityModified = true;
            }
        }
    }
}
