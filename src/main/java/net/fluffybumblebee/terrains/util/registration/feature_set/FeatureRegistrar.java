package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.minecraft.item.Item;

import java.util.List;

public interface FeatureRegistrar<T> extends BasicIterator<Item> {
    List<T> getAll();

    @Override
    default List<Item> getValues() {
        return getAllItems();
    }

    default List<Item> getAllItems() {
        return List.of();
    }

    default void optionalGenerationEvent() {}

    default BasicIterator<T> getIterator() {
        return this::getAll;
    }

    default void addAll(T[] all) {
        for (T element : all) {
            getAll().add(element);
        }
    }
}
