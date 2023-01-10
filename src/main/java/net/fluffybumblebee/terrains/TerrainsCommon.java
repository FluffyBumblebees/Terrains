package net.fluffybumblebee.terrains;

import net.fabricmc.api.ModInitializer;

import static net.fluffybumblebee.terrains.common.registry.RegistryManager.*;

public class TerrainsCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        registerBlocks();
        registerItems();
        registerItemGroups();
        registerWorldGen();
    }
}
