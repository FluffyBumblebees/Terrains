package net.stockieslad.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.stockieslad.terrains.common.registry.sets.clouds.TypesCloudSet;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.stockieslad.terrains.common.registry.sets.RegistrySetManager.CLOUDS;


public class SkiesItemGroup {
    public static void register() {}
    public static final ItemGroup SKIES;

    static {
        SKIES = FabricItemGroupBuilder.create(new Identifier(TerrainsDefaults.NAMESPACE, "skies"))
                .icon(() -> new ItemStack(CLOUDS.getTypeMap().get(TypesCloudSet.BLUE).cloudBlock.ITEM))
                .appendItems(stacks -> CLOUDS.iterateRegistry(element -> element.forEach(nextElement ->
                        stacks.add(nextElement.item().orElseThrow().getDefaultStack())
                ))).build();
    }
}
