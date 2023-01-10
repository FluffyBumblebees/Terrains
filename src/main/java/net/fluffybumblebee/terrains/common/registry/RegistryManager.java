package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.stainedtrees.item.StainedItemGroup;
import net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks;
import net.fluffybumblebee.terrains.common.registry.category.CrystalGeodes;
import net.fluffybumblebee.terrains.common.registry.itemgroup.CrystalItemGroup;
import net.fluffybumblebee.terrains.common.registry.world.gen.StainedTreesWorldGen;

public class RegistryManager {
    public static void registerBlocks() {
        StainedBlocks.register();
    }

    public static void registerItems() {
    }

    public static void registerItemGroups() {
        CrystalItemGroup.register();
        StainedItemGroup.register();
    }

    public static void registerWorldGen() {
        StainedTreesWorldGen.register();
        CrystalGeodes.register();
    }
}
