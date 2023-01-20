package net.fluffybumblebee.terrains.common.registry.sets.foliage.component;

import net.fluffybumblebee.terrains.common.registry.sets.foliage.component.FoliageSet.FoliageProvider;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.ALL_FLOWERS;

public record TypesFoliage<B extends Block, T extends Enum<?>>(
        List<T> types,
        FoliageProvider<B, T> blockInstanceProvider,
        String type_suffix,
        boolean shouldMakePotted,
        RegistryTypes type
)  {
    public static final List<TypesFoliage<?, ?>> ALL_FOLIAGE = List.of(
            ALL_FLOWERS
    );
}
