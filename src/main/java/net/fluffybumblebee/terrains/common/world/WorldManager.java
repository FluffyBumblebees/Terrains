package net.fluffybumblebee.terrains.common.world;

import net.fluffybumblebee.terrains.common.world.terrablender.TerrablenderManager;

public class WorldManager {
    public static final TerrablenderManager BIOME_INJECTOR = new TerrablenderManager();
    public static void init() {
        BIOME_INJECTOR.init();
    }
}
