package net.stockieslad.terrains.common.registry.sets.tree.primitive.stained;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.stockieslad.terrains.common.registry.sets.RegistrySetManager;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.common.registry.sets.tree.primitive.stained.StainedTreeType.StainedConfig;

@SuppressWarnings("unchecked")
public class StainedTreeAccess {

    public static StainedTreeType<TypesStainedTree> accessInstance(TypesStainedTree colour) {
        return RegistrySetManager.STAINED_TREES.getTypeMap().get(colour);
    }

    public static <FeatureProvider extends FeatureCreator>  FeatureProvider
    getFeatures(TypesStainedTree colour) {
        return (FeatureProvider) accessInstance(colour).TREE_CONFIG.TREE_FEATURES;
    }

    public static RegistryEntry<PlacedFeature> getPlacedFeature(TypesStainedTree colour) {
        return ((StainedConfig)getFeatures(colour)).COMMON_RANDOM_TREE_PLACED;
    }

    public static void addStainedTrees(GenerationSettings.Builder builder) {
        for (TypesStainedTree colour : TypesStainedTree.values()) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, StainedTreeAccess.getPlacedFeature(colour));
        }
    }
}
