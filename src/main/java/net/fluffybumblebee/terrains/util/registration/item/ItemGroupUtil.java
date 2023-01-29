package net.fluffybumblebee.terrains.util.registration.item;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;

public class ItemGroupUtil {
    public static void addGroupEntry(final ItemConvertible item, final ItemGroup group) {
        final var stack = item.asItem().getDefaultStack();
        group.getDisplayStacks().add(stack);
        group.getSearchTabStacks().add(stack);
    }
}
