package net.fluffybumblebee.terrains.common.registry.sets.item.food;

import net.fluffybumblebee.terrains.common.instances.item.food.FoodItem;
import net.fluffybumblebee.terrains.common.registry.sets.item.TypesItem;
import net.fluffybumblebee.terrains.common.registry.sets.item.component.ItemSet.IdentifiableItem;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.item.component.ItemSet.getItem;

public class FoodItemType {

    public static final IdentifiableItem<FoodItem> PANCAKE_DOUGH = getItem(new FoodItem(2), "pancake_dough");
    public static final IdentifiableItem<FoodItem> MAPLE_SAP = getItem(new FoodItem(3), "maple_sap");
    public static final IdentifiableItem<FoodItem> PANCAKE = getItem(new FoodItem(4), "pancake");
    public static final IdentifiableItem<FoodItem> HONEY_FLAVOURED_PANCAKE = getItem(new FoodItem(6),
            "honey_flavoured_pancake");
    public static final IdentifiableItem<FoodItem> MAPLE_FLAVOURED_PANCAKE = getItem(new FoodItem(6),
            "maple_flavoured_pancake");
    public static final IdentifiableItem<FoodItem> HONEYCOMB_PANCAKE_SANDWICH = getItem(new FoodItem(8),
            "honeycomb_pancake_sandwich");

    public static final TypesItem<FoodItem> FOOD_ITEMS = new TypesItem<>(
            List.of(
                    PANCAKE_DOUGH,
                    MAPLE_SAP,
                    PANCAKE,
                    HONEY_FLAVOURED_PANCAKE,
                    MAPLE_FLAVOURED_PANCAKE,
                    HONEYCOMB_PANCAKE_SANDWICH
            )
    );
}
