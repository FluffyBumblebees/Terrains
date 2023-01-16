package net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple;

import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleFeatures;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.UniqueMapleFeatures;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.accessTreeFeatures;
import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.getUniqueFeatures;

public class MapleTreeAccess {

    public static WholeTreeSet<?, ?, ?> getMapleTree() {
        return AllRegistrySets.FULL_TREES.getTypeMap().get(MapleTreeType.MAPLE_TREES);
    }

    public static UniqueMapleFeatures getUniqueMapleFeatures() {
        return getUniqueFeatures(getMapleTree());
    }

    public static MapleFeatures getFeature(String treeVariant) {
        return accessTreeFeatures(MapleTreeType.MAPLE_TREES, treeVariant);
    }

    public static RegistryEntry<PlacedFeature> getPlacedNoBees(MapleTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_NO_BEES;
    }

    public static RegistryEntry<PlacedFeature> getPlacedBees(MapleTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_BEES;
    }
}
