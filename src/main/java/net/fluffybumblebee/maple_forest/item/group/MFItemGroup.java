package net.fluffybumblebee.maple_forest.item.group;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class MFItemGroup {
    public static void initClass() {}

    public static final ItemGroup MAPLE_FOREST = FabricItemGroupBuilder.create(
            new Identifier(TerrainsDefaults.NAMESPACE, TerrainsDefaults.NAMESPACE)
                ).icon(() ->
                        new ItemStack(MFRegistry.RED_MAPLE_LEAVES)
                ).appendItems(stacks -> Registry.ITEM.forEach(item -> {
                if (Registry.ITEM.getId(item).getNamespace().equals(TerrainsDefaults.NAMESPACE)) {
                    item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
                    }})).build();
}
