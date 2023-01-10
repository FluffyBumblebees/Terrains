package net.fluffybumblebee.maple_forest.world.gen;

import com.mojang.datafixers.util.Pair;
import net.fluffybumblebee.maple_forest.config.MFConfig;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

import static net.fluffybumblebee.maple_forest.init.MFBiomes.*;

public class MFBiomeGeneration extends Region implements Runnable, TerraBlenderApi {

    public MFBiomeGeneration() {
        super(new Identifier(MapleForest.NAMESPACE, "overworld"), RegionType.OVERWORLD, 5);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        if (MFConfig.MAPLE_BLOSSOM) {
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
        }

        if (MFConfig.MAPLE_WOODLANDS) {
            this.addBiome(
                    mapper,
                    ParameterUtils.Temperature.WARM,
                    ParameterUtils.Humidity.DRY,
                    ParameterUtils.Continentalness.MID_INLAND,
                    ParameterUtils.Erosion.FULL_RANGE,
                    ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING,
                    ParameterUtils.Depth.SURFACE,
                    0L,
                    MAPLE_WOODLANDS
            );
        }

        if (MFConfig.BARREN_MAPLE_WOODS) {
            this.addBiome(
                    mapper,
                    ParameterUtils.Temperature.HOT,
                    ParameterUtils.Humidity.ARID,
                    ParameterUtils.Continentalness.FAR_INLAND,
                    ParameterUtils.Erosion.EROSION_0,
                    ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING,
                    ParameterUtils.Depth.SURFACE,
                    0L,
                    BARREN_MAPLE_WOODS
            );
        }
    }

    @Override
    public void onTerraBlenderInitialized() {
        // We can't do registration stuff until both Maple Forest and TerraBlender are ready.
        // The run() method below will be called when Maple Forest is done initializing.
        MapleForest.callbackWhenInitialized(this);
    }

    // Initialize TerraBlender as our biome placement provider.
    @Override
    public void run() {
        // Add the biomes to Overworld generation via TerraBlender.
        Regions.register(this);
    }
}
