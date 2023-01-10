package net.fluffybumblebee.maple_forest.config;

import net.fabricmc.loader.api.FabricLoader;
import net.fluffybumblebee.maple_forest.init.MapleForest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class MFConfig {
    public static void loadClass() {}

    //Config Variables
    public static final boolean MAPLE_BLOSSOM;
    public static final boolean MAPLE_WOODLANDS;
    public static final boolean BARREN_MAPLE_WOODS;


    static {
        final Properties properties = new Properties();
        final Properties newProperties = new Properties();
        final Path path = FabricLoader.getInstance().getConfigDir().resolve( MapleForest.NAMESPACE + ".properties");

        if (Files.isRegularFile(path)) {
            try (InputStream in = Files.newInputStream(path, StandardOpenOption.CREATE)) {
                properties.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Don't forget that these variables are sorted alphabetically in .properties files!
        MAPLE_BLOSSOM = getBoolean(properties, newProperties, "generate_maple_blossom");
        MAPLE_WOODLANDS = getBoolean(properties, newProperties, "generate_maple_woodlands");
        BARREN_MAPLE_WOODS = getBoolean(properties, newProperties, "generate_barren_maple_woods");


        try (OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            newProperties.store(out, MapleForest.NAMESPACE.replace('_', ' ') + " config");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void logError(String key) {
        MapleForest.LOGGER.error("Failed to parse variable '" + key + "' in " + MapleForest.NAMESPACE + "'s config, generating a new one!");
    }
    private static boolean parseBoolean(String string) {
        if (string == null) throw new NumberFormatException("null");
        if (string.trim().equalsIgnoreCase("true")) return true;
        if (string.trim().equalsIgnoreCase("false")) return false;
        throw new NumberFormatException(string);
    }
    private static boolean getBoolean(Properties properties, Properties newProperties, String key) {
        try {
            final boolean b = parseBoolean(properties.getProperty(key));
            newProperties.setProperty(key, String.valueOf(b));
            return b;
        } catch (NumberFormatException e) {
            logError(key);
            newProperties.setProperty(key, String.valueOf(true));
            return true;
        }
    }
}