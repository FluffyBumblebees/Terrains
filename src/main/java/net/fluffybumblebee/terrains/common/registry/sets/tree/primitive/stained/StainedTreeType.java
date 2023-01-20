package net.fluffybumblebee.terrains.common.registry.sets.tree.primitive.stained;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.Config;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.component.StainedSaplingGenerator;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;

public final class StainedTreeType<Colour extends Enum<?>> implements RegistrySetCreator {
    public final PrimitiveTreeSet<StainedSaplingGenerator, StainedConfig> TREE_CONFIG;

    public StainedTreeType(final Colour type) {
        final String colour = type.name().toLowerCase();

        TREE_CONFIG = new PrimitiveTreeSet<>(new Config<>(
                colour,
                List.of(Blocks.OAK_LOG),
                StainedConfig::new
        ));
    }

    @Override
    public void registryEvent(SetRegistry registry) {
        TREE_CONFIG.registryEvent(registry);
    }

    public static class StainedConfig implements FeatureCreator<StainedSaplingGenerator> {

        public final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> RANDOM_TREE;
        //public final RegistryEntry<PlacedFeature> RANDOM_TREE_PLACED;
        public final RegistryEntry<PlacedFeature> COMMON_RANDOM_TREE_PLACED;
        private final StainedSaplingGenerator GENERATOR;

        private StainedConfig(final List<Block> allLogs, final LeavesBlock leaves, final String type) {
            final var tree = oakLike(type, leaves, false, false);
            final var tree_bees = oakLike(type, leaves, true, false);
            final var fat_tree = oakLike(type, leaves, false, true);
            final var fat_tree_bees = oakLike(type, leaves, true, true);
            getPlaced(type, tree, false, false);
            final var tree_bees_placed = getPlaced(type, tree_bees, true, false);
            getPlaced(type, fat_tree, false, true);
            final var fat_tree_bees_placed = getPlaced(type, fat_tree_bees, true, true);

            RANDOM_TREE = ConfiguredFeatures.register(TerrainsDefaults.getNamespaceVar() + type + "_tree_bees_spawn",
                    Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(
                            List.of(
                                    new RandomFeatureEntry(fat_tree_bees_placed, 0.1f)
                            ),
                            tree_bees_placed
                    )
            );

            COMMON_RANDOM_TREE_PLACED = PlacedFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + type + "common_tree_bees_placed",
                    RANDOM_TREE,
                    TreeRegistration.treePlacementModifiers(
                            PlacedFeatures.createCountExtraModifier(1, 0.1f, 1)
                    )
            );

            GENERATOR = new StainedSaplingGenerator(
                    () -> tree,
                    () -> tree_bees,
                    () -> fat_tree,
                    () -> fat_tree_bees
            );
        }

        @Override
        public StainedSaplingGenerator createNew() {
            return GENERATOR;
        }

        private static RegistryEntry<PlacedFeature> getPlaced(
                final String type, final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> tree,
                final boolean bees, final boolean large) {

            var beeState = "no_bees";
            if (bees)
                beeState = "bees";

            var size = "small";
            if (large)
                size = "large";

            return PlacedFeatures.register(
                    type + "_" + size + "_tree_" + beeState + "_placed",
                    tree,
                    PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING),
                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(BlockTags.DIRT, Direction.DOWN.getVector()))

            );
        }

        public static TreeFeatureConfig.Builder basicOak(
                final Block leaves, final boolean bees, final boolean large
        ) {
            TrunkPlacer trunkPlacer = new StraightTrunkPlacer(4, 2, 0);
            FoliagePlacer foliagePlacer = new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3);

            if (large) {
                trunkPlacer = new LargeOakTrunkPlacer(3, 11, 0);
                foliagePlacer = new LargeOakFoliagePlacer(ConstantIntProvider.create(2),
                        ConstantIntProvider.create(4), 4);
            }

            final var config = new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                    trunkPlacer,
                    BlockStateProvider.of(leaves.getDefaultState()),
                    foliagePlacer,
                    new TwoLayersFeatureSize(1, 0, 1)
            );

            if (bees)
                config.decorators(List.of(new BeehiveTreeDecorator(0.03F)));

            return config;
        }

        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> oakLike(
                final String colour, final Block leaves, final boolean bees, final boolean large
        ) {
            var size = "small";
            if (large)
                size = "large";
            var beeState = "no_bees";
            if (bees)
                beeState = "bees";

            return ConfiguredFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + size + "_" + colour + "_tree_" + beeState,
                    Feature.TREE,
                    basicOak(leaves, bees, large).build()
            );
        }
    }
}
