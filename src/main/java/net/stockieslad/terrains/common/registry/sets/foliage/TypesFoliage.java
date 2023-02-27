package net.stockieslad.terrains.common.registry.sets.foliage;

import net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet.FeatureSupplier;
import net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet.FoliageProvider;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;

import java.util.List;

import static net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet.*;
import static net.stockieslad.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.ALL_FLOWERS;

public record TypesFoliage<B extends Block, T extends Enum<?>, FeatureInstance extends FeatureCreator>(
        List<T> types,
        FoliageProvider<B, T> blockInstanceProvider,
        String type_suffix,
        boolean shouldMakePotted,
        FeatureSupplier<B, FeatureInstance> featureSupplier,
        RegistryTypes registryTypes
)  {
    public static final List<TypesFoliage<?, ?, ?>> ALL_FOLIAGE = List.of(
            ALL_FLOWERS
    );
}
