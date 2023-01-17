package net.fluffybumblebee.terrains.common.world.terrablender;

import com.mojang.datafixers.util.Pair;
import net.fluffybumblebee.terrains.common.world.WorldManager;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseHypercube;
import terrablender.api.*;

import java.util.function.Consumer;

import static net.fluffybumblebee.terrains.common.world.inbuilt_biomes.TerrainsBiomeRegistry.*;

public class TerrainsTerrablenderBiomes extends Region implements Runnable, TerraBlenderApi {

    public TerrainsTerrablenderBiomes() {
        super(TerrainsDefaults.getIdentifier("overworld"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiome(
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

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.COAST,
                ParameterUtils.Erosion.EROSION_6,
                ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                MAPLE_BLOSSOM
        );

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.NEAR_INLAND,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                MAPLE_MEADOW
        );

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.DRY,
                ParameterUtils.Continentalness.MID_INLAND,
                ParameterUtils.Erosion.FULL_RANGE,
                ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                PUMPKIN_RIDGES
        );

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.HOT,
                ParameterUtils.Humidity.ARID,
                ParameterUtils.Continentalness.FAR_INLAND,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                MAPLE_GHOST_FOREST
        );

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.COOL,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.MID_INLAND,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                MAPLE_TUNDRA
        );

        this.addBiome(
                mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.COAST,
                ParameterUtils.Erosion.EROSION_6,
                ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0L,
                STAINED_FOREST
        );
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
