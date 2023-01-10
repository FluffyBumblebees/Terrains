package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.common.registry.itemgroup.CrystalItemGroups;
import net.fluffybumblebee.terrains.common.registry.blocks.CrystalBlocks;

public class RegistryManager {
    public static void registerBlocks() {
        CrystalBlocks.register();
    }

    public static void registerItems() {

    }

    public static void registerItemGroups() {
        CrystalItemGroups.registerItemgroup();
    }
}
