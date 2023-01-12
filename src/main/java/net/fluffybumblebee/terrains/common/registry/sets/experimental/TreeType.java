package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.PrimitiveTreeSetConfig.Configurator;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.PrimitiveTreeSetConfig.SaplingGeneratorProvider;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.PrimitiveTreeSetConfig.TreePattern;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.List;

public record TreeType<Generator extends SaplingGenerator>(
        List<BlockSet<?>> logVariants,
        String woodType,
        String[] treeVariants,
        String[] configuredVariants,
        String[] uniquePlacedVariants,
        TreePattern treePattern,
        Configurator featureConfigurator,
        SaplingGeneratorProvider<Generator> saplingGeneratorProvider
    ) {

}
