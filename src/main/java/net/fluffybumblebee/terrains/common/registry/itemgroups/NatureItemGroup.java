package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.common.instances.block.wood.Wood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;
import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.EasyIf.onIf;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(STAINED_TREES.getTypeMap().get(PURPLE).TREE_CONFIG.LEAVES.ITEM))
                .appendItems(stacks -> {
                    FULL_TREES.addAllToStack(stacks, true, true,
                            (element, booleanHolder, iterator, itemConditions, blockConditions) -> {
                        if (!(element.BLOCK instanceof Wood) && booleanHolder.bool) {
                            iterator.forEach(variant -> onIf(itemConditions, () -> stacks.add(variant.getDefaultStack())));
                            booleanHolder.bool = false;
                        }
                    });
                    STAINED_TREES.forEach(element -> {
                        if (!(element.BLOCK instanceof ConfiguredFlowerPotBlock)) {
                            stacks.add(element.ITEM.getDefaultStack());
                        }
                    });
                }).build();
    }
}
