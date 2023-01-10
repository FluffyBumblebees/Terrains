package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.default_abstract.block.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.TerrainsDefaults.NAMESPACE;
import static net.fluffybumblebee.terrains.common.registry.category.StainedCollections.AllColours.PURPLE;
import static net.fluffybumblebee.terrains.common.registry.category.StainedCollections.allStainedTrees;
import static net.fluffybumblebee.terrains.common.registry.category.StainedCollections.getAllRegistryEntries;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(allStainedTrees.get(PURPLE).LEAVES.getBlockItem().asItem()))
                .appendItems(stacks -> {
                    for (BlockBuilder<?> builder : getAllRegistryEntries()) {
                        if (!(builder.getBlock() instanceof ShortenedFlowerPotBlock)) {
                            stacks.add(builder.getBlockItem().asItem().getDefaultStack());
                        }
                    }
                }).build();
    }
}
