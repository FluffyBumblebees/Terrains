package net.fluffybumblebee.terrains.client.render;

import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;

import static net.fluffybumblebee.terrains.util.registration.render.WoodSetRenderingUtil.registerWoodSetEntities;

public class RendererModels {
    public static void registerModels() {
        AllRegistrySets.FULL_TREES.getTypeMap().forEach((treeType, wholeTreeSet) ->
                registerWoodSetEntities(wholeTreeSet.WOOD_SET)
        );
    }
}
