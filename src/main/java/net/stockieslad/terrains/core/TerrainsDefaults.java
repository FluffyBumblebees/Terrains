package net.stockieslad.terrains.core;

import net.stockieslad.terrains.util.etc.ChainLogger;
import net.minecraft.util.Identifier;

public final class TerrainsDefaults {
    private TerrainsDefaults() {}
    static void init() {}


    public static final String NAMESPACE = "terrains";

    public static final ChainLogger CHAIN_LOGGER = new ChainLogger(NAMESPACE);

    public static String getNamespaceVar() {
        return NAMESPACE + ":";
    }

    public static Identifier getIdentifier(String name) {
        return new Identifier(NAMESPACE, name);
    }
}
