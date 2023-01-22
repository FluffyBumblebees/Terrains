package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry.Storage;

public class TerrainsLootTables extends SimpleFabricLootTableProvider {
    public TerrainsLootTables(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> consumer) {
        leaves(consumer, FULL_TREES);
        leaves(consumer, STAINED_TREES);

        dropsItself(consumer, FOLIAGE, FLOWER);
        dropsItself(consumer, FULL_TREES, SAPLING);
        dropsItself(consumer, FULL_TREES, ALL_WOOD_BLOCKS);
        dropsItself(consumer, STAINED_TREES, SAPLING);
        dropsItself(consumer, CRYSTAL_GEODES, CRYSTAL);

    }

    private static void leaves(
            final BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer,
            final RegistrySet<?, ?> set
    ) {
        final var treeStorage = set.getRegistry().storage;
        final var leaves = treeStorage.get(LEAVES);
        for (int i = 0; i < leaves.size(); i++) {
            identifierBuilderBiConsumer.accept(
                    leaves.get(i).identifier().orElseThrow(),
                    BlockLootTableGenerator.leavesDrop(
                            leaves.get(i).block().orElseThrow(),
                            treeStorage.get(SAPLING).get(i).block().orElseThrow(),
                            0.05F
                    ));
        }
    }

    private static void dropsItself(
            final BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer,
            final RegistrySet<?, ?> set,
            final RegistryTypes type
    ) {
        for (Storage storage : set.getRegistry().storage.get(type)) {
            identifierBuilderBiConsumer.accept(
                    storage.identifier().orElseThrow(),
                    BlockLootTableGenerator.drops(storage.block().orElseThrow())
            );
        }
    }
}
