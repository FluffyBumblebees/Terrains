package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.maple_forest.world.placer.ConeFoliagePlacer;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.MapleTreeSetConfig;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.fluffybumblebee.terrains.util.registration.world.feature.MFPlacedFeatureRegistration.BiasedRegistration.registerMaplePlacer;
import static net.minecraft.world.gen.feature.ConfiguredFeatures.register;

public class TestingClass {
    public static final TFSetConfig<Types, FoliageTypes, MapleSaplingGenerator> config = new TFSetConfig<>(
            Blocks.OAK_LOG,
            Types.MAPLE,
            FoliageTypes.values(),
            instance -> {
                for (FoliageTypes types : instance.FOLIAGE_VARIANTS)  {
                    String TYPE = types.name().toLowerCase();
                    var builder = new TreeFeatureConfig.Builder(
                            BlockStateProvider.of(instance.LOG),
                            new StraightTrunkPlacer(6, 5, 5),
                            BlockStateProvider.of(instance.LEAVES.BLOCK),
                            new ConeFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                            new ThreeLayersFeatureSize(1,1, 0, 0, 2, OptionalInt.empty())
                    );

                    builder.decorators(List.of(new BeehiveTreeDecorator(0.05F)));

                    instance.CONFIGURED_FEATURES.put(types, register(TerrainsDefaults.getNamespaceVar() + instance.TYPE.name().toLowerCase() + "_" + types.name().toLowerCase() + "_tree", Feature.TREE, builder.build()));
                }
            },
            instance -> {
                return registerMaplePlacer(instance.CONFIGURED_FEATURES.get(FoliageTypes.TREE));
            },
            instance -> {

            }
    );

    enum Types {
        MAPLE,
    }

    enum FoliageTypes {
        TREE,
        TREE_BEES
    }
}
