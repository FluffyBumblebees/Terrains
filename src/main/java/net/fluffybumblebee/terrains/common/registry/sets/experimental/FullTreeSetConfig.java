package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FullTreeSetConfig<Generator extends SaplingGenerator> implements FeatureRegistrar<BlockSet<?>> {
    public final HashMap<String, PrimitiveTreeSetConfig<Generator>> TREE_CONFIG;
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final ExperimentalWoodSetConfig WOOD_SET;


    public FullTreeSetConfig(
            TreeType<Generator> config
    ) {
        ALL_BLOCKS = new ArrayList<>();
        WOOD_SET = new ExperimentalWoodSetConfig(config.woodType());
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
    }

    @Override
    public List<BlockSet<?>> getAll() {
        return ALL_BLOCKS;
    }
}
