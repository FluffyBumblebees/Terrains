package net.fluffybumblebee.terrains.common.registry.sets.tree.whole;

import net.fluffybumblebee.maple_forest.world.placer.ConeFoliagePlacer;
import net.fluffybumblebee.terrains.common.instances.block.wood_set.WoodBlock;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.TreeFoliageSetConfig;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.TreeFoliageSetConfig.AfterLeaves;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSetConfig;
import net.fluffybumblebee.terrains.common.world.feature.raw.MapleSaplingGenerator;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.OptionalInt;

import static net.minecraft.world.gen.feature.ConfiguredFeatures.register;

@SuppressWarnings("FieldCanBeLocal")
public class MapleTreeSetConfig<E extends Enum<?>> implements FeatureRegistrar<BlockSet<?>> {
    private final BlockSet<?>[] ALL_BLOCKS;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> GREEN_FOLIAGE;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> GREEN_FOLIAGE_BEES;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> RED_FOLIAGE;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> YELLOW_FOLIAGE;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> ORANGE_FOLIAGE;
    private final TreeFoliageSetConfig<E, MapleSaplingGenerator> BROWN_FOLIAGE;
    private final BlockSet<?> SAPPY_LOG;
    private final WoodSetConfig<E> WOOD;


    public MapleTreeSetConfig(E type) {
        final String name = type.name();
        SAPPY_LOG = BlockSet.buildFlammableBlock(new WoodBlock(), name);
        WOOD = new WoodSetConfig<>(type);
        GREEN_FOLIAGE = new TreeFoliageSetConfig<>(type, getGen("green", null));
        GREEN_FOLIAGE_BEES = new TreeFoliageSetConfig<>(type, getGen("green", 0.05F));
        RED_FOLIAGE = new TreeFoliageSetConfig<>(type, getGen("green", null));
        YELLOW_FOLIAGE = new TreeFoliageSetConfig<>(type, getGen("green", null));
        ORANGE_FOLIAGE = new TreeFoliageSetConfig<>(type, getGen("green", null));
        BROWN_FOLIAGE = new TreeFoliageSetConfig<>(type, getGen("green", null));

        ALL_BLOCKS = (BlockSet<?>[]) ArrayUtils.addAll(
                RED_FOLIAGE.getAll(),
                BROWN_FOLIAGE.getAll(),
                YELLOW_FOLIAGE.getAll(),
                ORANGE_FOLIAGE.getAll(),
                GREEN_FOLIAGE.getAll(),
                new BlockSet[]{SAPPY_LOG},
                WOOD.getAll()
        );

    }

    @Override
    public BlockSet<?>[] getAll() {
        return ALL_BLOCKS;
    }

    public AfterLeaves<MapleSaplingGenerator> getGen(String name, @Nullable Float beeChance) {
        return block -> new MapleSaplingGenerator(() -> createMapleTree(name, block, beeChance));
    }
    public RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> createMapleTree(
            String name,
            Block leaves,
            @Nullable Float beeChance
    ) {
        DataPool<BlockState> logVariants = DataPool.<BlockState>builder()
                .add(WOOD.LOG.BLOCK.getDefaultState(), 2)
                .add(SAPPY_LOG.BLOCK.getDefaultState(), 1)
                .build();

        var builder = new TreeFeatureConfig.Builder(
                new WeightedBlockStateProvider(logVariants),
                new StraightTrunkPlacer(6, 5, 5),
                BlockStateProvider.of(leaves.getDefaultState()),
                new ConeFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new ThreeLayersFeatureSize(1,1, 0, 0, 2, OptionalInt.empty())
        );

        if (beeChance != null) {
            builder.decorators(List.of(new BeehiveTreeDecorator(beeChance)));
        }

        return register(TerrainsDefaults.getNamespaceVar() + name, Feature.TREE, builder.build());
    }
}
