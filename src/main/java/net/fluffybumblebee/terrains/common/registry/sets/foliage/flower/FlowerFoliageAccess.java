package net.fluffybumblebee.terrains.common.registry.sets.foliage.flower;

import net.fluffybumblebee.terrains.common.registry.sets.foliage.component.FoliageSet;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.component.FoliageSet.UniqueFoliageSetHolder;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.*;

public class FlowerFoliageAccess {
    public static FoliageSet<?, ?, ?> accessFlowerSet() {
        return RegistrySetTypeTools.accessFoliageSet(ALL_FLOWERS);
    }
    public static UniqueFoliageSetHolder<?> accessFlowerConfig(FlowerTypes type) {
        return accessFlowerSet().foliageHolder.get(type);
    }

    public static FlowerFeatures accessFlowerFeatures(FlowerTypes types) {
        return (FlowerFeatures)accessFlowerConfig(types).featureInstance();
    }

    public static RegistryEntry<PlacedFeature> accessDefaultPlacedFeature(FlowerTypes types) {
        return accessFlowerFeatures(types).PLACED_FLOWER_PATCH;
    }
}
