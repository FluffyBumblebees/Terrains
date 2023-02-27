package net.stockieslad.terrains.client;

import net.stockieslad.terrains.client.render.RegisterRenderLayers;
import net.stockieslad.terrains.client.render.RendererModels;

public class ClientManager {
    public static void register() {
        RegisterRenderLayers.registerCutouts();
        RegisterRenderLayers.registerTranslucents();
        RendererModels.registerModels();
    }
}
