package net.fluffybumblebee.terrains.util.registration.registry_set.helper;

import java.util.List;

@FunctionalInterface
public interface Quickerator<T> {
    List<T> getValues();

    default void forEach(final ElementProvider<T> provider) {
        for (T type : getValues()) {
            if (type != null)
                provider.with(type);
        }
    }

    @FunctionalInterface
    interface ElementProvider<T> {
        void with(final T element);
    }
}
