package net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultTypeFeatureSet<E extends Enum<?>, F extends FeatureRegistrar> implements AbstractFeatureSet<E, F> {
    private final FeatureSetIterator<E> ITERATOR;
    private final Map<E, F> ALL_TYPES;
    public MultTypeFeatureSet(E[] values, FeatureSetFactory<E, F> factory) {
        ALL_TYPES = new HashMap<>();
        ITERATOR = () -> Arrays.asList(values);
        ITERATOR.forEach(colour -> ALL_TYPES.put(colour, factory.getNewSet(colour)));
    }

    @Override
    public FeatureSetIterator<E> getIterator() {
        return ITERATOR;
    }

    @Override
    public Map<E, F> getTypes() {
        return ALL_TYPES;
    }

    public interface FeatureSetFactory< E extends Enum<?>, F extends FeatureRegistrar> {
        F getNewSet(E type);
    }
}
