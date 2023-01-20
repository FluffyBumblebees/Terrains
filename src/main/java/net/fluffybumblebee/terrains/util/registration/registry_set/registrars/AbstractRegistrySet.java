package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AbstractRegistrySet<Types, RegistryConfig extends RegistrySetCreator> extends Quickerator<BlockSet<?>> {
    Quickerator<Types> getIterator();

    Map<Types, RegistryConfig> getTypeMap();

    Quickerator<Item> getItemIterator();

    default List<BlockSet<?>> getAllBlocks() {
        List<BlockSet<?>> list = new ArrayList<>();
        getIterator().forEach(colour -> list.addAll(getTypeMap().get(colour).getAllBlockSets()));
        return list;
    }

    @Override
    default List<BlockSet<?>> getValues() {
        return getAllBlocks();
    }
}
