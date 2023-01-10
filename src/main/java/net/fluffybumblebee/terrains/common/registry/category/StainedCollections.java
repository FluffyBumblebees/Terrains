package net.fluffybumblebee.terrains.common.registry.category;

import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.fluffybumblebee.terrains.util.registration.category.StainedCollection;

import java.util.*;

public final class StainedCollections {
    public static final Map<AllColours, StainedCollection<AllColours>> allStainedTrees = new HashMap<>();

    static {
        for (AllColours colour : AllColours.values()) {
            allStainedTrees.put(colour, new StainedCollection<>(colour));
        }
    }

    public static List<BlockBuilder<?>> getAllRegistryEntries() {
        List<BlockBuilder<?>> list = new ArrayList<>();
        for (AllColours colour : AllColours.values()) {
            list.addAll(Arrays.asList(allStainedTrees.get(colour).ALL_BLOCKS));
        }
        return list;
    }

    public static void register() {}
    private StainedCollections() {}

    public enum AllColours {
        BLACK,
        BLUE,
        BROWN,
        CYAN,
        GRAY,
        LIGHT_BLUE,
        LIGHT_GRAY,
        LIME,
        MAGENTA,
        ORANGE,
        PINK,
        PURPLE,
        RAINBOW,
        RED,
        WHITE,
        YELLOW

    }
}
