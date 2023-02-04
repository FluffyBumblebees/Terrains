package net.fluffybumblebee.terrains.util.registration.render;

import net.fluffybumblebee.terrains.client.render.RenderTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;

import static net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public class BlockRenderLayerMapUtil {
    private static void basicIteration(
            final RegistrySet<?, ?>[] sets, final RenderTypes toCompare, final RegistryTypes[] all, final LambdaPlacement place
    ) {
        for (RegistrySet<?, ?> set : sets) {
            set.iterateRenderType().forEach(element -> {
                if (element == toCompare)
                    for (RegistryTypes type : all) {
                        set.iterateRegistry(type).forEach(nextElement -> {
                            if (nextElement.block().isPresent())
                                place.block(nextElement.block().orElseThrow());
                            if (nextElement.item().isPresent())
                                place.item(nextElement.item().orElseThrow());
                        });
                    }
            });
        }
    }

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
        basicIteration(sets, RenderTypes.CUTOUT, CUTOUT_TYPES, new LambdaPlacement() {
            @Override
            public void item(Item element) {
                cutout(element);
            }

            @Override
            public void block(Block element) {
                cutout(element);
            }
        });
    }

    private static final RegistryTypes[] TRANSLUCENT_TYPES = {
            TRANSPARENT_FOOD,
            TRANSPARENT_FULL_BLOCK
    };

    public static void translucent(final Block block) {
        INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }

    public static void translucent(final Item item) {
        INSTANCE.putItem(item, RenderLayer.getTranslucent());
    }

    public static void standardTranslucents(RegistrySet<?, ?>[] sets) {
        basicIteration(sets, RenderTypes.TRANSLUCENT, TRANSLUCENT_TYPES, new LambdaPlacement() {
            @Override
            public void item(Item element) {
                translucent(element);
            }

            @Override
            public void block(Block element) {
                translucent(element);
            }
        });
    }

    private interface LambdaPlacement {
        void item(Item element);
        void block(Block element);
    }
}
