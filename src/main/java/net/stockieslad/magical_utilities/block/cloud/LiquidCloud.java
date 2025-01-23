package net.stockieslad.magical_utilities.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Clouds;

public class LiquidCloud extends BasicCloud {
    private final Item filledBucket;
    private final SoundEvent sound;

    public LiquidCloud(Item filledBucket, SoundEvent sound) {
        this.filledBucket = filledBucket;
        this.sound = sound;
    }

    public LiquidCloud(Item filledBucket) {
        this(filledBucket, SoundEvents.ITEM_BUCKET_FILL);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var item = player.getStackInHand(hand);
        if (item.isOf(Items.BUCKET) && !state.get(DORMANT)) {
            player.playSound(sound, 1, 1);
            player.setStackInHand(hand, filledBucket.getDefaultStack());
            world.setBlockState(pos, Clouds.STEAM.block.getDefaultState().with(DORMANT, false));
            return ActionResult.SUCCESS;
        } else return super.onUse(state, world, pos, player, hand, hit);
    }
}
