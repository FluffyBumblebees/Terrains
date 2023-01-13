package net.fluffybumblebee.terrains.util.registration.feature_set;

public class EasyIf {
    public static void onIf(boolean condition, Runnable runnable) {
        if (condition)
            runnable.run();
    }
}
