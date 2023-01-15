package net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.component;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.instances.block.crystals.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.item.Item;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fluffybumblebee.terrains.util.QCUtil.registerGeode;
import static net.fluffybumblebee.terrains.util.QCUtil.registerPlacedGeode;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.generateFeature;

@SuppressWarnings("FieldCanBeLocal")
public final class CrystalGeodeSet<Type extends Enum<?>> implements RegistrySetCreator {
    public final List<BlockSet<?>> ALL_BLOCKS;

    public final BlockSet<CorundumCluster> CORUNDUM_CLUSTER;
    public final BlockSet<CorundumBlock> WAXED_CORUNDUM;

    public final BlockSet<CorundumBlock> CORUNDUM;
    public final BlockSet<CorundumCrystalSlab> CORUNDUM_SLAB;
    public final BlockSet<CorundumCrystalStairs> CORUNDUM_STAIRS;
    public final BlockSet<CorundumCrystalPane> CORUNDUM_PANE;


    public final BlockSet<CrystalBlock> CRYSTAL;
    public final BlockSet<CorundumCrystalSlab> CRYSTAL_SLAB;
    public final BlockSet<CorundumCrystalStairs> CRYSTAL_STAIRS;
    public final BlockSet<CorundumCrystalPane> CRYSTAL_PANE;


    private final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CONFIGURED_GEODE;
    private final RegistryEntry<PlacedFeature> PLACED_GEODE;

    public CrystalGeodeSet(Type type) {
        final String colour = type.name().toLowerCase();
        ALL_BLOCKS = new ArrayList<>();

        CORUNDUM_CLUSTER = buildBlock(new CorundumCluster(), colour + "_corundum_cluster");
        WAXED_CORUNDUM = buildBlock(new CorundumBlock(), "waxed_" + colour + "_corundum");


        CORUNDUM = buildBlock(new CorundumBlock(() -> CORUNDUM_CLUSTER.BLOCK), colour + "_corundum");
        CORUNDUM_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_corundum_slab");
        CORUNDUM_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_corundum_stairs");
        CORUNDUM_PANE = buildBlock(new CorundumCrystalPane(), colour + "_corundum_pane");

        CRYSTAL = buildBlock(new CrystalBlock(), colour + "_crystal");
        CRYSTAL_SLAB = buildBlock(new CorundumCrystalSlab(), colour + "_crystal_slab");
        CRYSTAL_STAIRS = buildBlock(new CorundumCrystalStairs(), colour + "_crystal_stairs");
        CRYSTAL_PANE = buildBlock(new CorundumCrystalPane(), colour + "_crystal_pane");


        addAllBlocks(new BlockSet[] {
                CORUNDUM_CLUSTER,
                WAXED_CORUNDUM,
                CORUNDUM,
                CORUNDUM_SLAB,
                CORUNDUM_STAIRS,
                CORUNDUM_PANE,
                CRYSTAL,
                CRYSTAL_SLAB,
                CRYSTAL_STAIRS,
                CRYSTAL_PANE
        });

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
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }

    @Override
    public void generationEvent() {
        generateFeature(PLACED_GEODE, BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES);

    }
}
