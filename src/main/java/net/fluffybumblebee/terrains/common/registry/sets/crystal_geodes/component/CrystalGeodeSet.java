package net.fluffybumblebee.terrains.common.registry.sets.crystal_geodes.component;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.instances.block.crystals.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.generateFeature;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;

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

        CORUNDUM_CLUSTER = buildCrystal(new CorundumCluster(), colour + "_corundum_cluster");
        WAXED_CORUNDUM = buildCrystal(new CorundumBlock(), "waxed_" + colour + "_corundum");


        CORUNDUM = buildCrystal(new CorundumBlock(() -> CORUNDUM_CLUSTER.BLOCK), colour + "_corundum");
        CORUNDUM_SLAB = buildCrystal(new CorundumCrystalSlab(), colour + "_corundum_slab");
        CORUNDUM_STAIRS = buildCrystal(new CorundumCrystalStairs(), colour + "_corundum_stairs");
        CORUNDUM_PANE = buildCrystal(new CorundumCrystalPane(), colour + "_corundum_pane");

        CRYSTAL = buildCrystal(new CrystalBlock(), colour + "_crystal");
        CRYSTAL_SLAB = buildCrystal(new CorundumCrystalSlab(), colour + "_crystal_slab");
        CRYSTAL_STAIRS = buildCrystal(new CorundumCrystalStairs(), colour + "_crystal_stairs");
        CRYSTAL_PANE = buildCrystal(new CorundumCrystalPane(), colour + "_crystal_pane");


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

    private static <B extends Block> BlockSet<B> buildCrystal(B block, String name) {
        return buildBlock(block, name, ItemGroup.BUILDING_BLOCKS);
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

    public static <B extends Block, C extends AmethystClusterBlock> RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> registerGeode(
            B corundum,
            B crystal,
            C cluster,
            String colour
    ) {
        WeightedBlockStateProvider outerLayers = new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                .add(crystal.getDefaultState(), 5000)
                .add(corundum.getDefaultState(), 500)
                .add(Blocks.COAL_BLOCK.getDefaultState(), 30)
                .add(Blocks.COPPER_BLOCK.getDefaultState(), 20)
                .add(Blocks.IRON_BLOCK.getDefaultState(), 15)
                .add(Blocks.REDSTONE_BLOCK.getDefaultState(), 15)
                .add(Blocks.LAPIS_BLOCK.getDefaultState(), 15)
                .add(Blocks.GOLD_BLOCK.getDefaultState(), 10)
                .add(Blocks.EMERALD_BLOCK.getDefaultState(), 2)
                .add(Blocks.DIAMOND_BLOCK.getDefaultState(), 1)
                .build());

        return ConfiguredFeatures.register(
                getNamespaceVar() + colour + "_geode",
                Feature.GEODE,
                new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                BlockStateProvider.of(Blocks.AIR),
                                BlockStateProvider.of(crystal),
                                BlockStateProvider.of(corundum),
                                outerLayers,
                                outerLayers,
                                List.of(cluster.getDefaultState()),
                                BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                        new GeodeLayerThicknessConfig(3.4, 5.6, 7.2, 9.4),
                        new GeodeCrackConfig(0.95, 2.0, 2), 0.35, 0.083, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16,
                        16,
                        0.05,
                        1)
        );
    }

    public static RegistryEntry<PlacedFeature> registerPlacedGeode(RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> geode, String name) {
        return register(
                getNamespaceVar() + name + "_geode_placed",
                geode,
                RarityFilterPlacementModifier.of(1440),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6),
                        YOffset.fixed(30)),
                BiomePlacementModifier.of()
        );
    }
}
