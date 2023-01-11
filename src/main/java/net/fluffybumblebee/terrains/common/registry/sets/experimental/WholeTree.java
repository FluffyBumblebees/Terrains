package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;

import java.util.ArrayList;
import java.util.List;

public class WholeTree <E extends Enum<?>> implements FeatureRegistrar<BlockSet<?>> {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public WholeTree() {
        ALL_BLOCKS = new ArrayList<>();
    }

    @Override
    public List<BlockSet<?>> getAll() {
        return ALL_BLOCKS;
    }
}
