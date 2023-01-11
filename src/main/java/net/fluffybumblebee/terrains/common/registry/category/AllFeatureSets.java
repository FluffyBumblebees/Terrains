package net.fluffybumblebee.terrains.common.registry.category;

import net.fluffybumblebee.terrains.common.registry.category.crystal_geodes.CrystalGeodeSetConfig;
import net.fluffybumblebee.terrains.common.registry.category.crystal_geodes.CrystalGeodeTypes;
import net.fluffybumblebee.terrains.common.registry.category.stained.StainedTreeTypes;
import net.fluffybumblebee.terrains.common.registry.category.stained.StainedTreeSetConfig;
import net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper.MultTypeFeatureSet;

public final class AllFeatureSets {
    public static final MultTypeFeatureSet<StainedTreeTypes, StainedTreeSetConfig<StainedTreeTypes>> STAINED_TREES;
    public static final MultTypeFeatureSet<CrystalGeodeTypes, CrystalGeodeSetConfig<CrystalGeodeTypes>> CRYSTAL_GEODES;

    static {
        STAINED_TREES = new MultTypeFeatureSet<>(StainedTreeTypes.values(), StainedTreeSetConfig::new);
        CRYSTAL_GEODES = new MultTypeFeatureSet<>(CrystalGeodeTypes.values(), CrystalGeodeSetConfig::new);
    }

    private AllFeatureSets() {}
    public static void register() {}
}
