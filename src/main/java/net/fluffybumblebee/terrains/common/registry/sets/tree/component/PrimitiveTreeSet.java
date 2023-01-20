package net.fluffybumblebee.terrains.common.registry.sets.tree.component;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredLeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredSaplingBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildFlammableBlock;

public final class PrimitiveTreeSet<
        Generator extends SaplingGenerator,
        FeatureProvider extends PrimitiveTreeSet.FeatureCreator<Generator>
        > implements RegistrySetCreator {

    public final String TREE_TYPE;
    public final FeatureProvider TREE_FEATURES;

    public final BlockSet<ConfiguredSaplingBlock> SAPLING;
    public final BlockSet<ConfiguredLeavesBlock> LEAVES;
    public final BlockSet<ConfiguredFlowerPotBlock> POTTED_SAPLING;

    public PrimitiveTreeSet(
            final Config<Generator, FeatureProvider> config
    ) {
        TREE_TYPE = config.treeType;
        LEAVES = buildFlammableBlock(new ConfiguredLeavesBlock(), TREE_TYPE + "_leaves");
        TREE_FEATURES = config.generatorSupplier.get(config.logs, LEAVES.BLOCK, TREE_TYPE);
        final Generator saplingGenerator = TREE_FEATURES.createNew();

        SAPLING = buildBlock(
                new ConfiguredSaplingBlock(saplingGenerator),
                TREE_TYPE + "_sapling"
        );

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ConfiguredFlowerPotBlock(SAPLING.BLOCK),
                getIdentifier("potted_" + TREE_TYPE +  "_sapling")
        ).build();
    }

    @Override
    public void registryEvent(final SetRegistry registry) {
        registry.blockSet(RegistryTypes.LEAVES, LEAVES);
        registry.blockSet(RegistryTypes.SAPLING, SAPLING);
        registry.blockSetPotted(RegistryTypes.POTTED_BLOCK, POTTED_SAPLING);
    }

    public record Config<Generator extends SaplingGenerator, FeatureProvider extends FeatureCreator<Generator>>(
            String treeType,
            List<Block> logs,
            FeatureSupplier<Generator, FeatureProvider> generatorSupplier
    ) {}

    public interface FeatureSupplier <Generator extends SaplingGenerator, FeatureProvider extends FeatureCreator<Generator>> {
        FeatureProvider get(final List<Block> allLogs, final ConfiguredLeavesBlock leavesBlock, final String type);
    }

    public interface FeatureCreator<Generator extends SaplingGenerator> {
        Generator createNew();
    }
}
