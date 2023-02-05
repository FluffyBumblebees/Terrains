package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.registry.sets.RegistrySetManager;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.item.food.FoodItemType.HONEYCOMB_PANCAKE_SANDWICH;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class FoodItemGroup {
    public static void register() {}

    public static final ItemGroup FOOD;

    static {
        FOOD = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "foods"))
                .icon(() -> HONEYCOMB_PANCAKE_SANDWICH.item().getDefaultStack())
                .appendItems(stacks -> {
                    RegistrySetManager.ITEMS.iterateRegistry(RegistryTypes.FOOD).forEach(element ->
                            stacks.add(element.item().orElseThrow().getDefaultStack())
                    );
                    RegistrySetManager.ITEMS.iterateRegistry(RegistryTypes.TRANSPARENT_FOOD).forEach(element ->
                            stacks.add(element.item().orElseThrow().getDefaultStack()));
                })
                .build();
    }

}
