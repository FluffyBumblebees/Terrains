package net.stockieslad.terrains.common.registry.sets.tree.whole;

import net.stockieslad.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;

import java.util.List;

import static net.stockieslad.terrains.common.registry.sets.tree.whole.jacaranda.JacarandaTreeType.JACARANDA_TREES;
import static net.stockieslad.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MAPLE_TREES;

public class TypesWholeTree {
    public static final List<TreeType<?, ?>> ALL_WHOLE_TREES = List.of(
            MAPLE_TREES,
            JACARANDA_TREES
    );
}


