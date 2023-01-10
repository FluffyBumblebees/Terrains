package net.fluffybumblebee.stainedtrees.item;

import net.fluffybumblebee.stainedtrees.block.STBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static net.fluffybumblebee.stainedtrees.StainedTrees.MOD_ID;

public class STItemGroup {
    public static void initClass () {}
    public static final ItemGroup STAINED_TREES;

    static {
        STAINED_TREES = FabricItemGroupBuilder.create(
                        new Identifier(MOD_ID, "general"))
                .icon(() -> new ItemStack(STBlocks.PURPLE_LEAVES))
                .appendItems(stacks -> Registry.ITEM.forEach(item -> {
                    if (Registry.ITEM.getId(item).getNamespace().equals(MOD_ID)) {
                        item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
                    }})).build();
    }
}
