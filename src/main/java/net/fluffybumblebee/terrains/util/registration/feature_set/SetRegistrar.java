package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.item.Item;

import java.util.List;

public interface SetRegistrar extends BasicIterator<Item> {
    List<BlockSet<?>> getAllBlockSets();

    List<Item> getAllItems();

    default void generationEvent() {}

    default BasicIterator<BlockSet<?>> getBlockIterator() {
        return this::getAllBlockSets;
    }
    default BasicIterator<Item> getItemIterator() {
        return this::getAllItems;
    }

    default void addAllBlocks(BlockSet<?>[] all) {
        for (BlockSet<?> element : all) {
            getAllBlockSets().add(element);
        }
    }

    @SuppressWarnings("unused")
    default void addAllItems(Item[] all) {
        for (Item element : all) {
            getAllItems().add(element);
        }
    }

    @Override
    default List<Item> getValues() {
        return getAllItems();
    }
}