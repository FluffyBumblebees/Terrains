package net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.instances.block.crystals.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fluffybumblebee.terrains.util.QCUtil.registerGeode;
import static net.fluffybumblebee.terrains.util.QCUtil.registerPlacedGeode;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.generateFeature;

@SuppressWarnings("FieldCanBeLocal")
public final class CrystalGeodeSetConfig<E extends Enum<?>> implements FeatureRegistrar<BlockSet<?>> {

    public final BlockSet<CorundumBlock> WAXED_CORUNDUM;
    public final BlockSet<CorundumCluster> CORUNDUM_CLUSTER;

    public final BlockSet<CorundumBlock> CORUNDUM;
    public final BlockSet<CorundumCrystalPane> CORUNDUM_PANE;
    public final BlockSet<CorundumCrystalSlab> CORUNDUM_SLAB;
    public final BlockSet<CorundumCrystalStairs> CORUNDUM_STAIRS;

    public final BlockSet<CrystalBlock> CRYSTAL;
    public final BlockSet<CorundumCrystalPane> CRYSTAL_PANE;
    public final BlockSet<CorundumCrystalSlab> CRYSTAL_SLAB;
    public final BlockSet<CorundumCrystalStairs> CRYSTAL_STAIRS;

    public final BlockSet<?>[] ALL_BLOCKS;

    private final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CONFIGURED_GEODE;
    private final RegistryEntry<PlacedFeature> PLACED_GEODE;

    public CrystalGeodeSetConfig(E type) {
        final String colour = type.name().toLowerCase();

        WAXED_CORUNDUM = buildBlock(new CorundumBlock(), "waxed_" + colour + "_corundum");
        CORUNDUM_CLUSTER = buildBlock(new CorundumCluster(), colour + "_corundum_cluster");

        CORUNDUM = buildBlock(new CorundumBlock(() -> CORUNDUM_CLUSTER.BLOCK), colour + "_corundum");
        CORUNDUM_PANE = buildBlock(new CorundumCrystalPane(), colour + "_corundum_pane");
        CORUNDUM_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_corundum_slab");
        CORUNDUM_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_corundum_stairs");

        CRYSTAL = buildBlock(new CrystalBlock(), colour + "_crystal");
        CRYSTAL_PANE = buildBlock(new CorundumCrystalPane(), colour + "_crystal_pane");
        CRYSTAL_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_crystal_slab");
        CRYSTAL_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_crystal_stairs");

        ALL_BLOCKS = new BlockSet[] {
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

        registerWaxableBlockPair(CORUNDUM.BLOCK, WAXED_CORUNDUM.BLOCK);

        CONFIGURED_GEODE = registerGeode(
                CORUNDUM.BLOCK,
                CRYSTAL.BLOCK,
                CORUNDUM_CLUSTER.BLOCK,
                colour
        );
        PLACED_GEODE = registerPlacedGeode(
                CONFIGURED_GEODE,
                colour
        );

        generateFeature(PLACED_GEODE, BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES);
    }

    @Override
    public BlockSet<?>[] getAll() {
        return ALL_BLOCKS;
    }
}
