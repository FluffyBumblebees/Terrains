package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes.PURPLE;


public class UndergroundItemGroup {
    public static void register() {}
    public static final ItemGroup UNDERGROUND;

    static {
        UNDERGROUND = FabricItemGroupBuilder.create(new Identifier(TerrainsDefaults.NAMESPACE, "underground"))
                .icon(() -> new ItemStack(CRYSTAL_GEODES.getTypes().get(PURPLE).CORUNDUM.BLOCK_ITEM.asItem()))
                .appendItems(stacks ->
                        CRYSTAL_GEODES.forEach(element ->
                                stacks.add(element.BLOCK_ITEM.asItem().getDefaultStack())
                        )
                ).build();
    }
}
