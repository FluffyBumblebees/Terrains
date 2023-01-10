package net.fluffybumblebee.stainedtrees;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.stainedtrees.item.STItemGroup;
import net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks;

public class StainedTrees implements ModInitializer {
    @Override
    public void onInitialize() {
        StainedBlocks.initClass();
        STItemGroup.initClass();
    }
}
