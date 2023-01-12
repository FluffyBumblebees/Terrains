package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FeatureSet<Type, FeatureConfig extends FeatureRegistrar<BlockSet<?>>> implements AbstractFeatureSet<Type, FeatureConfig> {
    private final BasicIterator<Type> ITERATOR;
    private final Map<Type, FeatureConfig> ALL_TYPES;
    public FeatureSet(Type[] values, FeatureSetFactory<Type, FeatureConfig> factory) {
        ALL_TYPES = new HashMap<>();
        ITERATOR = () -> Arrays.asList(values);
        ITERATOR.forEach(element -> {
            FeatureConfig set = factory.getNewSet(element);
            set.optionalGenerationEvent();
            ALL_TYPES.put(element, set);
        });
    }

    @Override
    public BasicIterator<Type> getIterator() {
        return ITERATOR;
    }

    @Override
    public Map<Type, FeatureConfig> getTypes() {
        return ALL_TYPES;
    }

    public interface FeatureSetFactory<E, F extends FeatureRegistrar<BlockSet<?>>> {
        F getNewSet(E type);
    }
}
