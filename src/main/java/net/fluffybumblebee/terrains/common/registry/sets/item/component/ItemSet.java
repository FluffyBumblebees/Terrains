package net.fluffybumblebee.terrains.common.registry.sets.item.component;

import net.fluffybumblebee.terrains.common.registry.sets.item.TypesItem;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

public class ItemSet<I extends Item> implements RegistrySetCreator {
    private final TypesItem<I> items;

    public ItemSet(TypesItem<I> items) {
        this.items = items;
    }

    public static <I extends Item> IdentifiableItem<I> getItem(I item, String name) {
        return new IdentifiableItem<>(item, getIdentifier(name));
    }

    @Override
    public void registryEvent(SetRegistry registry) {
        registry.iterate(items.items()).forEach(element -> {
            Registry.register(Registry.ITEM, element.identifier, element.item);
            registry.storage.get(items.types()).add(new SetRegistry.Storage(element.item));
        });
    }

    public record IdentifiableItem<I extends Item>(I item, Identifier identifier) {}
}
