package net.fluffybumblebee.terrains.core;

import net.fabricmc.api.ClientModInitializer;
import net.fluffybumblebee.terrains.client.ClientManager;

public class TerrainsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientManager.register();
    }
}
