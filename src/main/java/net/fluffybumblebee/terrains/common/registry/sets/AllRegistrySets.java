package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.clouds.TypesCloudSet;
import net.fluffybumblebee.terrains.common.registry.sets.clouds.component.CloudSet;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.TypesCrystalGeode;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.component.CrystalGeodeSet;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.component.FoliageSet;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.TypesFoliage;
import net.fluffybumblebee.terrains.common.registry.sets.item.TypesItem;
import net.fluffybumblebee.terrains.common.registry.sets.item.component.ItemSet;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.StainedTreeType;
import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.TypesStainedTree;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.foliage.TypesFoliage.ALL_FOLIAGE;
import static net.fluffybumblebee.terrains.common.registry.sets.item.TypesItem.ALL_ITEMS;
import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.TypesWholeTree.ALL_WHOLE_TREES;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public final class AllRegistrySets {
    public static final RegistrySet<?, ?>[] ALL_SETS;

    public static final RegistrySet<TypesStainedTree, StainedTreeType<TypesStainedTree>> STAINED_TREES;
    public static final RegistrySet<TypesCrystalGeode, CrystalGeodeSet<TypesCrystalGeode>> CRYSTAL_GEODES;
    public static final RegistrySet<TreeType<?, ?, ?>, WholeTreeSet<?, ?, ?>> FULL_TREES;
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

    private AllRegistrySets() {}
    public static void register() {}
}
