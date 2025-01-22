package net.stockieslad.magical_utilities.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;

public class BlockHelper {
    public static void registerBlockAndItem(Identifier identifier, Block block, Item item) {
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, item);
    }

    @SafeVarargs
    public static <T> DataPool<T> arrayToPool(T... array) {
        DataPool.Builder<T> builder = DataPool.builder();
        for (T element : array) {
            builder.add(element, 1);
        }
        return builder.build();
    }
}
