package net.stockieslad.magical_utilities;

import net.stockieslad.magical_utilities.util.ChainLogger;
import net.minecraft.util.Identifier;

public class MagicalUtilities {
    public static final String NAMESPACE = "magical_utilities";

    public static final ChainLogger LOGGER = new ChainLogger(NAMESPACE);

    public static Identifier getIdentifier(String name) {
        return new Identifier(NAMESPACE, name);
    }
}
