package net.fluffybumblebee.terrains.common.world.inbuilt_biomes;

import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component.*;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.add;


public class TerrainsBiomeRegistry {
    public static final int HOT_WATER = 0x6E9A98;
    public static final int WARM_WATER = 0x0089F5;
    public static final int COLD_WATER = 0x002D96;

    public static final RegistryKey<Biome> JACARANDA_PLAINS = add("jacaranda_plains", JacarandaPlains.JACARANDA_PLAINS);
    public static final RegistryKey<Biome> LUSH_MAPLE_BLOSSOM = add("lush_maple_blossom",
            LushMapleBlossom.LUSH_MAPLE_BLOSSOM);
    public static final RegistryKey<Biome> MAPLE_BLOSSOM = add("maple_blossom", MapleBlossom.MAPLE_BLOSSOM);
    public static final RegistryKey<Biome> MAPLE_GHOST_FOREST = add("maple_ghost_forest",
            MapleGhostForest.MAPLE_GHOST_FOREST);
    public static final RegistryKey<Biome> MAPLE_MEADOW = add("maple_meadow", MapleMeadow.MAPLE_MEADOW);
    public static final RegistryKey<Biome> MAPLE_TUNDRA = add("maple_tundra", MapleTundra.MAPLE_TUNDRA);
    public static final RegistryKey<Biome> MEADOW_FLATS = add("meadow_flats", MeadowFlats.MEADOW_FLATS);
    public static final RegistryKey<Biome> PUMPKIN_RIDGES = add("pumpkin_ridges", PumpkinRidges.PUMPKIN_RIDGES);
    public static final RegistryKey<Biome> STAINED_FOREST = add("stained_forest", StainedForest.STAINED_FOREST);


    public static void register() {}
}
