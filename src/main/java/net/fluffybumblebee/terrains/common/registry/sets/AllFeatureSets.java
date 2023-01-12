package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.AllTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.TreeType;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.OakLikeSetConfig;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.BasicIterator;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureSet;

import java.util.ArrayList;
import java.util.List;

public final class AllFeatureSets {
    public static final FeatureSet<StainedTreeTypes, OakLikeSetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final FeatureSet<CrystalGeodeTypes, CrystalGeodeSetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;
    public static final FeatureSet<TreeType<?>, FullTreeSetConfig<?>> FULL_TREE_SETS;
    private static final BasicIterator<BasicIterator<BlockSet<?>>> ITERATOR;
    private static final List<BlockSet<?>> ALL_BLOCK_SETS;

    static {
        STAINED_TREES = new FeatureSet<>(StainedTreeTypes.values(), OakLikeSetConfig::new);
        CRYSTAL_GEODES = new FeatureSet<>(CrystalGeodeTypes.values(), CrystalGeodeSetConfig::new);
        FULL_TREE_SETS = new FeatureSet<>(AllTreeTypes.getTypes().getAllEntries(), FullTreeSetConfig::new);

        ITERATOR = () -> List.of(
                () -> STAINED_TREES.getAllRegistryEntries(),
                () -> CRYSTAL_GEODES.getAllRegistryEntries(),
                () -> FULL_TREE_SETS.getAllRegistryEntries()
        );

        ALL_BLOCK_SETS =  new ArrayList<>();
        ITERATOR.forEach(subIterator -> subIterator.forEach(ALL_BLOCK_SETS::add));
    }

    private AllFeatureSets() {}
    public static void register() {}

    public static List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCK_SETS;
    }
}
