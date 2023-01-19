package net.fluffybumblebee.terrains.util.janky_random;

import net.minecraft.util.math.random.Random;

public record MinecraftRandomUtil(Random random) {


    /**
     * Simply gets a random int and divides it to get a random float.
     * e.g (nextFloat(0.35)) -> (randomFloat(random, 25, 100))
     * @param bound The bound.
     * @param divisor The number it divides by.
     * @return a random float.
     */
    public float randomFloat(final int bound, final int divisor) {
        if (bound >= divisor)
            throw new NumberFormatException();
        return (float) random.nextInt(bound) / divisor;
    }

    public float randomFloatInHundredths(final int bound) {
        return randomFloat(bound, 100);
    }
}
