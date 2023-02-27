package net.stockieslad.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.stockieslad.terrains.common.registry.sets.RegistrySetManager.CRYSTAL_GEODES;
import static net.stockieslad.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode.PURPLE;


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
