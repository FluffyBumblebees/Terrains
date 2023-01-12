package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.BasicTreeSetConfig.Configurator;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.BasicTreeSetConfig.SaplingGeneratorProvider;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.BasicTreeSetConfig.TreePattern;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSetConfig;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComplexTreeSetConfig<
        WoodType extends Enum<?>,
        TreeVariant extends Enum<?>,
        ConfiguredVariants extends Enum<?>,
        PlacedVariants extends Enum<?>,
        Generator extends SaplingGenerator>
        implements FeatureRegistrar<BlockSet<?>> {
    public final HashMap<TreeVariant, BasicTreeSetConfig
           <WoodType,
                   TreeVariant,
            ConfiguredVariants,
            PlacedVariants,
            Generator>> TREE_CONFIG;
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final WoodSetConfig<WoodType> WOOD_SET;


    public ComplexTreeSetConfig(
            final List<BlockSet<?>> logVariants,
            final WoodType woodType,
            final TreeVariant[] treeVariants,
            final ConfiguredVariants[] configuredVariants,
            final PlacedVariants[] placedVariants,
            TreePattern treePattern,
            final Configurator<ConfiguredVariants, PlacedVariants> featureConfigurator,
            final SaplingGeneratorProvider<ConfiguredVariants, Generator> saplingGeneratorProvider
    ) {
        ALL_BLOCKS = new ArrayList<>();
        WOOD_SET = new WoodSetConfig<>(woodType);
        TREE_CONFIG = new HashMap<>();

        List<BlockSet<?>> logs;
        if (logVariants != null) {
            logs = new ArrayList<>(logVariants);
            logs.add(WOOD_SET.LOG);
        } else logs = List.of(WOOD_SET.LOG);

        for (TreeVariant variant : treeVariants) {
            TREE_CONFIG.put(variant, new BasicTreeSetConfig<>(
                    logs,
                    woodType,
                    variant,
                    configuredVariants,
                    placedVariants,
                    treePattern,
                    featureConfigurator,
                    saplingGeneratorProvider
            ));
        }
    }

    @Override
    public List<BlockSet<?>> getAll() {
        return ALL_BLOCKS;
    }
}
