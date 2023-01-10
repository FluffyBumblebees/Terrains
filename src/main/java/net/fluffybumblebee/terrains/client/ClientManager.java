package net.fluffybumblebee.terrains.client;

import net.fluffybumblebee.terrains.client.render.RenderRegistry;

public class ClientManager {
   public static void register() {
       RenderRegistry.registerRendererRegistries();
   }
}
