package net.stockieslad.terrains.common.registry.sets;

import net.stockieslad.terrains.common.registry.sets.clouds.TypesCloudSet;
import net.stockieslad.terrains.common.registry.sets.clouds.component.CloudSet;
import net.stockieslad.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode;
import net.stockieslad.terrains.common.registry.sets.crystal_geodes.component.CrystalGeodeSet;
import net.stockieslad.terrains.common.registry.sets.foliage.TypesFoliage;
import net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet;
import net.stockieslad.terrains.common.registry.sets.item.TypesItem;
import net.stockieslad.terrains.common.registry.sets.item.component.ItemSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.stockieslad.terrains.common.registry.sets.tree.primitive.stained.StainedTreeType;
import net.stockieslad.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySet;

import java.util.List;

import static net.stockieslad.terrains.common.registry.sets.foliage.TypesFoliage.ALL_FOLIAGE;
import static net.stockieslad.terrains.common.registry.sets.item.TypesItem.ALL_ITEMS;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.TypesWholeTree.ALL_WHOLE_TREES;
import static net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public final class RegistrySets {
    public static final RegistrySet<?, ?>[] ALL_SETS;

    public static final RegistrySet<TypesStainedTree, StainedTreeType<TypesStainedTree>> STAINED_TREES;
    public static final RegistrySet<TypesCrystalGeode, CrystalGeodeSet<TypesCrystalGeode>> CRYSTAL_GEODES;
    public static final RegistrySet<TreeType<?, ?>, WholeTreeSet<?, ?>> FULL_TREES;
    public static final RegistrySet<CloudSet.Config, CloudSet> CLOUDS;
    public static final RegistrySet<TypesFoliage<?, ?, ?>, FoliageSet<?, ?, ?>> FOLIAGE;
    public static final RegistrySet<TypesItem<?>, ItemSet<?>> ITEMS;

    static {
        ALL_SETS = new RegistrySet[] {
                STAINED_TREES = new RegistrySet<>(
                        TypesStainedTree.values(),
                        List.of(
                                SAPLING,
                                LEAVES,
                                POTTED_BLOCK
                        ),
                        StainedTreeType::new
                ),
                CRYSTAL_GEODES = new RegistrySet<>(
                        TypesCrystalGeode.values(),
                        List.of(
                                TRANSPARENT_FULL_BLOCK,
                                CLUSTER,
                                CRYSTAL
                        ),
                        CrystalGeodeSet::new
                ),
                FULL_TREES = new RegistrySet<>(
                        ALL_WHOLE_TREES,
                        List.of(
                                SAPLING,
                                LEAVES,
                                POTTED_BLOCK,
                                WOOD,
                                ALL_WOOD_BLOCKS,
                                WOOD_WITH_CUTOUT
                        ),
                        WholeTreeSet::new
                ),
                CLOUDS = new RegistrySet<>(
                        TypesCloudSet.ALL,
                        List.of(TRANSPARENT_FULL_BLOCK),
                        CloudSet::new
                ),
                FOLIAGE = new RegistrySet<>(
                        ALL_FOLIAGE,
                        List.of(
                                FLOWER,
                                POTTED_BLOCK
                        ),
                        FoliageSet::new
                ),
                ITEMS = new RegistrySet<>(
                        ALL_ITEMS,
                        List.of(
                                FOOD,
                                TRANSPARENT_FOOD
                        ),
                        ItemSet::new
                ),
        };
    }

    private RegistrySets() {}
    public static void init() {}
}
