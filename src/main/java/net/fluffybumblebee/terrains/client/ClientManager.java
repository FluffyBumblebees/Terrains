package net.fluffybumblebee.terrains.client;

import net.fluffybumblebee.terrains.client.render.RendererCutouts;
import net.fluffybumblebee.terrains.client.render.RendererModels;
import net.fluffybumblebee.terrains.client.render.RendererTranslucents;

public class ClientManager {
    public static void register() {
        RendererCutouts.registerCutouts();
        RendererModels.registerModels();
        RendererTranslucents.registerTranslucents();
    }
}
