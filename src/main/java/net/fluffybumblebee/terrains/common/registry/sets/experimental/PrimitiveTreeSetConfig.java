package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.plant.LeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.SaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ShortenedFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;

public final class PrimitiveTreeSetConfig<Generator extends SaplingGenerator> implements FeatureRegistrar<BlockSet<?>> {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final BlockSet<LeavesBlock> LEAVES;
    public final BlockSet<SaplingBlock> SAPLING;
    public final BlockSet<ShortenedFlowerPotBlock> POTTED_SAPLING;

    public final String[] CONFIGURED_VARIANTS;
    public final String[] PLACED_VARIANTS;

    public final HashMap<String, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> CONFIGURED_FEATURE_HOLDER;
    public final HashMap<String, RegistryEntry<PlacedFeature>> PLACED_FEATURES_HOLDER;

    public PrimitiveTreeSetConfig(
            final List<BlockSet<?>> logVariants,
            final String woodType,
            final String treeVariant,
            final String[] configuredVariants,
            final String[] placedVariants,
            final TreePattern treePattern,
            final Configurator featureConfigurator,
            final SaplingGeneratorProvider<Generator> saplingGeneratorProvider
    ) {
        final String typeString = treeVariant + "_" + woodType;

        CONFIGURED_VARIANTS = configuredVariants;
        CONFIGURED_FEATURE_HOLDER = new HashMap<>();
        PLACED_VARIANTS = placedVariants;
        PLACED_FEATURES_HOLDER = new HashMap<>();

        ALL_BLOCKS = new ArrayList<>();

        LEAVES = buildBlock(new LeavesBlock(), typeString + "_leaves");

        featureConfigurator.configureAndPlace(
                typeString,
                logVariants,
                LEAVES.BLOCK,
                treePattern,
                CONFIGURED_FEATURE_HOLDER,
                configuredVariants,
                PLACED_FEATURES_HOLDER,
                placedVariants
        );

        SAPLING = buildBlock(
                new SaplingBlock(saplingGeneratorProvider.register(CONFIGURED_FEATURE_HOLDER,
                        configuredVariants)),
                typeString + "_sapling"
        );

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ShortenedFlowerPotBlock(SAPLING.BLOCK),
                getIdentifier("potted_" + typeString +  "_sapling")
        ).addBlockItem(new Item.Settings()).build();

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


    //Use this in the ConfiguredVariants Enum.
    public interface TreePattern {
        TreeFeatureConfig.Builder build(String type, List<BlockSet<?>> log, Block leaves);
    }

    public interface Configurator {
        void configureAndPlace(String type,
                               List<BlockSet<?>> log,
                               Block leaves,
                               TreePattern treePattern,
                               HashMap<String, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> configuredFeatureHolder,
                               String[] configuredVariants,
                               HashMap<String, RegistryEntry<PlacedFeature>> placedFeatureHolder,
                               String[] placedVariants

        );
    }

    public interface SaplingGeneratorProvider<Gen extends SaplingGenerator> {
        Gen register(HashMap<String, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> configuredFeatures,
                     String[] variants);
    }
}
