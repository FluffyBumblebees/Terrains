package net.stockieslad.terrains.util.registration.render;

import net.feltmc.abstractium.init.AbstractiumClient;
import net.feltmc.abstractium.library.client.render.AbstractRenderCalls;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;

import java.util.function.Consumer;

import static net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes.*;

public class BlockRenderLayerMapUtil {
    private static final AbstractRenderCalls RENDER_CALLS = AbstractiumClient.CLIENT_ABSTRACTION_HANDLER.abstraction.getRenderCalls();
    private static void basicIteration(
            final RegistrySet<?, ?>[] sets,
            final RenderTypes toCompare,
            final RegistryTypes[] all,
            final Consumer<Item> itemConsumer,
            final Consumer<Block> blockConsumer
    ) {
        for (RegistrySet<?, ?> set : sets) {
            set.iterateRenderType().forEach(element -> {
                if (element == toCompare)
                    for (RegistryTypes type : all) {
                        set.iterateRegistry(type).forEach(nextElement -> {
                            if (nextElement.block().isPresent())
                                blockConsumer.accept(nextElement.block().orElseThrow());
                            if (nextElement.item().isPresent())
                                itemConsumer.accept(nextElement.item().orElseThrow());
                        });
                    }
            });
        }
    }

    public static void standardCutouts(RegistrySet<?, ?>... sets) {
        basicIteration(
                sets,
                RenderTypes.CUTOUT,
                new RegistryTypes[]{
                        WOOD_WITH_CUTOUT,
                        LEAVES,
                        SAPLING,
                        POTTED_BLOCK,
                        CLUSTER,
                        FLOWER
                },
                RENDER_CALLS::registerCutout,
                RENDER_CALLS::registerCutout
        );
    }

    public static void standardTranslucents(RegistrySet<?, ?>[] sets) {
        basicIteration(
                sets,
                RenderTypes.TRANSLUCENT,
                new RegistryTypes[]{
                        TRANSPARENT_FOOD,
                        TRANSPARENT_FULL_BLOCK
                },
                RENDER_CALLS::registerTranslucent,
                RENDER_CALLS::registerTranslucent
        );
    }
}
