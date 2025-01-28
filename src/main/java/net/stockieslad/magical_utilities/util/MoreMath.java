package net.stockieslad.magical_utilities.util;

public class MoreMath {
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int limit(int i, int size) {
        while(i >= size) i = i - size;
        return i;
    }
}
