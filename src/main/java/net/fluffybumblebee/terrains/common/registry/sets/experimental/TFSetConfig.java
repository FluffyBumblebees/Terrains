package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.plant.LeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.SaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;

public final class TFSetConfig<E extends Enum<?>,T extends Enum<?>, S extends SaplingGenerator> implements FeatureRegistrar<BlockSet<?>> {
    private final List<BlockSet<?>> ALL_BLOCKS;

    public final BlockSet<LeavesBlock> LEAVES;
    public final BlockSet<SaplingBlock> SAPLING;
    public final BlockSet<ShortenedFlowerPotBlock> POTTED_SAPLING;

    public final HashMap<T, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> CONFIGURED_FEATURES;
    public final RegistryEntry<PlacedFeature> PLACED_FEATURES;
    public final T[] FOLIAGE_VARIANTS;
    public final E TYPE;
    public final String TYPE_STRING;
    public final Block LOG;

    public TFSetConfig(
            Block log,
            E type,
            T[] foliageVariants,
            FeatureConfigurator<TFSetConfig<E, T, S>> featureConfigurator,
            FeaturePlacementConfigurator<TFSetConfig<E, T, S>> featurePlacementConfigurator,
            SaplingGeneratorProvider<TFSetConfig<E, T, S>, S> saplingGeneratorProvider
    ) {
        LOG = log;
        TYPE = type;
        TYPE_STRING = type.name().toLowerCase();
        FOLIAGE_VARIANTS = foliageVariants;
        CONFIGURED_FEATURES = new HashMap<>();

        ALL_BLOCKS = new ArrayList<>();
        LEAVES = buildBlock(new LeavesBlock(), TYPE_STRING + "_leaves");
        featureConfigurator.configure(this);
        PLACED_FEATURES = featurePlacementConfigurator.configure(this);

        SAPLING = buildBlock(new SaplingBlock(saplingGeneratorProvider.register(this)),   TYPE_STRING + "_sapling");

        var sapling = SAPLING.BLOCK;
        POTTED_SAPLING = new BlockSet.Builder<>(
                new ShortenedFlowerPotBlock(sapling),
                getIdentifier("potted_" + TYPE_STRING +  "_sapling")
        ).addBlockItem().build();

        addAllElements(new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        });
    }

    @Override
    public List<BlockSet<?>> getAll() {
        return ALL_BLOCKS;
    }

    public interface FeatureConfigurator<T extends FeatureRegistrar<?>> {
        void configure(T instance);
    }

    public interface FeaturePlacementConfigurator<T extends FeatureRegistrar<?>> {
        RegistryEntry<PlacedFeature> configure(T instance);
    }

    public interface SaplingGeneratorProvider<T extends FeatureRegistrar<?>, S extends SaplingGenerator> {
        S register(T instance);
    }
}
