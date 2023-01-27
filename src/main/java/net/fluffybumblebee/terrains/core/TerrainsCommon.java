package net.fluffybumblebee.terrains.core;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.terrains.common.registry.RegistryManager;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;
import static org.slf4j.LoggerFactory.getLogger;

public class TerrainsCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        RegistryManager.init();
        getLogger(NAMESPACE).info("Initialised " + NAMESPACE + "!");
    }

}
