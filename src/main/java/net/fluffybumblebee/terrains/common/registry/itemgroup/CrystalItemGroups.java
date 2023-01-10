package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.registry.blocks.CrystalBlocks;
import net.fluffybumblebee.terrains.util.IdentifierUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;


public class CrystalItemGroups {
    public static void registerItemgroup() {}
    public static final ItemGroup QUARK_CRYSTALS;

    static {
        QUARK_CRYSTALS = FabricItemGroupBuilder.create(
                        new Identifier(IdentifierUtil.ID, IdentifierUtil.ID))
                .icon(() -> new ItemStack(CrystalBlocks.PURPLE_CORUNDUM))
                .appendItems(stacks -> Registry.ITEM.forEach((item) -> {
                    if (Registry.ITEM.getId(item).getNamespace().equals(IdentifierUtil.ID)) {
                        item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
                    }
                })).build();
    }
}
