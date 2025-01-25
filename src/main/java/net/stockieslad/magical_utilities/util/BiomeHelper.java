package net.stockieslad.magical_utilities.util;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;

import java.util.function.Predicate;

public class BiomeHelper {
    public static final Predicate<BiomeSelectionContext> IS_COLD = ctx -> ctx.getBiome().getTemperature() <= 0.15F;
}
