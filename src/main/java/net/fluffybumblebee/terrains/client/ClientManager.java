package net.fluffybumblebee.terrains.client;

import net.fluffybumblebee.terrains.client.particles.ClientParticles;
import net.fluffybumblebee.terrains.client.render.RegisterRenderLayers;
import net.fluffybumblebee.terrains.client.render.RendererModels;

public class ClientManager {
    public static void register() {
        RegisterRenderLayers.registerCutouts();
        RegisterRenderLayers.registerTranslucents();
        RendererModels.registerModels();
        ClientParticles.init();
    }
}
