package net.fluffybumblebee.maple_forest.item.group;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class MFItemGroup {
    public static void initClass() {}

    public static final ItemGroup MAPLE_FOREST = FabricItemGroupBuilder.create(
            new Identifier(MapleForest.NAMESPACE, MapleForest.NAMESPACE)
                ).icon(() ->
                        new ItemStack(MFRegistry.RED_MAPLE_LEAVES)
                ).appendItems(stacks -> Registry.ITEM.forEach(item -> {
                if (Registry.ITEM.getId(item).getNamespace().equals(MapleForest.NAMESPACE)) {
                    item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
                    }})).build();
}
