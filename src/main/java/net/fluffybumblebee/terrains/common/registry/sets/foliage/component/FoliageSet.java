package net.fluffybumblebee.terrains.common.registry.sets.foliage.component;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildFlammableBlock;

public final class FoliageSet<B extends Block, T extends Enum<?>> implements RegistrySetCreator {

    private final List<BlockSet<?>> ALL_BLOCKS;

    public FoliageSet(TypesFoliage<B, T> foliageTypes) {
        ALL_BLOCKS = new ArrayList<>();

        for (T types : foliageTypes.types()) {
            final String string = types.name().toLowerCase() + "_" + foliageTypes.type_suffix();

            final var block = buildFlammableBlock(
                    foliageTypes.blockInstanceProvider().getInstance(types),
                    string
            );

            final var potBlock = new BlockSet.Builder<>(
                    new ConfiguredFlowerPotBlock(block.BLOCK),
                    getIdentifier("potted_" + string)
            ).build();

            ALL_BLOCKS.add(block);
            ALL_BLOCKS.add(potBlock);
        }
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }


    public interface FoliageProvider <B extends Block, T extends Enum<?>> {
        B getInstance(T type);
    }
}
