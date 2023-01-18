package net.fluffybumblebee.terrains.common.registry.sets.tree.component;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.terrains.common.instances.block.wood.WoodBlock;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureSupplier;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class WholeTreeSet<
        Generator extends SaplingGenerator,
        FeatureProvider extends FeatureCreator<Generator>,
        UniqueFeatures>
        implements RegistrySetCreator {

    public final HashMap<String, PrimitiveTreeSet<Generator, FeatureProvider>> PRIMITIVE_TREE_CONFIGS;
    public final UniqueFeatures UNIQUE_FEATURES;

    public final String WOOD_TYPE;
    public final String[] TREE_VARIANTS;
    public final List<BlockSet<WoodBlock>> ADDITIONAL_LOG_VARIANTS;

    private final List<BlockSet<?>> ALL_BLOCKS;
    public final WoodSet WOOD_SET;

    public WholeTreeSet(
            @NotNull final TreeType<Generator, FeatureProvider, UniqueFeatures> config
    ) {
        WOOD_TYPE = config.woodType;
        TREE_VARIANTS = config.treeVariants;
        PRIMITIVE_TREE_CONFIGS = new HashMap<>();
        ADDITIONAL_LOG_VARIANTS = new ArrayList<>();
        WOOD_SET = new WoodSet(WOOD_TYPE);
        ADDITIONAL_LOG_VARIANTS.add((BlockSet<WoodBlock>) WOOD_SET.LOG);

        final var logVariants = config.additionalLogVariants;
        if (logVariants != null) {
            for (String logVariant : logVariants) {
                ADDITIONAL_LOG_VARIANTS.add((BlockSet<WoodBlock>) BlockSet.buildFlammableBlock(new WoodBlock(),
                        logVariant));
            }
        }

        final List<Block> logs = new ArrayList<>();
        for (BlockSet<WoodBlock> log : ADDITIONAL_LOG_VARIANTS) {
            logs.add(log.BLOCK);
        }

        final List<Block> leaves = new ArrayList<>();
        for (String treeVariant : config.treeVariants) {
            PRIMITIVE_TREE_CONFIGS.put(treeVariant, new PrimitiveTreeSet<>(
                    new PrimitiveTreeSet.Config<>(
                            treeVariant + "_" + WOOD_TYPE ,
                            logs,
                            config.featureSupplier
                    )
            ));
            leaves.add(PRIMITIVE_TREE_CONFIGS.get(treeVariant).LEAVES.BLOCK);
        }

        UNIQUE_FEATURES = config.uniqueFeatureSupplier.get(logs, leaves, WOOD_TYPE);

        ALL_BLOCKS = new ArrayList<>();
        ALL_BLOCKS.addAll(ADDITIONAL_LOG_VARIANTS);
        ALL_BLOCKS.remove(WOOD_SET.LOG);
        ALL_BLOCKS.addAll(WOOD_SET.getAllBlockSets());

        Quickerator<String> treeVariantIterator = () -> Arrays.asList(TREE_VARIANTS);
        treeVariantIterator.forEach(featureSets -> PRIMITIVE_TREE_CONFIGS.get(featureSets).getBlockIterator()
                .forEach(ALL_BLOCKS::add)
        );

        Quickerator<BlockSet<WoodBlock>> woodSetIterator = () -> ADDITIONAL_LOG_VARIANTS;
        woodSetIterator.forEach(variants ->
                StrippableBlockRegistry.register(variants.BLOCK, WOOD_SET.STRIPPED_LOG.BLOCK)
        );
    }

    @Override
    public List<Item> getAllItems() {
        return WOOD_SET.getAllItems();
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }


    public record TreeType<
            Generator extends SaplingGenerator,
            FeatureProvider extends FeatureCreator<Generator>,
            UniqueFeatures>(
            String woodType,
            String[] treeVariants,
            @Nullable String[] additionalLogVariants,
            FeatureSupplier<Generator, FeatureProvider> featureSupplier,
            UniqueFeatureSupplier<UniqueFeatures> uniqueFeatureSupplier
    ) {}

    public interface UniqueFeatureSupplier <UniqueFeatures> {
        UniqueFeatures get(final List<Block> allLogs, final List<Block> allLeaves, final String type);
    }
}
