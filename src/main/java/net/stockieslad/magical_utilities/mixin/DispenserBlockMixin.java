package net.stockieslad.magical_utilities.mixin;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.stockieslad.magical_utilities.block.cloud.EntityDiscriminatingCloud;
import net.stockieslad.magical_utilities.core.Cloud;
import net.stockieslad.magical_utilities.util.CloudDispenserBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashSet;
import java.util.Set;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlockMixin {
    @Unique private static final Set<Item> CLOUD_CACHE = new HashSet<>();
    @Shadow protected abstract DispenserBehavior getBehaviorForItem(ItemStack stack);

    @Redirect(method = "dispense", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/DispenserBlock;getBehaviorForItem(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/dispenser/DispenserBehavior;"))
    private DispenserBehavior magicalUtils$addCloudSupport(DispenserBlock dispenser, ItemStack stack) {
        if (CLOUD_CACHE.contains(stack.getItem()))
            return CloudDispenserBehaviour.INSTANCE;

        for (Cloud value : Cloud.values()) {
            var block = value.block;
            if (block.testPacifier(stack) || block.testActivator(stack) || EntityDiscriminatingCloud.EntityMode.find(stack) != null) {
                CLOUD_CACHE.add(stack.getItem());
                return CloudDispenserBehaviour.INSTANCE;
            }
        }

        return getBehaviorForItem(stack);
    }
}
