package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredLeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredSaplingBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.SetRegistrar;
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

public final class PrimitiveTreeSetConfig<Generator extends SaplingGenerator> implements SetRegistrar {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final BlockSet<ConfiguredLeavesBlock> LEAVES;
    public final BlockSet<ConfiguredSaplingBlock> SAPLING;
    public final BlockSet<ConfiguredFlowerPotBlock> POTTED_SAPLING;

    public final String[] CONFIGURED_VARIANTS;
    public final String[] PLACED_VARIANTS;

    public final HashMap<String, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> CONFIGURED_FEATURE_HOLDER;
    public final HashMap<String, RegistryEntry<PlacedFeature>> PLACED_FEATURES_HOLDER;

    public PrimitiveTreeSetConfig(
            final List<BlockSet<?>> logVariants,
            final String type,
            final String[] configuredVariants,
            final String[] uniquePlacedVariants,
            final TreePattern treePattern,
            final Configurator featureConfigurator,
            final SaplingGeneratorProvider<Generator> saplingGeneratorProvider
    ) {

        CONFIGURED_VARIANTS = configuredVariants;
        CONFIGURED_FEATURE_HOLDER = new HashMap<>();
        PLACED_VARIANTS = uniquePlacedVariants;
        PLACED_FEATURES_HOLDER = new HashMap<>();

        ALL_BLOCKS = new ArrayList<>();

        LEAVES = buildBlock(new ConfiguredLeavesBlock(), type + "_leaves");

        featureConfigurator.configureAndPlace(
                type,
                logVariants,
                LEAVES.BLOCK,
                treePattern,
                CONFIGURED_FEATURE_HOLDER,
                configuredVariants,
                PLACED_FEATURES_HOLDER,
                uniquePlacedVariants
        );

        SAPLING = buildBlock(
                new ConfiguredSaplingBlock(saplingGeneratorProvider.register(CONFIGURED_FEATURE_HOLDER,
                        configuredVariants)),
                type + "_sapling"
        );

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ConfiguredFlowerPotBlock(SAPLING.BLOCK),
                getIdentifier("potted_" + type +  "_sapling")
        ).addBlockItem(new Item.Settings()).build();

        addAllBlocks(new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        });
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }

    public interface TreePattern {
        TreeFeatureConfig.Builder get(String type, List<BlockSet<?>> log, Block leaves);
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
