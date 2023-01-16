package net.fluffybumblebee.terrains.client.render;

import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.registry.sets.tree.whole.maple.MapleTreeType;

import static net.fluffybumblebee.terrains.util.registration.render.WoodSetRenderingUtil.registerWoodSetEntities;

public class RendererModels {
    public static void registerModels() {
        registerWoodSetEntities(AllRegistrySets.FULL_TREES.getTypeMap().get(MapleTreeType.MAPLE_TREES).WOOD_SET);
    }
}
