package net.fluffybumblebee.terrains.core;

import net.minecraft.util.Identifier;

public final class TerrainsDefaults {
    private TerrainsDefaults() {}

    public static final String NAMESPACE = "terrains";

    public static String getNamespaceVar() {
        return NAMESPACE + ":";
    }

    public static Identifier getIdentifier(String name) {
        return new Identifier(NAMESPACE, name);
    }
}
