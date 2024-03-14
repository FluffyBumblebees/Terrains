package net.stockieslad.terrains.common.registry.sets.tree.component;

import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.stockieslad.terrains.common.instances.block.plant.ConfiguredLeavesBlock;
import net.stockieslad.terrains.common.instances.block.plant.ConfiguredSaplingBlock;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.List;

import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;
import static net.stockieslad.terrains.util.registration.mass.UnsafeTriSet.buildBlock;
import static net.stockieslad.terrains.util.registration.mass.UnsafeTriSet.buildFlammableBlock;

public final class PrimitiveTreeSet<
        Generator extends SaplingGenerator,
        FeatureProvider extends PrimitiveTreeSet.FeatureCreator<Generator>
        > implements RegistrySetCreator {

    public final String TREE_TYPE;
    public final FeatureProvider TREE_FEATURES;

    public final UnsafeTriSet<ConfiguredSaplingBlock> SAPLING;
    public final UnsafeTriSet<ConfiguredLeavesBlock> LEAVES;
    public final UnsafeTriSet<ConfiguredFlowerPotBlock> POTTED_SAPLING;

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

        POTTED_SAPLING = new UnsafeTriSet.Builder<>(
                new ConfiguredFlowerPotBlock(SAPLING.BLOCK),
                getIdentifier("potted_" + TREE_TYPE +  "_sapling")
        ).build();
    }

    @Override
    public void register(final SetRegistry registry) {
        registry.triSet(RegistryTypes.LEAVES, LEAVES);
        registry.triSet(RegistryTypes.SAPLING, SAPLING);
        registry.triSetPotted(RegistryTypes.POTTED_BLOCK, POTTED_SAPLING);
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.CUTOUT);
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
