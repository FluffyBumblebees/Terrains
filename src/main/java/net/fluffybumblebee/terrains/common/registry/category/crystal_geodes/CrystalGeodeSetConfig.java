package net.fluffybumblebee.terrains.common.registry.category.crystal_geodes;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.default_abstract.block.*;
import net.fluffybumblebee.terrains.util.registration.abstract_collection.abstract_helper.FeatureRegistrar;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fluffybumblebee.terrains.util.QCUtil.registerGeode;
import static net.fluffybumblebee.terrains.util.QCUtil.registerPlacedGeode;
import static net.fluffybumblebee.terrains.util.registration.block.BlockBuilder.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.generateFeature;

@SuppressWarnings("FieldCanBeLocal")
public final class CrystalGeodeSetConfig<E extends Enum<?>> implements FeatureRegistrar {

    public final BlockBuilder<CorundumBlock> WAXED_CORUNDUM;
    public final BlockBuilder<CorundumCluster> CORUNDUM_CLUSTER;

    public final BlockBuilder<CorundumBlock> CORUNDUM;
    public final BlockBuilder<CorundumCrystalPane> CORUNDUM_PANE;
    public final BlockBuilder<CorundumCrystalSlab> CORUNDUM_SLAB;
    public final BlockBuilder<CorundumCrystalStairs> CORUNDUM_STAIRS;

    public final BlockBuilder<CrystalBlock> CRYSTAL;
    public final BlockBuilder<CorundumCrystalPane> CRYSTAL_PANE;
    public final BlockBuilder<CorundumCrystalSlab> CRYSTAL_SLAB;
    public final BlockBuilder<CorundumCrystalStairs> CRYSTAL_STAIRS;

    public final BlockBuilder<?>[] ALL_BLOCKS;

    private final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CONFIGURED_GEODE;
    private final RegistryEntry<PlacedFeature> PLACED_GEODE;

    public CrystalGeodeSetConfig(E type) {
        String colour = type.name().toLowerCase();

        WAXED_CORUNDUM = buildBlock(new CorundumBlock(), "waxed_" + colour + "_corundum");
        CORUNDUM_CLUSTER = buildBlock(new CorundumCluster(), colour + "_corundum_cluster");

        CORUNDUM = buildBlock(new CorundumBlock(CORUNDUM_CLUSTER::getBlock), colour + "_corundum");
        CORUNDUM_PANE = buildBlock(new CorundumCrystalPane(), colour + "_corundum_pane");
        CORUNDUM_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_corundum_slab");
        CORUNDUM_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_corundum_stairs");

        CRYSTAL = buildBlock(new CrystalBlock(), colour + "_crystal");
        CRYSTAL_PANE = buildBlock(new CorundumCrystalPane(), colour + "_crystal_pane");
        CRYSTAL_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_crystal_slab");
        CRYSTAL_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_crystal_stairs");

        ALL_BLOCKS = new BlockBuilder[] {
                WAXED_CORUNDUM,
                CORUNDUM_CLUSTER,
                CORUNDUM,
                CORUNDUM_PANE,
                CORUNDUM_SLAB,
                CORUNDUM_STAIRS,
                CRYSTAL,
                CRYSTAL_PANE,
                CRYSTAL_SLAB,
                CRYSTAL_STAIRS
        };

        registerWaxableBlockPair(CORUNDUM.getBlock(), WAXED_CORUNDUM.getBlock());

        CONFIGURED_GEODE = registerGeode(
                CORUNDUM.getBlock(),
                CRYSTAL.getBlock(),
                CORUNDUM_CLUSTER.getBlock(),
                colour
        );
        PLACED_GEODE = registerPlacedGeode(
                CONFIGURED_GEODE,
                colour
        );

        generateFeature(PLACED_GEODE, BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES);
    }

    @Override
    public BlockBuilder<?>[] getAllBlocks() {
        return ALL_BLOCKS;
    }
}
