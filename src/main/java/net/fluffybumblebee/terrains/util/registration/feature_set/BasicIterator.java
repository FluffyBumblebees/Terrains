package net.fluffybumblebee.terrains.util.registration.feature_set;

import java.util.List;

public interface BasicIterator<T> {
    List<T> getValues();

    default void forEach(TypeIterator<T> iterator) {
        for (T colour : getValues()) {
            iterator.with(colour);
        }
    }

    interface TypeIterator <E> {
        void with(E element);
    }
}
