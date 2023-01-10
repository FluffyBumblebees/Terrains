package net.fluffybumblebee.maple_forest.world.gen;


import net.fluffybumblebee.maple_forest.world.feature.MFPlacedFeatures;

public class MFTreeGeneration {
    public static void generateMapleTrees() {
       // BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
          //      GenerationStep.Feature.VEGETAL_DECORATION, MFPlacedFeatures.MAPLE_PLACED.getKey().get());
        MFPlacedFeatures.initClass();
    }
}
