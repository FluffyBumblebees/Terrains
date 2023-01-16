package net.fluffybumblebee.terrains.common.registry.sets.tree.component;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.terrains.common.instances.block.wood.WoodBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.EasyIf.onIf;

@SuppressWarnings("unchecked")
public class WholeTreeSet<
        Generator extends SaplingGenerator,
        FeatureProvider extends PrimitiveTreeSet.FeatureCreator<Generator>>
        implements RegistrySetCreator {

    public final HashMap<String, PrimitiveTreeSet<Generator, FeatureProvider>> PRIMITIVE_TREE_CONFIGS;
    public final String WOOD_TYPE;
    public final String[] TREE_VARIANTS;
    final List<BlockSet<WoodBlock>> ADDITIONAL_LOG_VARIANTS;

    private final List<BlockSet<?>> ALL_BLOCKS;
    public final WoodSet WOOD_SET;



    public WholeTreeSet(
            @NotNull final TreeType<Generator, FeatureProvider> config
    ) {
        WOOD_TYPE = config.woodType;
        TREE_VARIANTS = config.treeVariants;
        PRIMITIVE_TREE_CONFIGS = new HashMap<>();
        ADDITIONAL_LOG_VARIANTS = new ArrayList<>();

        final var logVariants = config.additionalLogVariants;
        if (logVariants != null) {
            for (String logVariant : logVariants) {
                ADDITIONAL_LOG_VARIANTS.add((BlockSet<WoodBlock>) BlockSet.buildFlammableBlock(new WoodBlock(),
                        logVariant));
            }
        }

        WOOD_SET = new WoodSet(WOOD_TYPE);
        ADDITIONAL_LOG_VARIANTS.add((BlockSet<WoodBlock>) WOOD_SET.LOG);

        for (String treeVariant : config.treeVariants) {
            final List<Block> list = new ArrayList<>();
            for (BlockSet<WoodBlock> log : ADDITIONAL_LOG_VARIANTS) {
                list.add(log.BLOCK);
            }
            PRIMITIVE_TREE_CONFIGS.put(treeVariant, new PrimitiveTreeSet<>(
                    new PrimitiveTreeSet.Config<>(
                            treeVariant + "_" + WOOD_TYPE ,
                            list,
                            config.featureSupplier
                    )
            ));
        }

        ALL_BLOCKS = new ArrayList<>();
        ALL_BLOCKS.addAll(ADDITIONAL_LOG_VARIANTS);
        ALL_BLOCKS.addAll(WOOD_SET.getAllBlockSets());

        Quickerator<String> treeVariantIterator = () -> Arrays.asList(TREE_VARIANTS);
        treeVariantIterator.forEach(featureSets -> PRIMITIVE_TREE_CONFIGS.get(featureSets).getBlockIterator().forEach(blockSet ->
                onIf(!(blockSet.BLOCK instanceof FlowerPotBlock),() -> ALL_BLOCKS.add(blockSet)))
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


    public record TreeType<Generator extends SaplingGenerator,
            FeatureProvider extends PrimitiveTreeSet.FeatureCreator<Generator>>(
            String woodType,
            String[] treeVariants,
            @Nullable String[] additionalLogVariants,
            PrimitiveTreeSet.FeatureSupplier<Generator, FeatureProvider> featureSupplier
    ) {
    }
}
