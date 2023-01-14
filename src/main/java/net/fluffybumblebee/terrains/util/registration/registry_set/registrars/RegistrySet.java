package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.minecraft.item.Item;

import java.util.*;

public class RegistrySet<Types, RegistryConfig extends RegistrySetCreator> implements AbstractRegistrySet<Types, RegistryConfig> {
    private final Quickerator<Types> ITERATOR;
    private final Map<Types, RegistryConfig> TYPE_MAP;
    public RegistrySet(List<Types> values, RegistrySetFactory<Types, RegistryConfig> factory) {
        TYPE_MAP = new HashMap<>();
        ITERATOR = () -> values;
        ITERATOR.forEach(element -> {
            RegistryConfig set = factory.getNewSet(element);
            set.generationEvent();
            TYPE_MAP.put(element, set);
        });
    }

    public RegistrySet(Types[] values, RegistrySetFactory<Types, RegistryConfig> factory) {
        this(Arrays.asList(values), factory);
    }

    @Override
    public Quickerator<Types> getIterator() {
        return ITERATOR;
    }

    @Override
    public Map<Types, RegistryConfig> getTypeMap() {
        return TYPE_MAP;
    }

    @Override
    public Quickerator<Item> getItemIterator() {
        List<Item> list = new ArrayList<>();
        getIterator().forEach(element -> list.addAll(getTypeMap().get(element).getAllItems()));
        return () -> list;
    }

    public interface RegistrySetFactory<Types, RegistryConfig extends RegistrySetCreator> {
        RegistryConfig getNewSet(Types type);
    }
}
