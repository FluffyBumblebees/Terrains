package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;

import static net.fluffybumblebee.terrains.common.registry.sets.item.food.FoodItemType.HONEYCOMB_PANCAKE_SANDWICH;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

@SuppressWarnings("UnstableApiUsage")
public class FoodItemGroup {
    public static void register() {}

    public static final ItemGroup FOOD;

    static {
        FOOD = new FabricItemGroupBuilderImpl(getIdentifier("foods"))
                .icon(() -> HONEYCOMB_PANCAKE_SANDWICH.item().getDefaultStack())
                .entries((enabledFeatures, entries, operatorEnabled) -> {
                    AllRegistrySets.ITEMS.iterateRegistry(RegistryTypes.FOOD).forEach(element ->
                            entries.add(element.item().orElseThrow().getDefaultStack())
                    );
                    AllRegistrySets.ITEMS.iterateRegistry(RegistryTypes.TRANSPARENT_FOOD).forEach(element ->
                            entries.add(element.item().orElseThrow().getDefaultStack()));
                })
                .build();
    }

}
