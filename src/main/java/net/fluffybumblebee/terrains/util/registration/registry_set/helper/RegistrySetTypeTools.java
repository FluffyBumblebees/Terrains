package net.fluffybumblebee.terrains.util.registration.registry_set.helper;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.PrimitiveTreeSet;
import net.minecraft.block.sapling.SaplingGenerator;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.FULL_TREES;


public class RegistrySetTypeTools {
    public static String[] EnumArrayToString(Enum<?>[] values) {
        int length = values.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = values[i].name().toLowerCase();
        } return strings;
    }

    @SuppressWarnings("unused")
    public static <Generator extends SaplingGenerator, GeneratorSupplier extends PrimitiveTreeSet.FeatureCreator<Generator>>
    GeneratorSupplier accessTreeFeatures(TreeType<Generator, GeneratorSupplier> treeType,
                                         Enum<?> treeVariant) {
        return accessTreeFeatures(treeType, treeVariant.name().toLowerCase());
    }

    @SuppressWarnings("unchecked")
    public static <Generator extends SaplingGenerator, GeneratorSupplier extends PrimitiveTreeSet.FeatureCreator<Generator>>
    GeneratorSupplier accessTreeFeatures(TreeType<Generator, GeneratorSupplier> treeType, String treeVariant) {
        return (GeneratorSupplier) FULL_TREES
                .getTypeMap()
                .get(treeType)
                .PRIMITIVE_TREE_CONFIGS
                .get(treeVariant)
                .TREE_FEATURES;
    }
}
