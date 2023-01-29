package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;


@SuppressWarnings("UnstableApiUsage")
public class UndergroundItemGroup {
    public static void register() {}
    public static final ItemGroup UNDERGROUND;

    static {
        UNDERGROUND = new FabricItemGroupBuilderImpl(getIdentifier("underground"))
                .icon(() -> new ItemStack(CRYSTAL_GEODES.getTypeMap().get(PURPLE).CORUNDUM.ITEM))
                .entries((enabledFeatures, entries, operatorEnabled) -> CRYSTAL_GEODES.iterateRegistry(RegistryTypes.CRYSTAL)
                                .forEach(element -> entries.add(element.item().orElseThrow().getDefaultStack())
                )
                ).build();
    }
}
