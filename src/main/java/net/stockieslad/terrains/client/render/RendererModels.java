package net.stockieslad.terrains.client.render;

import net.stockieslad.terrains.common.registry.sets.RegistrySets;

import static net.stockieslad.terrains.util.registration.render.WoodSetRenderingUtil.registerWoodSetEntities;

public class RendererModels {
    public static void registerModels() {
        RegistrySets.FULL_TREES.getTypeMap().forEach((treeType, wholeTreeSet) ->
                registerWoodSetEntities(wholeTreeSet.WOOD_SET)
        );
    }
}
