package net.stockieslad.magical_utilities.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockHelper {
    public static void registerBlockAndItem(Identifier identifier, Block block, Item item) {
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, item);
    }
}
