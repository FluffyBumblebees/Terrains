package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

import static net.stockieslad.magical_utilities.block.cloud.EntityDiscriminatingCloud.EntityMode.ITEM;

public class EntityDiscriminatingCloud extends BasicCloud {
    public static final EnumProperty<EntityMode> ENTITY_MODE = EnumProperty.of("entity_mode", EntityMode.class);

    public EntityDiscriminatingCloud() {
        this.setDefaultState(this.stateManager.getDefaultState().with(ENTITY_MODE, ITEM));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ENTITY_MODE);
    }

    public boolean tryUpdateEntityMode(World world, BlockState state, BlockPos pos, ItemStack stack, EntityMode mode, @Nullable PlayerEntity player) {
        for (EntityMode value : EntityMode.values()) {
            if (!mode.equals(value) && value.testStack(stack)) {
                if (player == null || !player.isCreative()) stack.decrement(1);
                world.playSound(null, pos, BlockSoundGroup.WOOL.getPlaceSound(), SoundCategory.BLOCKS);
                world.setBlockState(pos, state.with(ENTITY_MODE, value));
                return true;
            }
        }

        return false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var stack = player.getStackInHand(hand);
        var mode = state.get(ENTITY_MODE);

        if (tryUpdateEntityMode(world, state, pos, stack, mode, player))
            return ActionResult.SUCCESS;
        else return super.onUse(state, world, pos, player, hand, hit);
    }

    public enum EntityMode implements StringIdentifiable {
        ALL(stack -> stack.isOf(Items.COPPER_INGOT), entity -> true),
        ITEM(stack -> stack.isOf(Items.IRON_INGOT), entity -> entity instanceof ItemEntity),
        LIVING(stack -> stack.isOf(Items.GOLD_INGOT), entity -> entity instanceof LivingEntity);

        private final Predicate<ItemStack> stackPredicate;
        public final Predicate<Entity> entityPredicate;

        public boolean testStack(ItemStack stack) {
            return stackPredicate.test(stack);
        }

        public boolean testEntity(Entity entity) {
            return entityPredicate.test(entity);
        }

        EntityMode(Predicate<ItemStack> stackPredicate, Predicate<Entity> entityPredicate) {
            this.stackPredicate = stackPredicate;
            this.entityPredicate = entityPredicate;
        }

        @Override
        public String asString() {
            return name().toLowerCase();
        }

        public static EntityMode find(ItemStack stack) {
            for (EntityMode value : values()) {
                if (value.testStack(stack))
                    return value;
            }
            return null;
        }
    }
}
