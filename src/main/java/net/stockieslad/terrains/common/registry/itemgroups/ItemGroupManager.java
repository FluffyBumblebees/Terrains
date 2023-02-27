package net.stockieslad.terrains.common.registry.itemgroups;

public class ItemGroupManager {
    public static void init() {
        UndergroundItemGroup.register();
        NatureItemGroup.register();
        FoodItemGroup.register();
        SkiesItemGroup.register();
    }
}
