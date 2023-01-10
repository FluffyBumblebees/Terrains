package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import static net.fluffybumblebee.terrains.TerrainsDefaults.NAMESPACE;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = FabricItemGroupBuilder.create(
                        new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(StainedBlocks.PURPLE_LEAVES_BLOCK))
                .appendItems(stacks -> Registry.ITEM.forEach(item -> {
                    if (Registry.ITEM.getId(item).getNamespace().equals(NAMESPACE)) {
                        item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
                    }})).build();
    }
}
