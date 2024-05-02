package net.stockieslad.terrains.common.registry.sets.tree.primitive.stained;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
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
import net.stockieslad.abstractium.init.AbstractiumCommon;
import net.stockieslad.abstractium.library.common.AbstractCommonCalls;
import net.stockieslad.abstractium.library.common.wrap.util.WrappedRandom;
import net.stockieslad.abstractium.util.dynamic.Mimic;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.Config;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.stockieslad.terrains.util.registration.etc.ValueHelper;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.stockieslad.terrains.util.registration.world.feature.TreeRegistration;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static net.stockieslad.abstractium.library.common.CommonTypeObjects.*;
import static net.stockieslad.abstractium.util.dynamic.TypeObject.*;

public final class StainedTreeType<Colour extends Enum<?>> implements RegistrySetCreator {
    private static final AbstractCommonCalls ABSTRACTION = AbstractiumCommon.COMMON_ABSTRACTION_HANDLER.abstraction;
    public final PrimitiveTreeSet<StainedConfig> TREE_CONFIG;

    public StainedTreeType(final Colour type) {
        final String colour = type.name().toLowerCase();

        TREE_CONFIG = new PrimitiveTreeSet<>(new Config<>(
                colour,
                List.of(Blocks.OAK_LOG),
                StainedConfig::new
        ));
    }

    @Override
    public void register(SetRegistry registry) {
        TREE_CONFIG.register(registry);
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.CUTOUT);
    }

    public static class StainedConfig implements FeatureCreator {

        public final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> RANDOM_TREE;
        public final RegistryEntry<PlacedFeature> COMMON_RANDOM_TREE_PLACED;
        private final SaplingGenerator GENERATOR;

        private StainedConfig(final List<Block> allLogs, final LeavesBlock leaves, final String type) {
            final var tree = createOakLike(type, leaves, false, false);
            final var tree_bees = createOakLike(type, leaves, true, false);
            final var fat_tree = createOakLike(type, leaves, false, true);
            final var fat_tree_bees = createOakLike(type, leaves, true, true);
            getPlaced(type, tree, false, false);
            final var tree_bees_placed = getPlaced(type, tree_bees, true, false);
            getPlaced(type, fat_tree, false, true);
            final var fat_tree_bees_placed = getPlaced(type, fat_tree_bees, true, true);

            RANDOM_TREE = ABSTRACTION.getRegistrar().registerConfiguredFeature(
                    new Identifier(TerrainsDefaults.getNamespaceVar() + type + "_tree_bees_spawn"),
                    ABSTRACTION.getStructureCreator().createConfiguredFeature(
                            new RandomFeatureConfig(
                                    List.of(new RandomFeatureEntry(fat_tree_bees_placed, 0.1f)),
                                    tree_bees_placed
                            ),
                            Feature.RANDOM_SELECTOR
                    )
            ).cast(registryEntry(configuredFeature(ofWildcard(), ofWildcard())));

            COMMON_RANDOM_TREE_PLACED = ABSTRACTION.getRegistrar().registerPlacedFeature(
                    new Identifier(TerrainsDefaults.getNamespaceVar() + type + "common_tree_bees_placed"),
                    ABSTRACTION.getStructureCreator().createPlacedFeature(
                            new Mimic(
                                    registryEntry(configuredFeature(ofWildcard(), ofWildcard())),
                                    RANDOM_TREE
                            ),
                            TreeRegistration.treePlacementModifiers(
                                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 1)
                            )
                    )
            ).cast(ofGenerics("RegistryEntry", of("PlacedFeature")));

            GENERATOR = ABSTRACTION.getStructureCreator().createSaplingGenerator(new BiFunction<>() {
                private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> def = () -> tree;
                private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> defBees = () -> tree_bees;
                private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fat = () -> fat_tree;
                private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fatBees = () -> fat_tree_bees;

                @Override
                public Mimic apply(WrappedRandom random, Boolean bees) {
                    final var type = registryEntry(configuredFeature(wildcard(), wildcard()));
                    if (random.nextInt(10) == 0) {
                        return bees ? new Mimic(type, fatBees.get()) : new Mimic(type, fat.get());
                    } else {
                        return bees ? new Mimic(type, defBees.get()) : new Mimic(type, def.get());
                    }
                }
            }).cast(of("SaplingGenerator"));
        }

        @Override
        public SaplingGenerator createNew() {
            return GENERATOR;
        }

        private static RegistryEntry<PlacedFeature> getPlaced(
                final String type, final RegistryEntry<ConfiguredFeature<TreeFeatureConfig,?>> tree,
                final boolean bees, final boolean large) {

            final var beeState = ValueHelper.returnIf(bees, "bees", "no_bees");
            final var size = ValueHelper.returnIf(large, "large", "small");

            return ABSTRACTION.getRegistrar().registerPlacedFeature(
                    TerrainsDefaults.getIdentifier(type + "_" + size + "_tree_" + beeState + "_placed"),
                    ABSTRACTION.getStructureCreator().createPlacedFeature(
                            new Mimic(registryEntry(configuredFeature(wildcard(), wildcard())), tree),
                            List.of(
                                    PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING),
                                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(BlockTags.DIRT, Direction.DOWN.getVector()))
                            )
                    )
            ).cast(registryEntry(placedFeature()));
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

        public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createOakLike(
                final String colour, final Block leaves, final boolean bees, final boolean large
        ) {
            final var beeState = ValueHelper.returnIf(bees, "bees", "no_bees");
            final var size = ValueHelper.returnIf(large, "large", "small");

            return ABSTRACTION.getRegistrar().registerConfiguredFeature(
                    TerrainsDefaults.getIdentifier(size + "_" + colour + "_tree_" + beeState),
                    ABSTRACTION.getStructureCreator().createConfiguredFeature(
                            basicOak(leaves, bees, large).build(),
                            Feature.TREE
                    )
            ).cast(registryEntry(configuredFeature(wildcard(), wildcard())));
        }
    }
}
