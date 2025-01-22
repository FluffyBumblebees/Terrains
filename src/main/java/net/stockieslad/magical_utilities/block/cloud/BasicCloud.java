package net.stockieslad.magical_utilities.block.cloud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.util.BlockPredicates;

@SuppressWarnings("deprecation")
public class BasicCloud extends TransparentBlock {
    //Fix Collision shape, add (safe, dormant) properties
    public static final BooleanProperty STABLE = BooleanProperty.of("stable");
    protected static final VoxelShape COLLISION_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 16.0, 0.01, 16.0);
    protected static final VoxelShape FALLING_COLLISION_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9, 1.0);

    public BasicCloud() {
        this(FabricBlockSettings.create().strength(0.2F));
        this.setDefaultState(getDefaultState().with(STABLE, true));
    }

    public BasicCloud(Settings settings) {
        super(settings.sounds(BlockSoundGroup.WOOL).nonOpaque().notSolid().suffocates(BlockPredicates::never).blockVision(BlockPredicates::never));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STABLE);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView reader, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var stack = player.getStackInHand(hand);
        if (stack.isOf(Items.GLOWSTONE_DUST) && !state.get(STABLE)) {
            if (!player.isCreative()) stack.decrement(1);
            world.setBlockState(pos, state.with(STABLE, true));
            return ActionResult.SUCCESS;
        } else if (stack.isOf(Items.REDSTONE) && state.get(STABLE)) {
            if (!player.isCreative()) stack.decrement(1);
            world.setBlockState(pos, state.with(STABLE, false));
            return ActionResult.SUCCESS;
        } else return ActionResult.PASS;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.fallDistance = 0.0F;
        if (entity.getVelocity().y < 0.1) {
            entity.setVelocity(entity.getVelocity().multiply(1.0, 0.005, 1.0));
        }
        entity.setOnGround(entity instanceof LivingEntity livingEntity && (!(livingEntity instanceof PlayerEntity player) || !player.getAbilities().flying));
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {}

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!COLLISION_SHAPE.isEmpty() && world.getBlockState(pos.up()).getBlock() instanceof BasicCloud) {
            return VoxelShapes.fullCube();
        }
        if (context instanceof EntityShapeContext entityCollisionContext) {
            Entity entity = entityCollisionContext.getEntity();
            if (entity != null) {
                if (entity.fallDistance > 2.5F && (!(entity instanceof LivingEntity livingEntity) || !livingEntity.isFallFlying())) {
                    return FALLING_COLLISION_SHAPE;
                }
            }
        }
        return COLLISION_SHAPE;
    }
}
