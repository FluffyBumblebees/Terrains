package net.fluffybumblebee.stainedtrees;

import net.fluffybumblebee.stainedtrees.block.STBlocks;
import net.fluffybumblebee.stainedtrees.item.STItemGroup;
import net.fluffybumblebee.stainedtrees.world.gen.STWorldGen;
import net.fabricmc.api.ModInitializer;

public class StainedTrees implements ModInitializer {
    public static String MOD_ID = "stained_trees";
    @Override
    public void onInitialize() {
        STWorldGen.generateWorldGen();
        STBlocks.initClass();
        STItemGroup.initClass();
    }
}
