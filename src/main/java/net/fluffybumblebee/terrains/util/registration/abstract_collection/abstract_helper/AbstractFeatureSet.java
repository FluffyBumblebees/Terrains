package net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper;

import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface AbstractFeatureSet<E extends Enum<?>, C extends FeatureRegistrar> extends FeatureSetIterator<BlockBuilder<?>> {
    FeatureSetIterator<E> getIterator();

    Map<E, C> getTypes();

    default List<BlockBuilder<?>> getAllRegistryEntries() {
        List<BlockBuilder<?>> list = new ArrayList<>();
        getIterator().forEach(colour -> list.addAll(Arrays.asList(getTypes().get(colour).getAllBlocks())));
        return list;
    }

    @Override
    default List<BlockBuilder<?>> getValues() {
        return getAllRegistryEntries();
    }
}
