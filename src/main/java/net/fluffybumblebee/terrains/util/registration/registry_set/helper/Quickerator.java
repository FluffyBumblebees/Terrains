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

    static <T> Quickerator<T> of(final List<T> list) {
        return () -> list;
    }

    static <T> Quickerator<T> of(final boolean condition, final List<T> list) {
        return condition ? of(list) : of(List.of());
    }

    @FunctionalInterface
    interface ElementProvider<T> {
        void with(final T element);
    }
}
