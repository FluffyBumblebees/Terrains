package net.stockieslad.magical_utilities.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ChunkSectionCache;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.BitSet;
import java.util.Objects;

import static net.minecraft.state.property.Properties.FACING;

public class CloudFeature extends OreFeature {
    public CloudFeature(Codec<OreFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<OreFeatureConfig> context) {
        Random random = context.getRandom();
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        OreFeatureConfig oreFeatureConfig = context.getConfig();
        float f = random.nextFloat() * 3.1415927F;
        float g = (float)oreFeatureConfig.size / 8.0F;
        int i = MathHelper.ceil(((float)oreFeatureConfig.size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double d = (double)blockPos.getX() + Math.sin(f) * (double)g;
        double e = (double)blockPos.getX() - Math.sin(f) * (double)g;
        double h = (double)blockPos.getZ() + Math.cos(f) * (double)g;
        double j = (double)blockPos.getZ() - Math.cos(f) * (double)g;
        double l = blockPos.getY() + random.nextInt(3) - 2;
        double m = blockPos.getY() + random.nextInt(3) - 2;
        int n = blockPos.getX() - MathHelper.ceil(g) - i;
        int o = blockPos.getY() - 2 - i;
        int p = blockPos.getZ() - MathHelper.ceil(g) - i;
        int q = 2 * (MathHelper.ceil(g) + i);
        int r = 2 * (2 + i);

        return this.generateVeinPart(structureWorldAccess, random, oreFeatureConfig, d, e, h, j, l, m, n, o, p, q, r);
    }

    @Override
    protected boolean generateVeinPart(StructureWorldAccess world, Random random, OreFeatureConfig config, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int horizontalSize, int verticalSize) {
        int i = 0;
        BitSet bitSet = new BitSet(horizontalSize * verticalSize * horizontalSize);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int j = config.size;
        double[] ds = new double[j * 4];

        int k;
        double d;
        double e;
        double g;
        double h;
        for(k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            d = MathHelper.lerp(f, startX, endX);
            e = MathHelper.lerp(f, startY, endY);
            g = MathHelper.lerp(f, startZ, endZ);
            h = random.nextDouble() * (double)j / 16.0;
            double l = ((double)(MathHelper.sin(3.1415927F * f) + 1.0F) * h + 1.0) / 2.0;
            ds[k * 4] = d;
            ds[k * 4 + 1] = e;
            ds[k * 4 + 2] = g;
            ds[k * 4 + 3] = l;
        }

        int m;
        for(k = 0; k < j - 1; ++k) {
            if (!(ds[k * 4 + 3] <= 0.0)) {
                for(m = k + 1; m < j; ++m) {
                    if (!(ds[m * 4 + 3] <= 0.0)) {
                        d = ds[k * 4] - ds[m * 4];
                        e = ds[k * 4 + 1] - ds[m * 4 + 1];
                        g = ds[k * 4 + 2] - ds[m * 4 + 2];
                        h = ds[k * 4 + 3] - ds[m * 4 + 3];
                        if (h * h > d * d + e * e + g * g) {
                            if (h > 0.0) {
                                ds[m * 4 + 3] = -1.0;
                            } else {
                                ds[k * 4 + 3] = -1.0;
                            }
                        }
                    }
                }
            }
        }

        ChunkSectionCache chunkSectionCache = new ChunkSectionCache(world);

        try {
            for(m = 0; m < j; ++m) {
                d = ds[m * 4 + 3];
                if (!(d < 0.0)) {
                    e = ds[m * 4];
                    g = ds[m * 4 + 1];
                    h = ds[m * 4 + 2];
                    int n = Math.max(MathHelper.floor(e - d), x);
                    int o = Math.max(MathHelper.floor(g - d), y);
                    int p = Math.max(MathHelper.floor(h - d), z);
                    int q = Math.max(MathHelper.floor(e + d), n);
                    int r = Math.max(MathHelper.floor(g + d), o);
                    int s = Math.max(MathHelper.floor(h + d), p);

                    for(int t = n; t <= q; ++t) {
                        double u = ((double)t + 0.5 - e) / d;
                        if (u * u < 1.0) {
                            for(int v = o; v <= r; ++v) {
                                double w = ((double)v + 0.5 - g) / d;
                                if (u * u + w * w < 1.0) {
                                    for(int aa = p; aa <= s; ++aa) {
                                        double ab = ((double)aa + 0.5 - h) / d;
                                        if (u * u + w * w + ab * ab < 1.0 && !world.isOutOfHeightLimit(v)) {
                                            int ac = t - x + (v - y) * horizontalSize + (aa - z) * horizontalSize * verticalSize;
                                            if (!bitSet.get(ac)) {
                                                bitSet.set(ac);
                                                mutable.set(t, v, aa);
                                                if (world.isValidForSetBlock(mutable)) {
                                                    ChunkSection chunkSection = chunkSectionCache.getSection(mutable);
                                                    if (chunkSection != null) {
                                                        int ad = ChunkSectionPos.getLocalCoord(t);
                                                        int ae = ChunkSectionPos.getLocalCoord(v);
                                                        int af = ChunkSectionPos.getLocalCoord(aa);
                                                        BlockState blockState = chunkSection.getBlockState(ad, ae, af);

                                                        for (OreFeatureConfig.Target target : config.targets) {
                                                            Objects.requireNonNull(chunkSectionCache);
                                                            if (shouldPlace(blockState, chunkSectionCache::getBlockState, random, config, target, mutable)) {
                                                                var state = target.state;
                                                                if (state.contains(FACING))
                                                                    state = state.with(FACING, Direction.random(random));
                                                                chunkSection.setBlockState(ad, ae, af, state, false);
                                                                ++i;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable var60) {
            try {
                chunkSectionCache.close();
            } catch (Throwable var59) {
                var60.addSuppressed(var59);
            }

            throw var60;
        }

        chunkSectionCache.close();
        return i > 0;
    }
}
