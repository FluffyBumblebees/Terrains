package net.stockieslad.terrains.core;

import net.fabricmc.api.ClientModInitializer;
import net.stockieslad.terrains.client.ClientManager;

public class TerrainsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientManager.register();
    }
}
