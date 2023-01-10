package net.fluffybumblebee.maple_forest.util.registration.item;

import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MFItemRegistration {
    public static <I extends Item> I register(I item, String name) {
        Registry.register(Registry.ITEM, new Identifier(MapleForest.NAMESPACE, name), item);
        return item;
    }
}
