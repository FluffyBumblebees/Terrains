package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.BasicIterator;
import net.fluffybumblebee.terrains.util.registration.feature_set.SetRegistrar;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static net.fluffybumblebee.terrains.util.registration.feature_set.EasyIf.onIf;

public class FullTreeSetConfig<Generator extends SaplingGenerator> implements SetRegistrar {
    public final HashMap<String, PrimitiveTreeSetConfig<Generator>> TREE_CONFIG;
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final WoodSetConfig WOOD_SET;


    public FullTreeSetConfig(
            @NotNull TreeType<Generator> config
    ) {
        ALL_BLOCKS = new ArrayList<>();
        WOOD_SET = new WoodSetConfig(config.woodType());
        TREE_CONFIG = new HashMap<>();

        List<BlockSet<?>> logVariants;
        if (config.logVariants() != null) {
            logVariants = new ArrayList<>(config.logVariants());
            logVariants.add(WOOD_SET.LOG);
        } else logVariants = List.of(WOOD_SET.LOG);

        for (String treeVariant : config.treeVariants()) {
            TREE_CONFIG.put(treeVariant, new PrimitiveTreeSetConfig<>(
                    logVariants,
                    config.woodType(),
                    treeVariant,
                    config.configuredVariants(),
                    config.uniquePlacedVariants(),
                    config.treePattern(),
                    config.featureConfigurator(),
                    config.saplingGeneratorProvider()
            ));
        }

        if (config.logVariants() != null) {
            ALL_BLOCKS.addAll(config.logVariants());
        }
        ALL_BLOCKS.addAll(WOOD_SET.getAllBlockSets());

        BasicIterator<String> treeVariantIterator = () -> Arrays.asList(config.treeVariants());
        treeVariantIterator.forEach(featureSets -> TREE_CONFIG.get(featureSets).getBlockIterator().forEach(blockSet ->
                onIf(!(blockSet.BLOCK instanceof FlowerPotBlock),() -> ALL_BLOCKS.add(blockSet)))
        );

       if (config.logVariants() != null) {
            BasicIterator<BlockSet<?>> woodSetIterator = config::logVariants;
            woodSetIterator.forEach(variants ->
                    StrippableBlockRegistry.register(variants.BLOCK, WOOD_SET.STRIPPED_LOG.BLOCK)
            );
        }
    }

    @Override
    public List<Item> getAllItems() {
        return WOOD_SET.getAllItems();
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }
}
