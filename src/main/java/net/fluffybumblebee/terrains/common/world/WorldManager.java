package net.fluffybumblebee.terrains.common.world;

import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.BiomeRegistry;
import net.fluffybumblebee.terrains.common.world.inbuilt_features.raw.component.Placers;
import net.fluffybumblebee.terrains.common.world.terrablender.TerrablenderManager;

public class WorldManager {
    public static final TerrablenderManager BIOME_INJECTOR = new TerrablenderManager();
    public static void init() {
        Placers.register();
        BiomeRegistry.register();
        BIOME_INJECTOR.init();
    }
}
