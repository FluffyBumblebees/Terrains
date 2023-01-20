package net.fluffybumblebee.terrains.client.render;


import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fluffybumblebee.terrains.common.instances.block.crystals.CorundumCluster;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.render.RenderLayer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;

public class RendererCutouts {
    public static void registerCutouts() {
        FULL_TREES.iterateRegistry(RegistryTypes.WOOD).forEach(element -> {
            if (element.block().isPresent()) {
                var block = element.block().get();
                if (block instanceof TrapdoorBlock || block instanceof DoorBlock)
                    putBlock(element.block().orElseThrow());
            }
        });

        FULL_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> putBlock(element.block().orElseThrow()));
        FULL_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> putBlock(element.block().orElseThrow()));
        CRYSTAL_GEODES.iterateRegistry(RegistryTypes.CRYSTAL).forEach(element -> {
            if (element.block().isPresent()) {
                var block = element.block().get();
                if (block instanceof CorundumCluster)
                    putBlock(block);
            }
        });

        STAINED_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element -> putBlock(element.block().orElseThrow()));
        STAINED_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element -> putBlock(element.block().orElseThrow()));
        FOLIAGE.iterateRegistry(RegistryTypes.FLOWER).forEach(element -> putBlock(element.block().orElseThrow()));

    }

    private static void putBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
