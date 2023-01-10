package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.common.registry.category.GeodeCollections;
import net.fluffybumblebee.terrains.common.registry.category.StainedCollections;
import net.fluffybumblebee.terrains.common.registry.itemgroup.NatureItemGroup;
import net.fluffybumblebee.terrains.common.registry.itemgroup.UndergroundItemGroup;

public class RegistryManager {
    public static void init() {
        registerBlocks();
        registerItems();
        registerWorldGen();
        registerCollection();
        registerItemGroups();
    }

    public static void registerBlocks() {
    }

    public static void registerItems() {
    }

    public static void registerWorldGen() {
    }

    public static void registerCollection() {
        GeodeCollections.register();
        StainedCollections.register();
    }

    public static void registerItemGroups() {
        UndergroundItemGroup.register();
        NatureItemGroup.register();
    }
}
