package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FeatureSet<E extends Enum<?>, F extends FeatureRegistrar<BlockSet<?>>> implements AbstractFeatureSet<E, F> {
    private final BasicIterator<E> ITERATOR;
    private final Map<E, F> ALL_TYPES;
    public FeatureSet(E[] values, FeatureSetFactory<E, F> factory) {
        ALL_TYPES = new HashMap<>();
        ITERATOR = () -> Arrays.asList(values);
        ITERATOR.forEach(element -> {
            F set = factory.getNewSet(element);
            set.optionalGenerationEvent();
            ALL_TYPES.put(element, set);
        });
    }

    @Override
    public BasicIterator<E> getIterator() {
        return ITERATOR;
    }

    @Override
    public Map<E, F> getTypes() {
        return ALL_TYPES;
    }

    public interface FeatureSetFactory< E extends Enum<?>, F extends FeatureRegistrar<BlockSet<?>>> {
        F getNewSet(E type);
    }
}
