package net.stockieslad.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.stockieslad.terrains.common.registry.sets.RegistrySets;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import static net.stockieslad.terrains.common.registry.sets.item.food.FoodItemType.HONEYCOMB_PANCAKE_SANDWICH;
import static net.stockieslad.terrains.core.TerrainsDefaults.NAMESPACE;

public class FoodItemGroup {
    public static void register() {}

    public static final ItemGroup FOOD;

    static {
        FOOD = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "foods"))
                .icon(() -> HONEYCOMB_PANCAKE_SANDWICH.item().getDefaultStack())
                .appendItems(stacks -> {
                    RegistrySets.ITEMS.iterateRegistry(RegistryTypes.FOOD).forEach(element ->
                            stacks.add(element.item().orElseThrow().getDefaultStack())
                    );
                    RegistrySets.ITEMS.iterateRegistry(RegistryTypes.TRANSPARENT_FOOD).forEach(element ->
                            stacks.add(element.item().orElseThrow().getDefaultStack()));
                })
                .build();
    }

}
