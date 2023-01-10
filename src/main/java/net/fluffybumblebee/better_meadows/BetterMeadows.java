package net.fluffybumblebee.better_meadows;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;

public class BetterMeadows implements ModInitializer {
    public static final String NAMESPACE = "better_meadows";
    private static Boolean INITIALIZED = false;
    private static final ArrayList<Runnable> RUNNABLES = new ArrayList<>(1);
    public static String getNamespaceAppendable() {
        return NAMESPACE + ":";
    }

    @Override
    public void onInitialize() {
        if (!FabricLoader.getInstance().isModLoaded("terrablender")) {
            return;
        }

        INITIALIZED = true;
        for (Runnable callback : RUNNABLES) {
            callback.run();
        }
    }

    public static void callbackWhenInitialized(Runnable callback) {
        if (INITIALIZED) {
            callback.run();
        } else {
            RUNNABLES.add(callback);
        }
    }
}

