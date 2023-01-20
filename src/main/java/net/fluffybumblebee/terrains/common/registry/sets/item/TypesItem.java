package net.fluffybumblebee.terrains.common.registry.sets.item;

import net.fluffybumblebee.terrains.common.registry.sets.item.component.ItemSet.IdentifiableItem;
import net.minecraft.item.Item;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.item.food.FoodItemType.FOOD_ITEMS;

public record TypesItem<I extends Item>(List<IdentifiableItem<I>> items) {
    public static final TypesItem<?>[] ALL_ITEMS;


    static {
        ALL_ITEMS = new TypesItem<?>[] {
                FOOD_ITEMS
        };
    }

}
