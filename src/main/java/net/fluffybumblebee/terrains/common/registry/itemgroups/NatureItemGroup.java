package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;
    public static final List<BlockSet<?>> BLOCKS_IN_ORDER;

    static {
        BLOCKS_IN_ORDER = addInOrder(FULL_TREES, STAINED_TREES, FOLIAGE);
        NATURE = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(STAINED_TREES.getTypeMap().get(PURPLE).TREE_CONFIG.LEAVES.ITEM))
                .appendItems(stacks -> {
                    for (BlockSet<?> set : BLOCKS_IN_ORDER) {
                        stacks.add(set.ITEM.getDefaultStack());
                    }
                }).build();
    }

    private static List<BlockSet<?>> addInOrder(RegistrySet<?, ?>... registrySets) {
        final List<BlockSet<?>> blockSets = new ArrayList<>();

        //Order
        final List<BlockSet<?>> woods = new ArrayList<>();
        final List<BlockSet<?>> leaves = new ArrayList<>();
        final List<BlockSet<?>> saplings = new ArrayList<>();
        final List<BlockSet<?>> foliage = new ArrayList<>();

        for (RegistrySet<?, ?> registrySet : registrySets) {
            registrySet.forEach(element -> {
                if (element.ITEM != null) {
                    if (element.BLOCK instanceof LeavesBlock)
                        leaves.add(element);
                    else if (element.BLOCK instanceof SaplingBlock)
                        saplings.add(element);
                    else if (element.BLOCK instanceof FlowerBlock)
                        foliage.add(element);
                    else {
                        woods.add(element);
                    }
                }
            });
        }
        return addAllAll(blockSets, woods, leaves, saplings, foliage);
    }

    @SafeVarargs
    private static List<BlockSet<?>> addAllAll(List<BlockSet<?>> main, List<BlockSet<?>>... lists) {
        for (List<BlockSet<?>> list : lists) {
            main.addAll(list);
        }
        return main;
    }
}
