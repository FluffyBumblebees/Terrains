package net.fluffybumblebee.terrains.common.registry.category;

import net.fluffybumblebee.terrains.util.registration.category.GeodeCollection;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.world.biome.GenerationSettings;

import java.util.*;

public final class GeodeCollections {

    public static final Map<AllColours, GeodeCollection<AllColours>> allGeodes = new HashMap<>();

    static {
        for (AllColours colour : AllColours.values()) {
            allGeodes.put(colour, new GeodeCollection<>(colour));
        }
    }

    public static void generateGeodes(GenerationSettings.Builder builder) {
        for (AllColours colour : AllColours.values()) {
            allGeodes.get(colour).register(builder);
        }
    }

    public static List<BlockBuilder<?>> getAllRegistryEntries() {
        List<BlockBuilder<?>> list = new ArrayList<>();
        for (AllColours colour : AllColours.values()) {
            list.addAll(Arrays.asList(allGeodes.get(colour).ALL_BLOCKS));
        }
        return list;
    }

    public static void register() {}
    private GeodeCollections() {}

    public enum AllColours {
        BLACK,
        BLUE,
        BROWN,
        CYAN,
        DARK,
        GREEN,
        INDIGO,
        ORANGE,
        PINK,
        PURPLE,
        RAINBOW,
        RED,
        WHITE,
        YELLOW
    }
}
