package net.fluffybumblebee.maple_forest.init;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fluffybumblebee.maple_forest.config.MFConfig;
import net.fluffybumblebee.maple_forest.world.placer.MFPlacerTypes;
import net.fluffybumblebee.maple_forest.entity.MFEntities;
import net.fluffybumblebee.maple_forest.item.MFItems;
import net.fluffybumblebee.maple_forest.item.group.MFItemGroup;
import net.fluffybumblebee.maple_forest.world.gen.MFWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MapleForest implements ModInitializer {
    public static final String NAMESPACE = "maple_forest";
    private static Boolean initialized = false;
    private static final ArrayList<Runnable> runnables = new ArrayList<>(1);
    public static final Logger LOGGER = LoggerFactory.getLogger("Fastload");
    @Override
    public void onInitialize() {
        MFConfig.loadClass();
        MFRegistry.addToRegistry();
        MFItems.addToRegistry();
        MFEntities.addToRegistry();
        MFItemGroup.initClass();
        MFWorldGen.generateWorldGen();
        MFBiomes.addToRegistry();
        MFPlacerTypes.addToRegistry();

        if (!FabricLoader.getInstance().isModLoaded("terrablender")) {
            return;
        }

        initialized = true;
        for (Runnable callback : runnables) {
            callback.run();
        }
    }

    public static void callbackWhenInitialized(Runnable callback) {
        if (initialized) {
            callback.run();
        } else {
            runnables.add(callback);
        }
    }
}

