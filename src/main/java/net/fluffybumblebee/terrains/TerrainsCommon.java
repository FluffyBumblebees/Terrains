package net.fluffybumblebee.terrains;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.terrains.common.registry.RegistryManager;

public class TerrainsCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        RegistryManager.init();
    }
}
