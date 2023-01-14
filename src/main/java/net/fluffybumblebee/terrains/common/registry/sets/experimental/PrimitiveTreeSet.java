package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredLeavesBlock;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredSaplingBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildBlock;

public final class PrimitiveTreeSet<Generator extends SaplingGenerator> implements RegistrySetCreator {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final BlockSet<ConfiguredLeavesBlock> LEAVES;
    public final BlockSet<ConfiguredSaplingBlock> SAPLING;
    public final BlockSet<ConfiguredFlowerPotBlock> POTTED_SAPLING;

    public PrimitiveTreeSet(
            final Config<Generator> config
    ) {
        ALL_BLOCKS = new ArrayList<>();
        String treeType = config.treeType;
        SaplingGeneratorProvider<Generator> saplingGeneratorProvider = config.saplingGeneratorProvider;


        LEAVES = buildBlock(new ConfiguredLeavesBlock(), treeType + "_leaves");

        SAPLING = buildBlock(
                new ConfiguredSaplingBlock(saplingGeneratorProvider.register(LEAVES.BLOCK)),
                treeType + "_sapling"
        );

        POTTED_SAPLING = new BlockSet.Builder<>(
                new ConfiguredFlowerPotBlock(SAPLING.BLOCK),
                getIdentifier("potted_" + treeType +  "_sapling")
        ).build();

        addAllBlocks(new BlockSet[] {
                LEAVES,
                SAPLING,
                POTTED_SAPLING
        });
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }

    public interface SaplingGeneratorProvider<Generator extends SaplingGenerator> {
        Generator register(LeavesBlock leaves);
    }

    public record Config<Generator extends SaplingGenerator>(
            String treeType,
            SaplingGeneratorProvider<Generator> saplingGeneratorProvider
    ) {}
}
