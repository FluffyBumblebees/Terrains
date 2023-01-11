package net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper;

import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;

public interface FeatureRegistrar{
    BlockBuilder<?>[] getAllBlocks();
}
