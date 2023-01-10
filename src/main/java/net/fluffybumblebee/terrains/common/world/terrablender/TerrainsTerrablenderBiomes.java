package net.fluffybumblebee.terrains.common.world.terrablender;

import com.mojang.datafixers.util.Pair;
import net.fluffybumblebee.terrains.common.world.WorldManager;
import net.fluffybumblebee.terrains.common.world.terrablender.biomes.MeadowFlats;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseHypercube;
import terrablender.api.*;

import java.util.function.Consumer;

public class TerrainsTerrablenderBiomes extends Region implements Runnable, TerraBlenderApi {

    public TerrainsTerrablenderBiomes() {
        super(TerrainsDefaults.getIdentifier("overworld"), RegionType.OVERWORLD, 1);
    }

    public static final RegistryKey<Biome> MEADOW_FLATS = add("meadow_flats", MeadowFlats.MEADOW_FLATS);

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<NoiseHypercube, RegistryKey<Biome>>> mapper) {
        super.addBiome(
                mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.COAST,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                MEADOW_FLATS
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static RegistryKey<Biome> add(String name, Biome biome) {
        RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrainsDefaults.NAMESPACE, name));
        BuiltinRegistries.add(BuiltinRegistries.BIOME, key, biome);
        return key;
    }

    @Override
    public void onTerraBlenderInitialized() {
        WorldManager.BIOME_INJECTOR.callbackWhenInitialized(this);
    }

    @Override
    public void run() {
        Regions.register(this);
    }
}
