package net.stockieslad.terrains.common.world.inbuilt_biomes;

import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.stockieslad.abstractium.library.common.worldgen.biome.AbstractBiomeGenerationInfo;
import net.stockieslad.abstractium.library.common.worldgen.biome.AbstractBiomes;

import java.util.List;

import static net.stockieslad.terrains.common.world.inbuilt_biomes.TerrainsBiomes.*;
import static net.stockieslad.terrains.common.world.inbuilt_biomes.TerrainsBiomes.STAINED_FOREST_KEY;
import static net.stockieslad.terrains.core.TerrainsCommon.ABSTRACTION;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

public class TerrainsGeneration {
    public static void register() {
        ABSTRACTION.getBiomeGenerator().generateBiomes(
                List.of(
                        new AbstractBiomeGenerationInfo(
                                JACARANDA_FOREST_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(0.05F, 0.45F),
                                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                JACARANDA_PLAINS_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                                        MultiNoiseUtil.ParameterRange.of(-0.3F, -0.05F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                LUSH_MAPLE_BLOSSOM_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(0.05F, 0.45F),
                                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                MAPLE_BLOSSOM_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                                        MultiNoiseUtil.ParameterRange.of(-0.26666668F, -0.05F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                MAPLE_GHOST_FOREST_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.55F, 1.0F),
                                        MultiNoiseUtil.ParameterRange.of(-1.0F, -0.35F),
                                        MultiNoiseUtil.ParameterRange.of(0.3F, 1.0F),
                                        MultiNoiseUtil.ParameterRange.of(-1.0F, -0.78F),
                                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                MAPLE_MEADOW_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(0.3F, 1.0F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                                        MultiNoiseUtil.ParameterRange.of(-1.0F, -0.78F),
                                        MultiNoiseUtil.ParameterRange.of(-0.26666668F, -0.05F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                MAPLE_TUNDRA_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
                                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
                                        MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
                                        MultiNoiseUtil.ParameterRange.of(0.05F, 0.45F),
                                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                MEADOW_FLATS_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
                                        MultiNoiseUtil.ParameterRange.of(-1.0F, -0.78F),
                                        MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                PUMPKIN_RIDGES_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.55F, 1.0F),
                                        MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
                                        MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
                                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        ),
                        new AbstractBiomeGenerationInfo(
                                STAINED_FOREST_KEY,
                                new MultiNoiseUtil.NoiseHypercube(
                                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
                                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F),
                                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                                        MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
                                        MultiNoiseUtil.ParameterRange.of(0.0F),
                                        0L
                                )
                        )
                ),
                getIdentifier("overworld"),
                1,
                AbstractBiomes.OVERWORLD
        );
    }
}
