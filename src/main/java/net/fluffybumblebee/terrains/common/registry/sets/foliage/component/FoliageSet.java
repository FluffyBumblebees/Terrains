package net.fluffybumblebee.terrains.common.registry.sets.foliage.component;

import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildFlammableBlock;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public final class FoliageSet<B extends Block, T extends Enum<?>> implements RegistrySetCreator {

    private final List<BlockSet<?>> foliage;
    private final List<BlockSet<?>> pottedFoliage;
    private final RegistryTypes type;


    public FoliageSet(TypesFoliage<B, T> foliageTypes) {
        foliage = new ArrayList<>();
        pottedFoliage = new ArrayList<>();
        type = foliageTypes.type();

        for (T types : foliageTypes.types()) {
            final String string = types.name().toLowerCase() + "_" + foliageTypes.type_suffix();

            final BlockSet<B> foliage;
            foliage = buildFlammableBlock(
                    foliageTypes.blockInstanceProvider().getInstance(types),
                    string
            );

            final var potBlock = new BlockSet.Builder<>(
                    new ConfiguredFlowerPotBlock(foliage.BLOCK),
                    getIdentifier("potted_" + string)
            ).build();

            this.foliage.add(foliage);
            this.pottedFoliage.add(potBlock);
        }
    }

    @Override
    public void registryEvent(SetRegistry registry) {
        registry.iterate(foliage).forEach(element -> registry.blockSet(type, element));
        registry.iterate(pottedFoliage).forEach(element -> registry.blockSetPotted(POTTED_BLOCK, element));
    }


    public interface FoliageProvider <B extends Block, T extends Enum<?>> {
        B getInstance(T type);
    }
}
