package net.fluffybumblebee.terrains.common.registry.sets.item;

import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.Quickerator;
import net.fluffybumblebee.terrains.util.registration.feature_set.RegistrySetCreator;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistrySetConfig<I extends Item> implements RegistrySetCreator {
    private final List<Item> ALL_ITEMS;

    public ItemRegistrySetConfig(ItemTypes<I> items) {
        ALL_ITEMS = new ArrayList<>();

        final Quickerator<IdentifiableItem<I>> iterator = items::items;
        iterator.forEach(element -> {
            Registry.register(Registry.ITEM, element.identifier, element.item);
            ALL_ITEMS.add(element.item);
        });
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return List.of();
    }

    @Override
    public List<Item> getAllItems() {
        return ALL_ITEMS;
    }

    public static <I extends Item> IdentifiableItem<I> getItem(I item, String name) {
        return new IdentifiableItem<>(item, TerrainsDefaults.getIdentifier(name));
    }

    public record IdentifiableItem<I extends Item>(I item, Identifier identifier) {}
}
