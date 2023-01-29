package net.fluffybumblebee.terrains.util.janky_random;

import net.minecraft.util.math.random.Random;

public record MinecraftRandomUtil(Random random) {


    /**
     * Simply gets a random int and divides it to get a random float.
     * e.g (nextFloat(0.35)) -> (nextFloat(random, 25, 100))
     * @param bound The bound.
     * @param divisor The number it divides by.
     * @return a random float.
     */
    public float nextFloat(final int bound, final int divisor) {
        return (float) random.nextInt(bound) / divisor;
    }

    public float nextFloat(final int bound) {
        return nextFloat(bound, 100);
    }
}
