package net.stockieslad.magical_utilities.common.instances.block.cloud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.util.BlockPredicates;

@SuppressWarnings("deprecation")
public class BasicCloud extends TransparentBlock {

    public BasicCloud() {
        super(FabricBlockSettings.create().strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque().notSolid()
                .suffocates(BlockPredicates::never).blockVision(BlockPredicates::never));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.fallDistance = 0.0F;

        if (entity.getVelocity().y < 0.0) {
            entity.setVelocity(entity.getVelocity().multiply(1.0, 0.005, 1.0));
        }
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
    public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView reader, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}
