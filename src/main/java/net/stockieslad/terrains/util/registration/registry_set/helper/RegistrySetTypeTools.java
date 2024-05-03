package net.stockieslad.terrains.util.registration.registry_set.helper;

import net.stockieslad.terrains.common.registry.sets.RegistrySets;
import net.stockieslad.terrains.common.registry.sets.foliage.TypesFoliage;
import net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;

import static net.stockieslad.terrains.common.registry.sets.RegistrySets.FULL_TREES;


@SuppressWarnings("unchecked")
public class RegistrySetTypeTools {
    public static String[] EnumArrayToString(Enum<?>[] values) {
        int length = values.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = values[i].name().toLowerCase();
        } return strings;
    }

    @SuppressWarnings("unused")
    public static <
            GeneratorSupplier extends FeatureCreator,
            UniqueFeatures,
            Types extends Enum<?>
            >
    GeneratorSupplier accessTreeFeatures(TreeType<GeneratorSupplier, UniqueFeatures> treeType,
                                         Types treeVariant) {
        return accessTreeFeatures(treeType, treeVariant.name().toLowerCase());
    }

    public static <
            GeneratorSupplier extends FeatureCreator,
            UniqueFeatures
            >
    GeneratorSupplier accessTreeFeatures(TreeType<GeneratorSupplier, UniqueFeatures> treeType, String treeVariant) {
        return (GeneratorSupplier) FULL_TREES
                .getTypeMap()
                .get(treeType)
                .PRIMITIVE_TREE_CONFIGS
                .get(treeVariant)
                .TREE_FEATURES;
    }

    public static <UniqueFeatures> UniqueFeatures getUniqueTreeFeatures(WholeTreeSet<?, ?> treeVariant) {
        return (UniqueFeatures) treeVariant.UNIQUE_FEATURES;
    }

    public static FoliageSet<?, ?, ?> accessFoliageSet(TypesFoliage<?, ?, ?> type) {
        return RegistrySets
                .FOLIAGE
                .getTypeMap()
                .get(type);
    }
}
