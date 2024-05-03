package net.stockieslad.terrains.common.registry.sets.crystal_geodes.component;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.stockieslad.abstractium.library.common.worldgen.biome.AbstractBiomes;
import net.stockieslad.abstractium.util.dynamic.Mimic;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.crystals.*;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;

import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.stockieslad.terrains.core.TerrainsCommon.ABSTRACTION;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;
import static net.stockieslad.terrains.util.registration.mass.UnsafeTriSet.buildBlock;

@SuppressWarnings({"FieldCanBeLocal"})
public final class CrystalGeodeSet<Type extends Enum<?>> implements RegistrySetCreator {
    public final String TYPE;
    public final List<UnsafeTriSet<?>> ALL_BLOCKS;

    public final UnsafeTriSet<CorundumCluster> CORUNDUM_CLUSTER;
    public final UnsafeTriSet<CorundumBlock> WAXED_CORUNDUM;

    public final UnsafeTriSet<CorundumBlock> CORUNDUM;
    public final UnsafeTriSet<CorundumCrystalSlab> CORUNDUM_SLAB;
    public final UnsafeTriSet<CorundumCrystalStairs> CORUNDUM_STAIRS;
    public final UnsafeTriSet<CorundumCrystalPane> CORUNDUM_PANE;


    public final UnsafeTriSet<CrystalBlock> CRYSTAL;
    public final UnsafeTriSet<CorundumCrystalSlab> CRYSTAL_SLAB;
    public final UnsafeTriSet<CorundumCrystalStairs> CRYSTAL_STAIRS;
    public final UnsafeTriSet<CorundumCrystalPane> CRYSTAL_PANE;


    private final Mimic CONFIGURED_GEODE;
    private final Mimic PLACED_GEODE;

    public CrystalGeodeSet(final Type type) {
        TYPE = type.name().toLowerCase();
        ALL_BLOCKS = new ArrayList<>();

        CORUNDUM_CLUSTER = buildCrystal(new CorundumCluster(), TYPE + "_corundum_cluster");
        WAXED_CORUNDUM = buildCrystal(new CorundumBlock(), "waxed_" + TYPE + "_corundum");

        CORUNDUM = buildCrystal(new CorundumBlock(() -> CORUNDUM_CLUSTER.BLOCK), TYPE + "_corundum");
        CORUNDUM_SLAB = buildCrystal(new CorundumCrystalSlab(), TYPE + "_corundum_slab");
        CORUNDUM_STAIRS = buildCrystal(new CorundumCrystalStairs(), TYPE + "_corundum_stairs");
        CORUNDUM_PANE = buildCrystal(new CorundumCrystalPane(), TYPE + "_corundum_pane");

        CRYSTAL = buildCrystal(new CrystalBlock(), TYPE + "_crystal");
        CRYSTAL_SLAB = buildCrystal(new CorundumCrystalSlab(), TYPE + "_crystal_slab");
        CRYSTAL_STAIRS = buildCrystal(new CorundumCrystalStairs(), TYPE + "_crystal_stairs");
        CRYSTAL_PANE = buildCrystal(new CorundumCrystalPane(), TYPE + "_crystal_pane");

        registerWaxableBlockPair(CORUNDUM.BLOCK, WAXED_CORUNDUM.BLOCK);

        CONFIGURED_GEODE = registerGeode(
                CORUNDUM.BLOCK,
                CRYSTAL.BLOCK,
                CORUNDUM_CLUSTER.BLOCK,
                TYPE
        );
        PLACED_GEODE = registerPlacedGeode(
                CONFIGURED_GEODE,
                TYPE
        );
    }

    private static <B extends Block> UnsafeTriSet<B> buildCrystal(final B block, final String name) {
        return buildBlock(block, name, ItemGroup.BUILDING_BLOCKS);
    }

    @Override
    public void register(final SetRegistry registry) {
        registry.triSet(RegistryTypes.CLUSTER,
                CORUNDUM_CLUSTER
        );
        registry.triSet(RegistryTypes.TRANSPARENT_FULL_BLOCK,
                WAXED_CORUNDUM,
                CORUNDUM,
                CORUNDUM_SLAB,
                CORUNDUM_STAIRS,
                CORUNDUM_PANE,
                CRYSTAL,
                CRYSTAL_SLAB,
                CRYSTAL_STAIRS,
                CRYSTAL_PANE
        );
        registry.triSet(RegistryTypes.CRYSTAL,
                WAXED_CORUNDUM,
                CORUNDUM,
                CORUNDUM_SLAB,
                CORUNDUM_STAIRS,
                CORUNDUM_PANE,
                CRYSTAL,
                CRYSTAL_SLAB,
                CRYSTAL_STAIRS,
                CRYSTAL_PANE,
                CORUNDUM_CLUSTER
        );
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.TRANSLUCENT, RenderTypes.CUTOUT);
    }

    @Override
    public void generate() {
        ABSTRACTION.getStructureGenerator()
                .generateFeature(
                        /*new Mimic(
                                registryKey(placedFeature()),
                                PLACED_GEODE.getKey().orElseThrow()
                        ),*/
                        ABSTRACTION.getRegistrar().getKeyFromEntry(PLACED_GEODE),
                        AbstractBiomes.OVERWORLD,
                        GenerationStep.Feature.UNDERGROUND_STRUCTURES
                );
        //generateFeature(PLACED_GEODE, BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_STRUCTURES);
    }

    public static <B extends Block, C extends AmethystClusterBlock> Mimic registerGeode(
            final B corundum,
            final B crystal,
            final C cluster,
            final String colour
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

        return /*ConfiguredFeatures.register(
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
        );*/

        ABSTRACTION.getRegistrar().registerConfiguredFeature(
                getIdentifier(colour + "_geode"),
                ABSTRACTION.getStructureCreator().createConfiguredFeature(
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
                                1
                        ),
                        Feature.GEODE
                )
        );
    }

    public static Mimic registerPlacedGeode(
            final Mimic geode, final String name
    ) {
        return /*PlacedFeatures.register(
                getNamespaceVar() + name + "_geode_placed",
                geode,
                RarityFilterPlacementModifier.of(1440),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6),
                        YOffset.fixed(30)),
                BiomePlacementModifier.of()
        );*/

        ABSTRACTION.getRegistrar().registerPlacedFeature(
                getIdentifier(name + "_geode_placed"),
                ABSTRACTION.getStructureCreator().createPlacedFeature(
                        geode,
                        List.of(
                                RarityFilterPlacementModifier.of(1440),
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6),
                                        YOffset.fixed(30)),
                                BiomePlacementModifier.of()
                        )
                )
        );
    }
}
