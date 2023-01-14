package net.fluffybumblebee.terrains.util.registration.registry_set.access;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSet;
import net.minecraft.block.sapling.SaplingGenerator;

import static net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSetConfigs.FULL_TREES;

public class TreeSetFeatureAccess {

    @SuppressWarnings("unchecked")
    public static <Generator extends SaplingGenerator, Configurator extends FullTreeSet.FeatureConfigurator<Generator>>
    Configurator getTreeFeaturesForType(FullTreeSet.TreeType<Generator, Configurator> configType) {
        return (Configurator) FULL_TREES.getTypeMap().get(configType).FEATURE_CONFIGURATOR;
    }
}
