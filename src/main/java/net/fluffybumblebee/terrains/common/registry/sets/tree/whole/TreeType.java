package net.fluffybumblebee.terrains.common.registry.sets.tree.whole;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeRegistrySetConfig.*;

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
