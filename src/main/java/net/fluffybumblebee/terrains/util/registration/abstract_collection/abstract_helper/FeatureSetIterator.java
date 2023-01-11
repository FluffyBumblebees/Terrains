package net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper;

import java.util.List;

public interface FeatureSetIterator<E> {
    List<E> getValues();

    default void forEach(TypeIterator<E> iterator) {
        for (E colour : getValues()) {
            iterator.with(colour);
        }
    }

    interface TypeIterator <E> {
        void with(E element);
    }
}
