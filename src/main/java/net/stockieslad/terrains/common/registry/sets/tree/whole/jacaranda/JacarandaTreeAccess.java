package net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda;

import net.stockieslad.terrains.common.registry.sets.RegistrySets;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaTypes;
import net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.UniqueMapleFeatures;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JACARANDA_TREES;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JacarandaFeatures;
import static net.stockieslad.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.accessTreeFeatures;
import static net.stockieslad.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.getUniqueTreeFeatures;

@SuppressWarnings("unused")
public class JacarandaTreeAccess {

    public static WholeTreeSet<?, ?> getJacarandaTree() {
        return RegistrySets.FULL_TREES.getTypeMap().get(JACARANDA_TREES);
    }

    public static UniqueMapleFeatures getUniqueJacarandaFeatures() {
        return getUniqueTreeFeatures(getJacarandaTree());
    }

    public static JacarandaFeatures getFeature(String treeVariant) {
        return accessTreeFeatures(JACARANDA_TREES, treeVariant);
    }

    public static JacarandaFeatures getFeature(JacarandaTypes type) {
        return accessTreeFeatures(JACARANDA_TREES, type);
    }

    public static RegistryEntry<PlacedFeature> getPlacedNoBees(JacarandaTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_NO_BEES;
    }

    public static RegistryEntry<PlacedFeature> getPlacedBees(JacarandaTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_BEES;
    }
}
