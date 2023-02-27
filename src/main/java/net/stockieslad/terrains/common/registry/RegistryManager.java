package net.stockieslad.terrains.common.registry;

import net.stockieslad.terrains.common.registry.itemgroups.*;
import net.stockieslad.terrains.common.registry.sets.RegistrySetManager;
import net.stockieslad.terrains.common.world.WorldManager;

public final class RegistryManager {
    private RegistryManager(){}

    public static void init() {
        RegistrySetManager.init();
        ItemGroupManager.init();
        WorldManager.init();
    }
}
