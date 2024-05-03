package net.stockieslad.terrains.common.registry.sets.tree.whole.maple;

import net.stockieslad.terrains.common.registry.sets.RegistrySets;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleFeatures;
import net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MapleTypes;
import net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.UniqueMapleFeatures;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.stockieslad.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.accessTreeFeatures;
import static net.stockieslad.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.getUniqueTreeFeatures;

public class MapleTreeAccess {

    public static WholeTreeSet<?, ?> getMapleTree() {
        return RegistrySets.FULL_TREES.getTypeMap().get(MapleTreeType.MAPLE_TREES);
    }

    public static UniqueMapleFeatures getUniqueMapleFeatures() {
        return getUniqueTreeFeatures(getMapleTree());
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
