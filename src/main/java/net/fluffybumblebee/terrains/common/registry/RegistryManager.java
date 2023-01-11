package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets;
import net.fluffybumblebee.terrains.common.registry.itemgroup.NatureItemGroup;
import net.fluffybumblebee.terrains.common.registry.itemgroup.UndergroundItemGroup;

public class RegistryManager {
    public static void init() {
        registerBlocks();
        registerItems();
        registerWorldGen();
        registerSets();
        registerItemGroups();
    }

    public static void registerBlocks() {
    }

    public static void registerItems() {
    }

    public static void registerWorldGen() {
    }

    public static void registerSets() {
        AllFeatureSets.register();
    }

    public static void registerItemGroups() {
        UndergroundItemGroup.register();
        NatureItemGroup.register();
    }
}
