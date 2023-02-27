package net.stockieslad.terrains.util.registration.registry_set.helper;

public class EasyIf {
    public static void onIf(boolean condition, Runnable runnable) {
        if (condition)
            runnable.run();
    }
}
