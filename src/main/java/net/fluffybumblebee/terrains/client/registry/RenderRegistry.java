package net.fluffybumblebee.terrains.client.registry;

import net.fluffybumblebee.terrains.client.render.RendererCutouts;
import net.fluffybumblebee.terrains.client.render.RendererModels;
import net.fluffybumblebee.terrains.client.render.RendererTranslucents;

public class RenderRegistry {
    public static void registerRendererRegistries() {
        RendererCutouts.registerCutouts();
        RendererModels.registerModels();
        RendererTranslucents.registerTranslucents();
    }
}
