package net.fluffybumblebee.terrains.util.registration.feature_set;

import java.util.List;

public interface FeatureRegistrar<T> {
    List<T> getAll();

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
