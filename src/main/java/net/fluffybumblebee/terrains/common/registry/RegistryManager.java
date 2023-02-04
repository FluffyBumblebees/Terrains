package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.common.registry.itemgroups.FoodItemGroup;
import net.fluffybumblebee.terrains.common.registry.itemgroups.NatureItemGroup;
import net.fluffybumblebee.terrains.common.registry.itemgroups.SkiesItemGroup;
import net.fluffybumblebee.terrains.common.registry.itemgroups.UndergroundItemGroup;
import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.world.WorldManager;

public class RegistryManager {
    public static void init() {
        registerSets();
        registerItemGroups();
        registerWorldGen();
    }


    public static void registerSets() {
        AllRegistrySets.register();
    }

    public static void registerItemGroups() {
        UndergroundItemGroup.register();
        NatureItemGroup.register();
        FoodItemGroup.register();
        SkiesItemGroup.register();
    }
    public static void registerWorldGen() {
        WorldManager.init();
    }

}
