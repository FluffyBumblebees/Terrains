package net.fluffybumblebee.terrains.client.render;

public class RenderRegistry {
    public static void registerRendererRegistries() {
        RendererCutouts.registerCutouts();
        RendererModels.registerModels();
        RendererTranslucents.registerTranslucents();
    }
}
