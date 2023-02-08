package net.fluffybumblebee.terrains.cancer.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.fluffybumblebee.terrains.util.registration.mass.SafeTriSet;
import net.minecraft.block.Block;

import static net.fluffybumblebee.terrains.common.registry.sets.RegistrySetManager.*;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public class TerrainsBlockLootTables extends FabricBlockLootTableProvider {
    public TerrainsBlockLootTables(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        leaves(FULL_TREES);
        leaves(STAINED_TREES);
        dropsItself(FOLIAGE, FLOWER);
        dropsItself(FULL_TREES, SAPLING);
        dropsItself(FULL_TREES, ALL_WOOD_BLOCKS);
        dropsItself(STAINED_TREES, SAPLING);
        dropsItself(CRYSTAL_GEODES, CRYSTAL);
        dropsPotted(FOLIAGE);
        dropsPotted(FULL_TREES);
        dropsPotted(STAINED_TREES);
    }


    private void leaves(
            final RegistrySet<?, ?> set
    ) {
        final var treeStorage = set.getRegistry().storage;
        final var leaves = treeStorage.get(LEAVES);
        for (int i = 0; i < leaves.size(); i++) {
            leaves(
                    leaves.get(i).block().orElseThrow(),
                    treeStorage.get(SAPLING).get(i).block().orElseThrow()
            );
        }
    }

    private void dropsItself(
            final RegistrySet<?, ?> set,
            final RegistryTypes type
    ) {
        for (SafeTriSet safeTriSet : set.getRegistry().storage.get(type)) {
            final var block = safeTriSet.block().orElseThrow();
            addDrop(block);
        }
    }

    private void dropsPotted(RegistrySet<?, ?> foliage) {
        for (SafeTriSet safeTriSet : foliage.getRegistry().storage.get(POTTED_BLOCK)) {
            final var block = safeTriSet.block().orElseThrow();
            addPottedPlantDrop(block);
        }
    }

    private void leaves(Block leaves, Block sapling) {
        addDrop(leaves,
                leavesDrop(leaves,
                        sapling,
                        0.05f,
                        0.0625f,
                        0.083333336f,
                        0.1f
                )
        );
    }
}
