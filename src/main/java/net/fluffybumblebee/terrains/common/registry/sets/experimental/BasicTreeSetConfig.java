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

public final class BasicTreeSetConfig
        <WoodType extends Enum<?>,
        TreeVariant extends Enum<?>,
        ConfiguredVariants extends Enum<?>,
        PlacedVariants extends Enum<?>,
        Generator extends SaplingGenerator>
            implements FeatureRegistrar<BlockSet<?>> {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final BlockSet<LeavesBlock> LEAVES;
    public final BlockSet<SaplingBlock> SAPLING;
    public final BlockSet<ShortenedFlowerPotBlock> POTTED_SAPLING;

    public final ConfiguredVariants[] CONFIGURED_VARIANTS;
    public final PlacedVariants[] PLACED_VARIANTS;

    public final HashMap<ConfiguredVariants, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> CONFIGURED_FEATURE_HOLDER;
    public final HashMap<PlacedVariants, RegistryEntry<PlacedFeature>> PLACED_FEATURES_HOLDER;

    public BasicTreeSetConfig(
            final List<BlockSet<?>> logs,
            final WoodType woodType,
            final TreeVariant treeVariant,
            final ConfiguredVariants[] configuredVariants,
            final PlacedVariants[] placedVariants,
            final TreePattern<ConfiguredVariants> treePattern,
            final Configurator<ConfiguredVariants, PlacedVariants> featureConfigurator,
            final SaplingGeneratorProvider<ConfiguredVariants, Generator> saplingGeneratorProvider
    ) {
        final String typeString = treeVariant.name().toLowerCase() + "_" + woodType.name().toLowerCase();

        CONFIGURED_VARIANTS = configuredVariants;
        CONFIGURED_FEATURE_HOLDER = new HashMap<>();
        PLACED_VARIANTS = placedVariants;
        PLACED_FEATURES_HOLDER = new HashMap<>();

        ALL_BLOCKS = new ArrayList<>();

        LEAVES = buildBlock(new LeavesBlock(), typeString + "_leaves");

        featureConfigurator.configureAndPlace(
                typeString,
                logs,
                LEAVES.BLOCK,
                treePattern,
                CONFIGURED_FEATURE_HOLDER,
                configuredVariants,
                PLACED_FEATURES_HOLDER,
                placedVariants
        );

        SAPLING = buildBlock(
                new SaplingBlock(saplingGeneratorProvider.register(CONFIGURED_FEATURE_HOLDER)),
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
    public interface TreePattern<ConfiguredVariant extends Enum<?>> {
        TreeFeatureConfig.Builder build(String type, List<BlockSet<?>> log, Block leaves, HashMap<ConfiguredVariant, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> configuredFeatures);
    }

    public interface Configurator<ConfiguredVariant extends Enum<?>, PlacedVariant extends Enum<?>> {
        void configureAndPlace(String type,
                               List<BlockSet<?>> log,
                               Block leaves,
                               TreePattern<ConfiguredVariant> treePattern,
                               HashMap<ConfiguredVariant, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> configuredFeatureHolder,
                               ConfiguredVariant[] configuredVariants,
                               HashMap<PlacedVariant, RegistryEntry<PlacedFeature>> placedFeatureHolder,
                               PlacedVariant[] placedVariants

        );
    }

    public interface SaplingGeneratorProvider<FV extends Enum<?>, S extends SaplingGenerator> {
        S register(HashMap<FV, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> configuredFeatures);
    }
}
