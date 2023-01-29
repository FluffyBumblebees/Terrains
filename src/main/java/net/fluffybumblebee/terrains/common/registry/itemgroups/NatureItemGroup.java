package net.fluffybumblebee.terrains.common.registry.itemgroups;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree.PURPLE;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

@SuppressWarnings("UnstableApiUsage")
public class NatureItemGroup {
    public static void register() {}
    public static final ItemGroup NATURE;

    static {
        NATURE = new FabricItemGroupBuilderImpl(getIdentifier("nature"))
                .icon(() -> new ItemStack(STAINED_TREES.getTypeMap().get(PURPLE).TREE_CONFIG.LEAVES.ITEM))
                .entries((enabledFeatures, entries, operatorEnabled) -> {
                    FULL_TREES.iterateRegistry(RegistryTypes.ALL_WOOD_BLOCKS).forEach(element -> entries.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    FULL_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> entries.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    STAINED_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> entries.add(element
                            .item().orElseThrow().getDefaultStack()));
                    FULL_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> entries.add(element.item()
                            .orElseThrow().getDefaultStack()));
                    STAINED_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> entries.add(element
                            .item().orElseThrow().getDefaultStack()));
                    FOLIAGE.iterateRegistry(RegistryTypes.FLOWER).forEach(element -> entries.add(element
                            .item().orElseThrow().getDefaultStack()));
                }).build();
    }
}
