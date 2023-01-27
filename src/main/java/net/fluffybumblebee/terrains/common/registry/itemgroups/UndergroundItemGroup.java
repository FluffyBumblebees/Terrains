package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode.PURPLE;


public class UndergroundItemGroup {
    public static void register() {}
    public static final ItemGroup UNDERGROUND;

    static {
        UNDERGROUND = FabricItemGroupBuilder.create(new Identifier(TerrainsDefaults.NAMESPACE, "underground"))
                .icon(() -> new ItemStack(CRYSTAL_GEODES.getTypeMap().get(PURPLE).CORUNDUM.ITEM))
                .appendItems(stacks -> CRYSTAL_GEODES.iterateRegistry(RegistryTypes.CRYSTAL).forEach(element ->
                        stacks.add(element.item().orElseThrow().getDefaultStack())
                )
                ).build();
    }
}
