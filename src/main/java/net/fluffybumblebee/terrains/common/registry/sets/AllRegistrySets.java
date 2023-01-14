package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeRegistrySetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.FullTreeRegistrySetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.AllTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.TreeType;
import net.fluffybumblebee.terrains.common.registry.sets.item.ItemRegistrySetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.item.ItemTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeRegistrySetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.util.registration.feature_set.RegistrySet;

public final class AllRegistrySets {
    public static final RegistrySet<StainedTreeTypes, StainedTreeRegistrySetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final RegistrySet<CrystalGeodeTypes, CrystalGeodeRegistrySetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;
    public static final RegistrySet<TreeType<?>, FullTreeRegistrySetConfig<?>> FULL_TREE_SETS;
    public static final RegistrySet<ItemTypes<?>, ItemRegistrySetConfig<?>> ITEMS;


    static {
        STAINED_TREES = new RegistrySet<>(StainedTreeTypes.values(), StainedTreeRegistrySetConfig::new);
        CRYSTAL_GEODES = new RegistrySet<>(CrystalGeodeTypes.values(), CrystalGeodeRegistrySetConfig::new);
        FULL_TREE_SETS = new RegistrySet<>(AllTreeTypes.getTypes().getAllEntries(), FullTreeRegistrySetConfig::new);
        ITEMS = new RegistrySet<>(ItemTypes.ALL_ITEMS, ItemRegistrySetConfig::new);
    }

    private AllRegistrySets() {}
    public static void register() {}
}
