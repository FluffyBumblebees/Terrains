package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.AllTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.TreeType;
import net.fluffybumblebee.terrains.common.registry.sets.item.ItemSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.item.ItemTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.util.registration.feature_set.RegistrySet;

public final class AllRegistrySets {
    public static final RegistrySet<StainedTreeTypes, StainedTreeSetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final RegistrySet<CrystalGeodeTypes, CrystalGeodeSetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;
    public static final RegistrySet<TreeType<?>, FullTreeSetConfig<?>> FULL_TREE_SETS;
    public static final RegistrySet<ItemTypes<?>, ItemSetConfig<?>> ITEMS;


    static {
        STAINED_TREES = new RegistrySet<>(StainedTreeTypes.values(), StainedTreeSetConfig::new);
        CRYSTAL_GEODES = new RegistrySet<>(CrystalGeodeTypes.values(), CrystalGeodeSetConfig::new);
        FULL_TREE_SETS = new RegistrySet<>(AllTreeTypes.getTypes().getAllEntries(), FullTreeSetConfig::new);
        ITEMS = new RegistrySet<>(ItemTypes.ALL_ITEMS, ItemSetConfig::new);
    }

    private AllRegistrySets() {}
    public static void register() {}
}
