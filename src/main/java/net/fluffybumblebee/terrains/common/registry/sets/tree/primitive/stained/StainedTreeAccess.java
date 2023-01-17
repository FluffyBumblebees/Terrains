package net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained;

import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained.StainedTreeType.StainedConfig;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.raw.StainedSaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@SuppressWarnings("unchecked")
public class StainedTreeAccess {

    public static StainedTreeType<TypesStainedTree> accessInstance(TypesStainedTree colour) {
        return AllRegistrySets.STAINED_TREES.getTypeMap().get(colour);
    }

    public static <FeatureProvider extends FeatureCreator<StainedSaplingGenerator>>  FeatureProvider
    getFeatures(TypesStainedTree colour) {
        return (FeatureProvider) accessInstance(colour).TREE_CONFIG.TREE_FEATURES;
    }

    public static RegistryEntry<PlacedFeature> getPlacedFeature(TypesStainedTree colour) {
        return ((StainedConfig)getFeatures(colour)).COMMON_RANDOM_TREE_PLACED;
    }

    public static void addAllToBuilder(GenerationSettings.Builder builder) {
        for (TypesStainedTree colour : TypesStainedTree.values()) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, StainedTreeAccess.getPlacedFeature(colour));
        }
    }
}