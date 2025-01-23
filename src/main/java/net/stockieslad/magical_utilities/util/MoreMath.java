package net.stockieslad.magical_utilities.util;

public class MoreMath {
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
