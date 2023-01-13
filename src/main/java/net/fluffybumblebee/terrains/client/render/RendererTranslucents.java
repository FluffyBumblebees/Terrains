package net.fluffybumblebee.terrains.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.terrains.common.instances.block.crystals.CorundumCluster;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;

public class RendererTranslucents {
    public static void registerTranslucents() {
        //BlockRenderLayerMap.INSTANCE.putItem(MFRegistry.MAPLE_SAP, RenderLayer.getTranslucent());
        CRYSTAL_GEODES.forEach(element -> {
            Block block = element.BLOCK;
            if (!(block instanceof CorundumCluster)) {
                putBlock(block);
            }
        });
    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }
}

