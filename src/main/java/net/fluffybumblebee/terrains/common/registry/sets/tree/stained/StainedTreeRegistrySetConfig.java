package net.fluffybumblebee.terrains.common.registry.sets.tree.stained;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeRegistrySetConfig;
import net.fluffybumblebee.terrains.common.world.feature.raw.StainedSaplingGenerator;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.RegistrySetCreator;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getNamespaceVar;
import static net.fluffybumblebee.terrains.util.registration.world.feature.TreeRegistration.*;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;

public final class StainedTreeRegistrySetConfig<Colour extends Enum<?>> implements RegistrySetCreator {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final PrimitiveTreeRegistrySetConfig<StainedSaplingGenerator> TREE_CONFIG;
    public final RegistryEntry<PlacedFeature> TREE_PLACED;

    public StainedTreeRegistrySetConfig(Colour type) {
        final String colour = type.name().toLowerCase();
        ALL_BLOCKS = new ArrayList<>();


        TREE_CONFIG = new PrimitiveTreeRegistrySetConfig<>(
                List.of(BlockSet.of(Blocks.OAK_LOG, new Identifier("oak_log"))),
                colour,
                new String[] {
                        colour,
                        colour + "_bees",
                        "fat_" + colour,
                        "fat_" + colour + "_bees"
                },
                new String[0],
                (colours, log, leaves) -> null,
                (colours, log, leaves, treePattern, configuredFeatureHolder, configuredVariants, placedFeatureHolder,
                 placedVariants) -> {
                    configuredFeatureHolder.put(configuredVariants[0], defOak(configuredVariants[0], leaves));
                    configuredFeatureHolder.put(configuredVariants[1], defOakBees(configuredVariants[1], leaves));
                    configuredFeatureHolder.put(configuredVariants[2], fatOak(configuredVariants[2], leaves));
                    configuredFeatureHolder.put(configuredVariants[3], fatOakBees(configuredVariants[3], leaves));

                    for (String variants : configuredVariants) {
                        placedFeatureHolder.put(variants, register(
                                getNamespaceVar() + colours + "_" + variants + "_placed",
                                RegistryEntry.of(configuredFeatureHolder.get(variants).value()),
                                PlacedFeatures.wouldSurvive(Blocks.DIRT)
                        ));
                    }
                },
                (configuredFeatures, variants) -> new StainedSaplingGenerator(
                        () -> configuredFeatures.get(variants[0]),
                        () -> configuredFeatures.get(variants[1]),
                        () -> configuredFeatures.get(variants[2]),
                        () -> configuredFeatures.get(variants[3])
                )
        );

        var sapling = TREE_CONFIG.SAPLING.BLOCK;
        addAllBlocks(new BlockSet[] {
                TREE_CONFIG.LEAVES,
                TREE_CONFIG.SAPLING,
                TREE_CONFIG.POTTED_SAPLING
        });

        var featureHolder = TREE_CONFIG.CONFIGURED_FEATURE_HOLDER;
        String[] variants = TREE_CONFIG.CONFIGURED_VARIANTS;

        TREE_PLACED = createPlaced(
                featureHolder.get(variants[0]),
                featureHolder.get(variants[1]),
                featureHolder.get(variants[2]),
                featureHolder.get(variants[3]),
                sapling,
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
        generateFeature(TREE_PLACED, BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION);

    }
}
