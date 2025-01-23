package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static net.stockieslad.magical_utilities.block.cloud.MagneticCloud.Mode.PULLING;
import static net.stockieslad.magical_utilities.block.cloud.MagneticCloud.Mode.PUSHING;
import static net.stockieslad.magical_utilities.util.MoreMath.clamp;

@SuppressWarnings("deprecation")
public class MagneticCloud extends BasicCloud {
    private static final EnumProperty<Mode> MODE = EnumProperty.of("mode", Mode.class);
    private static final int RANGE = 10;

    public MagneticCloud() {
        this.setDefaultState(this.stateManager.getDefaultState().with(MODE, PULLING));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MODE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var stack = player.getStackInHand(hand);
        if (stack.isOf(Items.COPPER_INGOT) && state.get(MODE).equals(PULLING)) {
            if (!player.isCreative()) stack.decrement(1);
            world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);
            world.setBlockState(pos, state.with(MODE, PUSHING));
            return ActionResult.SUCCESS;
        } else if (stack.isOf(Items.IRON_INGOT) && state.get(MODE).equals(PUSHING)) {
            if (!player.isCreative()) stack.decrement(1);
            world.playSound(null, pos, BlockSoundGroup.WOOL.getBreakSound(), SoundCategory.BLOCKS);
            world.setBlockState(pos, state.with(MODE, PULLING));
            return ActionResult.SUCCESS;
        } else return super.onUse(state, world, pos, player, hand, hit);
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

        var powered = world.isReceivingRedstonePower(pos);
        var x = pos.getX();
        var y = pos.getY();
        var z = pos.getZ();
        var box = new Box(x - RANGE, y - RANGE, z - RANGE,
                          x + RANGE, y + RANGE, z + RANGE);
        var entities = world.getEntitiesByClass(Entity.class, box, entity -> powered ? entity instanceof LivingEntity : entity instanceof ItemEntity);

        if (entities.size() > (powered ? RANGE : RANGE * 10)) return;

        for (Entity entity : entities) {
            var entityPos = entity.getBlockPos();
            var velocity = entity.getVelocity();

            var diffX = entityPos.getX() - x;
            var diffY = entityPos.getY() - y;
            var diffZ = entityPos.getZ() - z;

            if (state.get(MODE).equals(PUSHING)) {
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

    protected enum Mode implements StringIdentifiable {
        PULLING,
        PUSHING;

        @Override
        public String asString() {
            return name().toLowerCase();
        }
    }
}
