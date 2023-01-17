package net.fluffybumblebee.terrains.common.registry.sets.tree.whole;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet.TreeType;

import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType.MAPLE_TREES;

public class TypesWholeTree {
    public static final List<TreeType<?, ?, ?>> ALL_WHOLE_TREES = List.of(
            MAPLE_TREES
    );
}


