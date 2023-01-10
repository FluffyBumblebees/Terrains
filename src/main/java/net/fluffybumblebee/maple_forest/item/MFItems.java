package net.fluffybumblebee.maple_forest.item;

import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class MFItems {
    public static void addToRegistry() {}
    public static Item register(String name,Item item) {
        if  (item instanceof BoatItem) {
            Registry.register(Registry.ITEM, new Identifier(TerrainsDefaults.NAMESPACE, name + "_boat"), item);
            return item;
        } else Registry.register(Registry.ITEM, new Identifier(TerrainsDefaults.NAMESPACE, name), item);
        return item;
    }
}
