package net.fluffybumblebee.terrains.util.registration.item;

import net.fluffybumblebee.terrains.TerrainsDefaults;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MFItemRegistration {
    public static <I extends Item> I register(I item, String name) {
        Registry.register(Registry.ITEM, new Identifier(TerrainsDefaults.NAMESPACE, name), item);
        return item;
    }
}
