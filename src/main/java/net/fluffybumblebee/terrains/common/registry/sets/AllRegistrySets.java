package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.component.CrystalGeodeSet;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.TypesWholeTree;
import net.fluffybumblebee.terrains.common.registry.sets.item.component.ItemRegistrySet;
import net.fluffybumblebee.terrains.common.registry.sets.item.food.TypesItem;
import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.StainedTreeType;
import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;

public final class AllRegistrySets {
    public static final RegistrySet<TypesStainedTree, StainedTreeType<TypesStainedTree>> STAINED_TREES;
    public static final RegistrySet<TypesCrystalGeode, CrystalGeodeSet<TypesCrystalGeode>> CRYSTAL_GEODES;
    public static final RegistrySet<WholeTreeSet.TreeType<?, ?>, WholeTreeSet<?, ?>> FULL_TREES;
    public static final RegistrySet<TypesItem<?>, ItemRegistrySet<?>> ITEMS;


    static {
        STAINED_TREES = new RegistrySet<>(TypesStainedTree.values(), StainedTreeType::new);
        CRYSTAL_GEODES = new RegistrySet<>(TypesCrystalGeode.values(), CrystalGeodeSet::new);
        FULL_TREES = new RegistrySet<>(TypesWholeTree.ALL_WHOLE_TREES, WholeTreeSet::new);
        ITEMS = new RegistrySet<>(TypesItem.ALL_ITEMS, ItemRegistrySet::new);
    }

    private AllRegistrySets() {}
    public static void register() {}
}
