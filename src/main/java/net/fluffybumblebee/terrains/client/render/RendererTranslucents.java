package net.fluffybumblebee.terrains.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.terrains.common.instances.block.crystals.CorundumCluster;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;

public class RendererTranslucents {
    public static void registerTranslucents() {
        //BlockRenderLayerMap.INSTANCE.putItem(MFRegistry.MAPLE_SAP, RenderLayer.getTranslucent());
        CRYSTAL_GEODES.iterateRegistry(RegistryTypes.CRYSTAL).forEach(element -> {
            if (element.block().isPresent()) {
                final var block = element.block().get();
                if (!(block instanceof CorundumCluster)) {
                    putBlock(block);
                }
            }
        });
    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }
}

