package net.fluffybumblebee.better_meadows.world.gen;

import com.mojang.datafixers.util.Pair;
import net.fluffybumblebee.better_meadows.BetterMeadows;
import net.fluffybumblebee.better_meadows.world.biome.BMMeadowFlats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

public class BMBiomeGeneration extends Region implements Runnable, TerraBlenderApi {

    public BMBiomeGeneration() {
        super(new Identifier(BetterMeadows.NAMESPACE, "overworld"), RegionType.OVERWORLD, 1);
    }

    public static final RegistryKey<Biome> MEADOW_FLATS = add("meadow_flats", BMMeadowFlats.MEADOW_FLATS);

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
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
        RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, new Identifier(BetterMeadows.NAMESPACE, name));
        BuiltinRegistries.add(BuiltinRegistries.BIOME, key, biome);
        return key;
    }

    @Override
    public void onTerraBlenderInitialized() {
        // We can't do registration stuff until both Maple Forest and TerraBlender are ready.
        // The run() method below will be called when Maple Forest is done initializing.
        BetterMeadows.callbackWhenInitialized(this);
    }

    @Override
    public void run() {
        // Add the biomes to Overworld generation via TerraBlender.
        Regions.register(this);
    }
}
