package net.stockieslad.magical_utilities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.stockieslad.magical_utilities.common.Clouds;

import java.util.Arrays;

public class MagicalUtilitiesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), Arrays.stream(Clouds.values()).map(e -> e.block).toArray((Block[]::new)));
    }
}
