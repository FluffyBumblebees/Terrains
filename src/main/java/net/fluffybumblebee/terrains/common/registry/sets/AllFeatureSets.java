package net.fluffybumblebee.terrains.common.registry.sets;

import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.common.registry.sets.stained.StainedTreeSetConfig;
import net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper.MultiTypeFeatureSet;

public final class AllFeatureSets {
    public static final MultiTypeFeatureSet<StainedTreeTypes, StainedTreeSetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final MultiTypeFeatureSet<CrystalGeodeTypes, CrystalGeodeSetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;

    static {
        STAINED_TREES = new MultiTypeFeatureSet<>(StainedTreeTypes.values(), StainedTreeSetConfig::new);
        CRYSTAL_GEODES = new MultiTypeFeatureSet<>(CrystalGeodeTypes.values(), CrystalGeodeSetConfig::new);
    }

    private AllFeatureSets() {}
    public static void register() {}
}
