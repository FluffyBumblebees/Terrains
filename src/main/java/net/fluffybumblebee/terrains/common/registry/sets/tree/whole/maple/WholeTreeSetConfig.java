package net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;

public class WholeTreeSetConfig<E extends Enum<?>> implements FeatureRegistrar<BlockSet<?>> {
    private final BlockSet<?>[] ALL_BLOCKS;

    public WholeTreeSetConfig(E type) {
        ALL_BLOCKS = new BlockSet[] {

        };
    }


    @Override
    public BlockSet<?>[] getAll() {
        return ALL_BLOCKS;
    }
}
