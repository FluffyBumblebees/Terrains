package net.fluffybumblebee.terrains.util.registration.render;

import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;

import static net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public class BlockRenderLayerMapUtil {
    public static final RegistryTypes[] CUTOUT_TYPES = {
            WOOD_WITH_CUTOUT,
            LEAVES,
            SAPLING,
            POTTED_BLOCK,
            CLUSTER,
            FLOWER
    };

    public static void cutout(final Block block) {
        INSTANCE.putBlock(block, RenderLayer.getCutout());
    }

    public static void cutout(final Item item) {
        INSTANCE.putItem(item, RenderLayer.getCutout());
    }

    public static void standardCutouts(RegistrySet<?, ?>... sets) {
        for (RegistrySet<?, ?> set : sets) {
            for (RegistryTypes type : CUTOUT_TYPES) {
                set.iterateRegistry(type).forEach(element -> {
                    if (element.block().isPresent())
                        cutout(element.block().orElseThrow());
                    if (element.item().isPresent())
                        cutout(element.item().orElseThrow());
                });
            }
        }
    }

    public static final RegistryTypes[] TRANSLUCENT_TYPES = {
            TRANSPARENT_FOOD,
            TRANSPARENT_FULL_BLOCK
    };

    public static void translucent(final Block block) {
        INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }

    public static void translucent(final Item item) {
        INSTANCE.putItem(item, RenderLayer.getTranslucent());
    }

    public static void standardTranslucents(RegistrySet<?, ?>... sets) {
        for (RegistrySet<?, ?> set : sets) {
            for (RegistryTypes type : TRANSLUCENT_TYPES) {
                set.iterateRegistry(type).forEach(element -> {
                    if (element.block().isPresent())
                        translucent(element.block().orElseThrow());

                    if (element.item().isPresent())
                        translucent(element.item().orElseThrow());
                });
            }
        }
    }
}
