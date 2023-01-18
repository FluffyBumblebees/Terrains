package net.fluffybumblebee.terrains.core;

import net.fabricmc.api.ModInitializer;
import net.fluffybumblebee.terrains.common.registry.RegistryManager;
import org.slf4j.LoggerFactory;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.NAMESPACE;

public class TerrainsCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        RegistryManager.init();
        LoggerFactory.getLogger(NAMESPACE).info(
                "Initialised " + NAMESPACE + "!"
        );
    }
}
