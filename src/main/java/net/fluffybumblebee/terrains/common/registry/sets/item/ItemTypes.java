package net.fluffybumblebee.terrains.common.registry.sets.item;

import net.fluffybumblebee.terrains.common.registry.sets.item.ItemRegistrySetConfig.IdentifiableItem;
import net.minecraft.item.Item;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.item.FoodItemType.FOOD_ITEMS;

public record ItemTypes<I extends Item>(List<IdentifiableItem<I>> items) {
    public static final ItemTypes<?>[] ALL_ITEMS;


    static {
        ALL_ITEMS = new ItemTypes<?>[] {
                FOOD_ITEMS
        };
    }

}
