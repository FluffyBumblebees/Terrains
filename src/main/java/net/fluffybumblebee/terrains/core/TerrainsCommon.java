package net.fluffybumblebee.terrains.core;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.terrains.common.registry.RegistryManager;
import net.fluffybumblebee.terrains.common.world.WorldManager;

public class TerrainsCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        RegistryManager.init();
        WorldManager.init();
    }
}
