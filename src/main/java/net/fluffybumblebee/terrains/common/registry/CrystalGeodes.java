package net.fluffybumblebee.terrains.common.registry;

import net.fluffybumblebee.terrains.util.registration.GeodeCollection;
import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.item.Item;
import net.minecraft.world.biome.GenerationSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CrystalGeodes {
    private CrystalGeodes() {}

    public static final String[] allGeodeColours = {
            "black",
            "blue",
            "brown",
            "cyan",
            "dark",
            "green",
            "indigo",
            "orange",
            "pink",
            "purple",
            "rainbow",
            "red",
            "white",
            "yellow"
    };

    public static final Map<String, GeodeCollection> allGeodes = new HashMap<>();

    static {
        for (String colour : allGeodeColours) {
            allGeodes.put(colour, new GeodeCollection(colour));
        }
    }

    public static void generateGeodes(GenerationSettings.Builder builder) {
        for (String colour : allGeodeColours) {
            allGeodes.get(colour).register(builder);
        }
    }

    public static List<Item> getAllItems() {
        List<Item> list = new ArrayList<>();
        for (String colour : allGeodeColours) {
            for (BlockBuilder<?> builder : allGeodes.get(colour).ALL_BLOCKS) {
                list.add(builder.getBlockItem().asItem());
            }
        }
        return list;
    }

    public static void register() {}


}
