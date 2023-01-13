package net.fluffybumblebee.terrains.util.registration.item;

import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ItemRegistration {
    public static <I extends Item> I create(I item, String name, List<I> collection) {
        collection.add(item);
        Registry.register(Registry.ITEM, new Identifier(TerrainsDefaults.NAMESPACE, name), item);
        return item;
    }
}
