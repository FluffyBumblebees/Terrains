package net.fluffybumblebee.terrains.client.render;


import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.terrains.common.instances.block.crystals.CorundumCluster;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.EasyIf.onIf;

public class RendererCutouts {
    public static void registerCutouts() {
        FULL_TREES.forEach(element -> onIf(element.BLOCK instanceof LeavesBlock || element.BLOCK instanceof DoorBlock
                || element.BLOCK instanceof TrapdoorBlock || element.BLOCK instanceof SaplingBlock,
                () -> putBlock(element.BLOCK))
        );

        CRYSTAL_GEODES.forEach(element ->
                onIf(element.BLOCK instanceof CorundumCluster, () -> putBlock(element.BLOCK))
        );

        STAINED_TREES.forEach(element -> putBlock(element.BLOCK));

    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
