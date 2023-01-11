package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.stained.OakLikeSetConfig;
import net.fluffybumblebee.terrains.util.registration.feature_set.MultiTypeFeatureSet;

public final class AllFeatureSets {
    public static final MultiTypeFeatureSet<StainedTreeTypes, OakLikeSetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final MultiTypeFeatureSet<CrystalGeodeTypes, CrystalGeodeSetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;

    static {
        STAINED_TREES = new MultiTypeFeatureSet<>(StainedTreeTypes.values(), OakLikeSetConfig::new);
        CRYSTAL_GEODES = new MultiTypeFeatureSet<>(CrystalGeodeTypes.values(), CrystalGeodeSetConfig::new);
    }

    private AllFeatureSets() {}
    public static void register() {}
}
