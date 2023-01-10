package net.fluffybumblebee.terrains.util.registration;

import net.fluffybumblebee.terrains.common.default_abstract.block.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fluffybumblebee.terrains.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.QCUtil.registerGeode;
import static net.fluffybumblebee.terrains.util.QCUtil.registerPlacedGeode;

public final class GeodeCollection <E extends Enum<?>> {
    public final BlockBuilder<?>[] ALL_BLOCKS;

    public final BlockBuilder<CorundumBlock> waxedCorundumBlock;
    public final BlockBuilder<CorundumCluster> corundumCluster;

    public final BlockBuilder<CorundumBlock> corundumBlock;
    public final BlockBuilder<CorundumCrystalPane> corundumPane;
    public final BlockBuilder<CorundumCrystalSlab> corundumSlab;
    public final BlockBuilder<CorundumCrystalStairs> corundumStairs;

    public final BlockBuilder<CrystalBlock> crystalBlock;
    public final BlockBuilder<CorundumCrystalPane> crystalPane;
    public final BlockBuilder<CorundumCrystalSlab> crystalSlab;
    public final BlockBuilder<CorundumCrystalStairs> crystalStairs;

    public final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CONFIGURED_GEODE;
    public final RegistryEntry<PlacedFeature> PLACED_GEODE;

    public GeodeCollection(E type) {
        String colour = type.name();
        waxedCorundumBlock = buildBlock(new CorundumBlock(), "waxed_" + colour + "corundum");
        corundumCluster = buildBlock(new CorundumCluster(), colour + "_corundum_bluster");

        corundumBlock = buildBlock(new CorundumBlock(corundumCluster::getBlock), colour + "_corundum");
        corundumPane = buildBlock(new CorundumCrystalPane(), colour + "_corundum_pane");
        corundumSlab = buildBlock(new CorundumCrystalSlab(), colour + "_corundum_slab");
        corundumStairs = buildBlock(new CorundumCrystalStairs(), colour + "corundum_stairs");

        crystalBlock = buildBlock(new CrystalBlock(), colour + "_crystal");
        crystalPane = buildBlock(new CorundumCrystalPane(), colour + "_crystal_pane");
        crystalSlab = buildBlock(new CorundumCrystalSlab(), colour + "crystal_slab");
        crystalStairs = buildBlock(new CorundumCrystalStairs(), colour + "crystal_stairs");

        ALL_BLOCKS = new BlockBuilder[] {
                waxedCorundumBlock,
                corundumCluster,
                corundumBlock,
                corundumPane,
                corundumSlab,
                corundumStairs,
                crystalBlock,
                crystalPane,
                crystalSlab,
                crystalStairs
        };

        registerWaxableBlockPair(corundumBlock.getBlock(), waxedCorundumBlock.getBlock());

        CONFIGURED_GEODE = registerGeode(
                corundumBlock.getBlock(),
                crystalBlock.getBlock(),
                corundumCluster.getBlock(),
                colour
        );
        PLACED_GEODE = registerPlacedGeode(
                CONFIGURED_GEODE,
                colour
        );

    }

    public static <B extends Block> BlockBuilder<B> buildBlock(B block, String name) {
        return new BlockBuilder<>(block, getIdentifier(name)).blockItem().build();
    }

    public void register(GenerationSettings.Builder builder) {
        builder.feature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                PLACED_GEODE
        );
    }
}
