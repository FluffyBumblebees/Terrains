package net.fluffybumblebee.terrains.common.registry.sets.item;

import net.fluffybumblebee.terrains.common.instances.item.food.FoodItem;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.item.FoodItemType.FoodItems.*;
import static net.fluffybumblebee.terrains.common.registry.sets.item.ItemSetConfig.getItem;

public class FoodItemType {
    public static final ItemTypes<FoodItem> FOOD_ITEMS;

    static {
        FOOD_ITEMS = new ItemTypes<>(
                List.of(
                        PANCAKE_DOUGH.ITEM,
                        MAPLE_SAP.ITEM,
                        PANCAKE.ITEM,
                        HONEY_FLAVOURED_PANCAKE.ITEM,
                        MAPLE_FLAVOURED_PANCAKE.ITEM,
                        HONEYCOMB_PANCAKE_SANDWICH.ITEM
                )
        );
    }

    public enum FoodItems {
        PANCAKE_DOUGH(getItem(new FoodItem(2, 0.5F), "pancake_dough")),
        MAPLE_SAP(getItem(new FoodItem(4, 0.5F), "maple_sap")),
        PANCAKE(getItem(new FoodItem(4, 0.5F), "pancake")),
        HONEY_FLAVOURED_PANCAKE(getItem(new FoodItem(6, 0.5F), "honey_flavoured_pancake")),
        MAPLE_FLAVOURED_PANCAKE(getItem(new FoodItem(6, 0.5F), "maple_flavoured_pancake")),
        HONEYCOMB_PANCAKE_SANDWICH(getItem(new FoodItem(8, 0.5F), "honeycomb_pancake_sandwich"));
        public final ItemSetConfig.IdentifiableItem<FoodItem> ITEM;

        FoodItems(ItemSetConfig.IdentifiableItem<FoodItem> item) {
            ITEM = item;
        }
    }
}
