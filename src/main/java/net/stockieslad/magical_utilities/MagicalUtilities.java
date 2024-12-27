package net.stockieslad.magical_utilities;

import net.stockieslad.magical_utilities.util.ChainLogger;
import net.minecraft.util.Identifier;

public class MagicalUtilities {
    public static final String NAMESPACE = "magical_utilities";

    public static final ChainLogger CHAIN_LOGGER = new ChainLogger(NAMESPACE);

    public static String getNamespaceVar() {
        return NAMESPACE + ":";
    }

    public static Identifier getIdentifier(String name) {
        return new Identifier(NAMESPACE, name);
    }
}
