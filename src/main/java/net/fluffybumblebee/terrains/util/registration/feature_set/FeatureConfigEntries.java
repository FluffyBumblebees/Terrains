package net.fluffybumblebee.terrains.util.registration.feature_set;

import java.util.Arrays;
import java.util.List;

public class FeatureConfigEntries<T> implements BasicIterator<T> {
    private final T[] ALL_ENTRIES;

    public FeatureConfigEntries(T[] allEntries) {
        ALL_ENTRIES = allEntries;
    }

    public T[] getAllEntries() {
        return ALL_ENTRIES;
    }

    @Override
    public List<T> getValues() {
        return Arrays.asList(ALL_ENTRIES);
    }
}
