package net.stockieslad.terrains.util.registration.etc;

public class ValueHelper {
    public static <T> T returnIf(boolean condition, T ifTrue, T ifFalse) {
        if (condition)
            return ifTrue;
        else return ifFalse;
    }
}
