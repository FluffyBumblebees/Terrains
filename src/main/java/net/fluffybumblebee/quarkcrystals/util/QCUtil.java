package net.fluffybumblebee.quarkcrystals.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fluffybumblebee.quarkcrystals.block.custom.CorundumCluster;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class QCUtil {
    public static <B extends Block> void registerBlock(B block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(IdentifierUtil.ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(IdentifierUtil.ID, name), new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }

    public static <B extends Block, C extends CorundumCluster> RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> registerGeode(B corundum, B crystal, C cluster, String colour) {
        WeightedBlockStateProvider outerLayers = new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                .add(crystal.getDefaultState(), 5000)
                .add(corundum.getDefaultState(), 100)
                .add(Blocks.COAL_BLOCK.getDefaultState(), 24)
                .add(Blocks.COPPER_BLOCK.getDefaultState(), 16)
                .add(Blocks.IRON_BLOCK.getDefaultState(), 12)
                .add(Blocks.REDSTONE_BLOCK.getDefaultState(), 12)
                .add(Blocks.LAPIS_BLOCK.getDefaultState(), 12)
                .add(Blocks.GOLD_BLOCK.getDefaultState(), 8)
                .add(Blocks.EMERALD_BLOCK.getDefaultState(), 2)
                .add(Blocks.DIAMOND_BLOCK.getDefaultState(), 1)
                .build());

        return ConfiguredFeatures.register(
                IdentifierUtil.ID + ":" + colour + "_geode",
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
        return  PlacedFeatures.register(
                name + "_geode_placed",
                geode,
                RarityFilterPlacementModifier.of(720),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6),
                        YOffset.fixed(30)),
                BiomePlacementModifier.of()
        );
    }
}
