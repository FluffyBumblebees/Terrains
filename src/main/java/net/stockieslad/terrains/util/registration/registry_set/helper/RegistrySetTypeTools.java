package net.stockieslad.terrains.util.registration.registry_set.helper;

import net.stockieslad.terrains.common.registry.sets.RegistrySetManager;
import net.stockieslad.terrains.common.registry.sets.foliage.TypesFoliage;
import net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.minecraft.block.sapling.SaplingGenerator;

import static net.stockieslad.terrains.common.registry.sets.RegistrySetManager.FULL_TREES;


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
            Generator extends SaplingGenerator,
            GeneratorSupplier extends FeatureCreator<Generator>,
            UniqueFeatures,
            Types extends Enum<?>
            >
    GeneratorSupplier accessTreeFeatures(TreeType<Generator, GeneratorSupplier, UniqueFeatures> treeType,
                                         Types treeVariant) {
        return accessTreeFeatures(treeType, treeVariant.name().toLowerCase());
    }

    public static <
            Generator extends SaplingGenerator,
            GeneratorSupplier extends FeatureCreator<Generator>,
            UniqueFeatures
            >
    GeneratorSupplier accessTreeFeatures(TreeType<Generator, GeneratorSupplier, UniqueFeatures> treeType, String treeVariant) {
        return (GeneratorSupplier) FULL_TREES
                .getTypeMap()
                .get(treeType)
                .PRIMITIVE_TREE_CONFIGS
                .get(treeVariant)
                .TREE_FEATURES;
    }

    public static <UniqueFeatures> UniqueFeatures getUniqueTreeFeatures(WholeTreeSet<?, ?, ?> treeVariant) {
        return (UniqueFeatures) treeVariant.UNIQUE_FEATURES;
    }

    public static FoliageSet<?, ?, ?> accessFoliageSet(TypesFoliage<?, ?, ?> type) {
        return RegistrySetManager
                .FOLIAGE
                .getTypeMap()
                .get(type);
    }
}
