package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface AbstractFeatureSet<E extends Enum<?>, C extends FeatureRegistrar> extends FeatureSetIterator<BlockSet<?>> {
    FeatureSetIterator<E> getIterator();

    Map<E, C> getTypes();

    default List<BlockSet<?>> getAllRegistryEntries() {
        List<BlockSet<?>> list = new ArrayList<>();
        getIterator().forEach(colour -> list.addAll(Arrays.asList(getTypes().get(colour).getAllBlocks())));
        return list;
    }

    @Override
    default List<BlockSet<?>> getValues() {
        return getAllRegistryEntries();
    }
}
