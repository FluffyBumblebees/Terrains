package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.minecraft.item.Item;

import java.util.*;

public class RegistrySet<Types, RegistryConfig extends SetRegistrar> implements AbstractRegistrySet<Types, RegistryConfig> {
    private final BasicIterator<Types> ITERATOR;
    private final Map<Types, RegistryConfig> ALL_TYPES;
    public RegistrySet(List<Types> values, FeatureSetFactory<Types, RegistryConfig> factory) {
        ALL_TYPES = new HashMap<>();
        ITERATOR = () -> values;
        ITERATOR.forEach(element -> {
            RegistryConfig set = factory.getNewSet(element);
            set.generationEvent();
            ALL_TYPES.put(element, set);
        });
    }

    public RegistrySet(Types[] values, FeatureSetFactory<Types, RegistryConfig> factory) {
        this(Arrays.asList(values), factory);
    }

    @Override
    public BasicIterator<Types> getIterator() {
        return ITERATOR;
    }

    @Override
    public Map<Types, RegistryConfig> getTypes() {
        return ALL_TYPES;
    }

    @Override
    public BasicIterator<Item> getItemIterator() {
        List<Item> list = new ArrayList<>();
        getIterator().forEach(element -> list.addAll(getTypes().get(element).getAllItems()));
        return () -> list;
    }

    public interface FeatureSetFactory<Types, RegistryConfig extends SetRegistrar> {
        RegistryConfig getNewSet(Types type);
    }
}
