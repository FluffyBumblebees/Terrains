package net.fluffybumblebee.maple_forest.util.registration.world.feature;

import com.google.common.collect.ImmutableList;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MFPlacedFeatureRegistration {
    public static RegistryEntry<PlacedFeature> register(
            String type,
            RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> treeSpawn,
            int guaranteedAmountInChunk,
            float chanceOfExtraInChunk,
            int amountIfExtraSpawns
    ) {
        return PlacedFeatures.register
                (type,
                        treeSpawn,
                        VegetationPlacedFeatures.modifiers(
                                PlacedFeatures.createCountExtraModifier(
                                        guaranteedAmountInChunk,
                                        chanceOfExtraInChunk,
                                        amountIfExtraSpawns)
                        )
                );
    }

    public static RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> featureRegistryEntry(String id, String name, TreeFeatureConfig tree, float chance) {
        RegistryEntry<PlacedFeature> treeFinal = createPlacedFeatureWithoutBiomeFilter(name, MFConfiguredFeatureRegistration.register(name, Feature.TREE, tree));
        return MFConfiguredFeatureRegistration.register(id, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(
                        ImmutableList.of(
                                new RandomFeatureEntry(treeFinal, chance)),
                        treeFinal

                )
        );
    }

    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
        List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
        list.add(BiomePlacementModifier.of());
        return createPlacedFeature(id, feature, list);
    }

    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeatureWithoutBiomeFilter(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
        List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
        return createPlacedFeature(id, feature, list);
    }

    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, List<PlacementModifier> placementModifiers) {
        Identifier realID = new Identifier(MapleForest.NAMESPACE, id);
        if (BuiltinRegistries.PLACED_FEATURE.getIds().contains(realID))
            throw new IllegalStateException("Placed Feature ID: \"" + realID + "\" already exists in the Placed Features registry!");

        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, realID, new PlacedFeature(RegistryEntry.upcast(feature), List.copyOf(placementModifiers)));
    }
    public static class BiasedRegistration {
        public static RegistryEntry<PlacedFeature> registryEntryOfPF(String type, TreeFeatureConfig tree, float chance, int countMod) {
            return MFPlacedFeatureRegistration.createPlacedFeature(
                    type +"_trees",
                    MFPlacedFeatureRegistration.featureRegistryEntry(
                            "fallen_"+ type + "_tree",
                            "fallen_"+ type + "_trunk",
                            tree,
                            chance),
                    CountPlacementModifier.of(countMod),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(BlockTags.DIRT, Direction.DOWN.getVector()))
            );
        }
        public static RegistryEntry<PlacedFeature> registerMaplePlacer(RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> spawner, String colour, String suffix) {
            if (Objects.equals(suffix, "")) {
                return MFPlacedFeatureRegistration.register(
                        colour + "_" + MFWoodTypes.MAPLE + "_placer_" + suffix,
                        spawner,
                        2,
                        0.5f,
                        1
                );
            }
            if (Objects.equals(suffix, "no_bees")) {
                return MFPlacedFeatureRegistration.register(
                        colour + "_" + MFWoodTypes.MAPLE + "_placer_" + suffix,
                        spawner,
                        1,
                        0.25f,
                        1
                );
            }
            throw new NullPointerException();
        }
        public static RegistryEntry<PlacedFeature> registerMaplePlacer(RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> spawner, String colour) {
            return registerMaplePlacer(spawner, colour, "no_bees");
        }
    }
}
