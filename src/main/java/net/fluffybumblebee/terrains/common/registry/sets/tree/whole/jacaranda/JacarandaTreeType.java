package net.fluffybumblebee.terrains.common.registry.sets.tree.whole.jacaranda;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.component.StandardSaplingGenerator;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.component.component.HemiEllipsoidFoliagePlacer;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.EnumArrayToString;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.treePlacementModifiers;
import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;

public final class JacarandaTreeType {
    public static final TreeType<StandardSaplingGenerator, JacarandaFeatures, UniqueJacarandaFeatures> JACARANDA_TREES =
            new TreeType<>(
                    "jacaranda",
                    EnumArrayToString(JacarandaTypes.values()),
                    new String[0],
                    JacarandaFeatures::new,
                    UniqueJacarandaFeatures::new
            );

    public enum JacarandaTypes {
        PINK,
        PURPLE
    }
    public static final class JacarandaFeatures implements FeatureCreator<StandardSaplingGenerator> {
        private final List<Block> ALL_LOGS;
        public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CONFIGURED_TREE_NO_BEES;
        public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CONFIGURED_TREE_BEES;
        public final RegistryEntry<PlacedFeature> PLACED_TREE_NO_BEES;
        public final RegistryEntry<PlacedFeature> PLACED_TREE_BEES;

        public final RegistryEntry<PlacedFeature> COMMON_PLACED_TREE_BEES;

        private JacarandaFeatures(final List<Block> allLogs, final LeavesBlock leaves, final String type) {
            ALL_LOGS = allLogs;
            CONFIGURED_TREE_NO_BEES = getConfiguredJacaranda(type, leaves, false);
            CONFIGURED_TREE_BEES = getConfiguredJacaranda(type, leaves, true);
            PLACED_TREE_NO_BEES = getPlacedJacaranda(type, CONFIGURED_TREE_NO_BEES, false,
                    createCountExtraModifier(0, 0.5F, 1));
            PLACED_TREE_BEES = getPlacedJacaranda(type, CONFIGURED_TREE_BEES, true,
                    createCountExtraModifier(0, 0.5F, 1));

            COMMON_PLACED_TREE_BEES = getPlacedJacaranda("common" + type, CONFIGURED_TREE_BEES, true,
                    createCountExtraModifier(4, 0.25F, 2));
        }

        @Override
        public StandardSaplingGenerator createNew() {
            return new StandardSaplingGenerator(
                    ctx -> CONFIGURED_TREE_NO_BEES,
                    ctx -> CONFIGURED_TREE_BEES
            );
        }

        private RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>
        getConfiguredJacaranda(final String type, final LeavesBlock leaves, final boolean bees) {
            final var builder = new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ALL_LOGS.get(0).getDefaultState()),
                    new StraightTrunkPlacer(3, 2, 0),
                    BlockStateProvider.of(leaves),
                    new HemiEllipsoidFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                    new ThreeLayersFeatureSize(1, 1, 0, 0, 1, OptionalInt.empty())
            );

            String beeState = "no_bees";

            if (bees) {
                builder.decorators(List.of(new BeehiveTreeDecorator(0.05F)));
                beeState = "bees";
            }

            return ConfiguredFeatures.register(
                    TerrainsDefaults.getNamespaceVar() + type + "_tree_" + beeState,
                    Feature.TREE,
                    builder.build());
        }

        private RegistryEntry<PlacedFeature> getPlacedJacaranda(
                final String type, final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> tree,
                final boolean bees, PlacementModifier modifier) {
            String beeState = "no_bees";
            if (bees)
                beeState = "bees";
            return PlacedFeatures.register(
                    type + "_tree_" + beeState + "_placed",
                    tree,
                    treePlacementModifiers(modifier)
            );
        }
    }

    public static final class UniqueJacarandaFeatures {
        private UniqueJacarandaFeatures(final List<Block> allLogs, final List<Block> allLeaves, final String type) {}
    }
}
