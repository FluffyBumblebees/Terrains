package net.fluffybumblebee.terrains.util.registration.feature_set;

import java.util.Arrays;
import java.util.List;

public class FeatureConfigEntries<T> implements BasicIterator<T> {
    public final T[] ALL_ENTRIES;

    public FeatureConfigEntries(T[] allEntries) {
        ALL_ENTRIES = allEntries;
    }

    @Override
    public List<T> getValues() {
        return Arrays.asList(ALL_ENTRIES);
    }
}
