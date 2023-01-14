package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSet.FeatureConfigurator;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSet.TreeType;
import net.fluffybumblebee.terrains.util.registration.feature_set.RegistrySet;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.List;

public class FullTreeSetConfigs {
    private static final RegistrySet<TreeType<?, ?>, FullTreeSet<?, ?>> FULL_TREES = new RegistrySet<>(
            List.of(MapleTreeType.CONFIG),
            FullTreeSet::new
    );


    @SuppressWarnings("unchecked")
    public static <Generator extends SaplingGenerator, Configurator extends FeatureConfigurator<Generator
            >> Configurator getFeaturesForType(TreeType<Generator, Configurator> configType) {
        return (Configurator)FULL_TREES.getTypeMap().get(configType).FEATURE_CONFIGURATOR;
    }
}


