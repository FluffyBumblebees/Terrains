package net.fluffybumblebee.terrains.common.world.inbuilt_biomes;

import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component.MapleDryLands;
import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component.MapleBlossom;
import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component.PumpkinRidges;
import net.fluffybumblebee.terrains.common.world.inbuilt_biomes.component.MeadowFlats;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import static net.fluffybumblebee.terrains.util.registration.world.biome.BiomeRegistryTools.add;


public class BiomeRegistry {
    public static final RegistryKey<Biome> MEADOW_FLATS = add("meadow_flats", MeadowFlats.MEADOW_FLATS);
    public static final RegistryKey<Biome> MAPLE_BLOSSOM = add("maple_blossom", MapleBlossom.MAPLE_BLOSSOM);
    public static final RegistryKey<Biome> PUMPKIN_RIDGES = add("pumpkin_ridges", PumpkinRidges.PUMPKIN_RIDGES);
    public static final RegistryKey<Biome> MAPLE_DRY_LANDS = add("maple_drylands", MapleDryLands.MAPLE_DRY_LANDS);

    public static void register() {}
}
