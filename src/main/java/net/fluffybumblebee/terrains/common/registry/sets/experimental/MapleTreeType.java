package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSet.FeatureConfigurator;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;

public final class MapleTreeType
        implements FeatureConfigurator<MapleSaplingGenerator> {
    public static final FullTreeSet.TreeType<MapleSaplingGenerator, MapleTreeType> CONFIG =
            new FullTreeSet.TreeType<>(
                    "maple",
                    new String[] {
                            "red",
                            "brown",
                            "orange",
                            "yellow",
                            "green"
                    },
                    null,
                    MapleTreeType::new
            );
    private final FullTreeSet<MapleSaplingGenerator, MapleTreeType> treeSet;

    public MapleTreeType(FullTreeSet<MapleSaplingGenerator, MapleTreeType> treeSet) {
        this.treeSet = treeSet;
    }

    @Override
    public PrimitiveTreeSet.SaplingGeneratorProvider<MapleSaplingGenerator> getSaplingGenerator() {
        return null;
    }

}
