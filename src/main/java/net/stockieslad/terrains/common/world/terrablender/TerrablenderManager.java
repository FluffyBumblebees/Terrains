package net.stockieslad.terrains.common.world.terrablender;

import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;

public class TerrablenderManager {
    private Boolean INITIALIZED = false;
    private final ArrayList<Runnable> RUNNABLES = new ArrayList<>(1);
    public void init() {
        if (FabricLoader.getInstance().isModLoaded("terrablender")) {
            INITIALIZED = true;
            for (Runnable callback : RUNNABLES) {
                callback.run();
            }
        }
    }

    public void callbackWhenInitialized(Runnable callback) {
        if (INITIALIZED) {
            callback.run();
        } else {
            RUNNABLES.add(callback);
        }
    }
}
