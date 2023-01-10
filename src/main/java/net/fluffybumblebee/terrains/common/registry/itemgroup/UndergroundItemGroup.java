package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.category.GeodeCollections.AllColours.PURPLE;
import static net.fluffybumblebee.terrains.common.registry.category.GeodeCollections.allGeodes;
import static net.fluffybumblebee.terrains.common.registry.category.GeodeCollections.getAllRegistryEntries;


public class UndergroundItemGroup {
    public static void register() {}
    public static final ItemGroup underground;

    static {
        underground = FabricItemGroupBuilder.create(new Identifier(TerrainsDefaults.NAMESPACE, "underground"))
                .icon(() -> new ItemStack(allGeodes.get(PURPLE).corundumBlock.getBlockItem().asItem()))
                .appendItems(stacks -> {
                    for (BlockBuilder<?> builder : getAllRegistryEntries()) {
                        stacks.add(builder.getBlockItem().asItem().getDefaultStack());
                    }
                }).build();
    }
}
