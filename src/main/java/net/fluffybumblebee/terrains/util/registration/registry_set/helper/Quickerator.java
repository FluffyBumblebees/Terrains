package net.fluffybumblebee.terrains.util.registration.registry_set.helper;

import java.util.List;

@FunctionalInterface
public interface Quickerator<T> {
    List<T> getValues();

    default void forEach(TypeIterator<T> iterator) {
        for (T type : getValues()) {
            if (type != null)
                iterator.with(type);
        }
    }

    @FunctionalInterface
    interface TypeIterator <T> {
        void with(T element);
    }
}
