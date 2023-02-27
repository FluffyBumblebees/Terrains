package net.stockieslad.terrains.common.world;

import net.stockieslad.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.component.Placers;
import net.stockieslad.terrains.common.world.terrablender.TerrablenderManager;

public final class WorldManager {
    public static final TerrablenderManager BIOME_INJECTOR = new TerrablenderManager();

    public static void init() {
        Placers.register();
        TerrainsBiomeRegistry.register();
        BIOME_INJECTOR.init();
    }

    private WorldManager() {}
}
