package net.fluffybumblebee.stainedtrees;

import net.fluffybumblebee.terrains.common.registry.blocks.StainedBlocks;
import net.fluffybumblebee.stainedtrees.item.STItemGroup;
import net.fluffybumblebee.stainedtrees.world.gen.STWorldGen;
import net.fabricmc.api.ModInitializer;

public class StainedTrees implements ModInitializer {
    public static String MOD_ID = "stained_trees";
    @Override
    public void onInitialize() {
        STWorldGen.generateWorldGen();
        StainedBlocks.initClass();
        STItemGroup.initClass();
    }
}
