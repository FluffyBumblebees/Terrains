package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;

public class SmitingCloud extends BasicCloud {
    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Cloud.CHAOS.item);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Items.COPPER_INGOT);
    }

    @Override
    public boolean noShapeWhenActivated() {
        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (world instanceof ServerWorld serverWorld && !state.get(DORMANT)) {
            if (world.getRandom().nextInt(5) == 0)
                serverWorld.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.5f, 10f);
            if (world.getRandom().nextInt(100) != 0) return;
            var lightning = EntityType.LIGHTNING_BOLT.create(serverWorld);
            if (lightning == null) return;
            lightning.setPos(pos.getX(), pos.getY(), pos.getZ());
            serverWorld.spawnEntity(lightning);
        }
    }
}
