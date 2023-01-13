package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.item.Item;

import java.util.*;

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

    @Override
    public BasicIterator<Item> getItemIterator() {
        List<Item> list = new ArrayList<>();
        getIterator().forEach(element -> list.addAll(getTypes().get(element).getAllItems()));
        return () -> list;
    }

    public interface FeatureSetFactory<E, F extends FeatureRegistrar<BlockSet<?>>> {
        F getNewSet(E type);
    }
}
