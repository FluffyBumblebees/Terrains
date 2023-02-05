package net.fluffybumblebee.terrains.core;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.terrains.common.registry.RegistryManager;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.CHAIN_LOGGER;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class TerrainsCommon implements ModInitializer {
    @Override
    public void onInitialize() {
        TerrainsDefaults.init();
        RegistryManager.init();
        CHAIN_LOGGER.info("Initialised " + NAMESPACE + "!");
    }
}
