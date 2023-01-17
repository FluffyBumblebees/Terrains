package net.fluffybumblebee.terrains.common.world;

import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.raw.component.Placers;
import net.fluffybumblebee.terrains.common.world.terrablender.TerrablenderManager;

public class WorldManager {
    public static final TerrablenderManager BIOME_INJECTOR = new TerrablenderManager();
    public static void init() {
        Placers.register();
        TerrainsBiomeRegistry.register();
        BIOME_INJECTOR.init();
    }
}
