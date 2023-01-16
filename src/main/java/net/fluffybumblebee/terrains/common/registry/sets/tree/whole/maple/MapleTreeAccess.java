package net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple;

import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WholeTreeSet;
import net.minecraft.block.BlockState;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.RegistrySetTypeTools.accessTreeFeatures;

public class MapleTreeAccess {
    public static final WholeTreeSet<?, ?> MAPLE_TREE = AllRegistrySets.FULL_TREES
            .getTypeMap().get(MapleTreeType.MAPLE_TREES);

    public static final DataPool<BlockState> MAPLE_LOGS = DataPool.<BlockState>builder()
            .add(MAPLE_TREE.WOOD_SET.LOG.BLOCK.getDefaultState(), 2)
            .add(MAPLE_TREE.ADDITIONAL_LOG_VARIANTS.get(0).BLOCK.getDefaultState(), 1)
            .build();

    public static MapleTreeType getFeature(String treeVariant) {
        return accessTreeFeatures(MapleTreeType.MAPLE_TREES, treeVariant);
    }

    public static RegistryEntry<PlacedFeature> getPlacedNoBees(MapleTreeType.MapleTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_NO_BEES;
    }

    public static RegistryEntry<PlacedFeature> getPlacedBees(MapleTreeType.MapleTypes type) {
        return getFeature(type.name().toLowerCase()).PLACED_TREE_BEES;
    }
}
