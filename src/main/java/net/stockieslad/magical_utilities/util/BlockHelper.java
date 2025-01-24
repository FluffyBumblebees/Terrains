package net.stockieslad.magical_utilities.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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

    public static List<BlockPos> testSides(BlockPos pos, Predicate<BlockPos> condition) {
        var passed = new LinkedList<>(Arrays.stream(Direction.values()).map(pos::offset).filter(condition).toList());
        if (condition.test(pos))
            passed.add(pos);
        return passed;
    }
}
