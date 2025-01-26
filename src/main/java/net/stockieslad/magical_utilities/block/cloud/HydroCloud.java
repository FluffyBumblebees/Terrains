package net.stockieslad.magical_utilities.block.cloud;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;
import toughasnails.api.player.ITANPlayer;

public class HydroCloud extends LiquidCloud {
    private static final boolean IS_TAN_LOADED = FabricLoader.getInstance().isModLoaded("toughasnails");

    public HydroCloud() {
        super(Items.WATER_BUCKET);
    }

    @Override
    public boolean testPacifier(ItemStack stack) {
        return stack.isOf(Cloud.STEAM.item);
    }

    @Override
    public boolean testActivator(ItemStack stack) {
        return stack.isOf(Cloud.GELID.item);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        var random = world.getRandom();
        if (!state.get(DORMANT)) {
            entity.setVelocity(entity.getVelocity().multiply(0.1, 0.1, 0.1));
            if (IS_TAN_LOADED && random.nextInt(1000) == 0 && entity instanceof PlayerEntity player) {
                ((ITANPlayer)player).getThirstData().drink(1, 0.1f);
                if (random.nextInt(100) == 0)
                    shedAbility(world, pos);
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
