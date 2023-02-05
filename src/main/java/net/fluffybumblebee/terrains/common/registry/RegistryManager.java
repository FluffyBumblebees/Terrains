package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.common.registry.itemgroups.*;
import net.fluffybumblebee.terrains.common.registry.sets.RegistrySetManager;
import net.fluffybumblebee.terrains.common.world.WorldManager;

public class RegistryManager {
    private RegistryManager(){}

    public static void init() {
        RegistrySetManager.init();
        ItemGroupManager.init();
        WorldManager.init();
    }
}
