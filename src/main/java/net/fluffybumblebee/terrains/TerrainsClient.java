package net.fluffybumblebee.terrains;

import net.fabricmc.api.ClientModInitializer;
import net.fluffybumblebee.terrains.client.render.RendererCutouts;
import net.fluffybumblebee.terrains.client.render.RendererModels;
import net.fluffybumblebee.terrains.client.render.RendererTranslucents;

public class TerrainsClient implements ClientModInitializer {
    public static void registerRendererRegistries() {
        RendererCutouts.registerCutouts();
        RendererModels.registerModels();
        RendererTranslucents.registerTranslucents();
    }

    @Override
    public void onInitializeClient() {
        registerRendererRegistries();
    }
}
