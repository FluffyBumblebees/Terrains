package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "nature"))
                .icon(() -> new ItemStack(STAINED_TREES.getTypeMap().get(PURPLE).TREE_CONFIG.LEAVES.ITEM))
                .appendItems(stacks -> {
                    FULL_TREES.iterateRegistry(RegistryTypes.ALL_WOOD_BLOCKS).forEach(element -> stacks.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    FULL_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> stacks.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    STAINED_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> stacks.add(element
                            .item().orElseThrow().getDefaultStack()));
                    FULL_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> stacks.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    STAINED_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> stacks.add(element
                            .item().orElseThrow().getDefaultStack()));
                    FOLIAGE.iterateRegistry(RegistryTypes.FLOWER).forEach(element -> stacks.add(element
                            .item().orElseThrow().getDefaultStack()));
                }).build();
    }
}
