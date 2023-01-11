package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.instances.block.ShortenedFlowerPotBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.AllFeatureSets.STAINED_TREES;
import static net.fluffybumblebee.terrains.common.registry.sets.stained.StainedTreeTypes.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(STAINED_TREES.getTypes().get(PURPLE).LEAVES.blockItem.asItem()))
                .appendItems(stacks -> STAINED_TREES.forEach(element -> {
                    if (!(element.block instanceof ShortenedFlowerPotBlock)) {
                        stacks.add(element.blockItem.asItem().getDefaultStack());
                    }
                })).build();
    }
}
