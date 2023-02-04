package net.fluffybumblebee.terrains.common.world.inbuilt_structures.carver.cloud;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverContext;
import net.minecraft.world.gen.carver.CarvingMask;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Function;


/**
 * Code from Paradise Lost under MIT License.
 */
public class CloudCarver extends Carver<CloudCarverConfig> {

    public CloudCarver(Codec<CloudCarverConfig> configCodec) {
        super(configCodec);
        this.alwaysCarvableBlocks = ImmutableSet.of(Blocks.AIR, Blocks.VOID_AIR, Blocks.CAVE_AIR);
    }

    private static boolean isPositionExcluded(double scaledRelativeX, double scaledRelativeY, double scaledRelativeZ) {
        return scaledRelativeX * scaledRelativeX + scaledRelativeY * scaledRelativeY + scaledRelativeZ * scaledRelativeZ >= 0.85D;
    }

    @Override
    public boolean carve(CarverContext context, CloudCarverConfig config, Chunk chunk, Function<BlockPos, RegistryEntry<Biome>> posToBiome, Random random, AquiferSampler sampler, ChunkPos pos, CarvingMask carvingMask) {

        double x = pos.getOffsetX(16);
        double y = config.y.get(random, context);
        double z = pos.getOffsetZ(16);

        int size = (int) Math.round((4 * 2 - 1) * (random.nextInt(4) + 1) * config.sizeMultiplier.get(random) * 1.5);

        int systemCount = 1;

        for (int i = 0; i < systemCount; ++i) {
            int tunnelCount = 1;
            float yawToPitchRatio = (random.nextFloat() / 4 + 0.4F);

            for (int j = 0; j < tunnelCount; j++) {
                float yaw = (float) (random.nextFloat() * Math.PI - (Math.PI / 2)) * config.yawMultiplier.get(random);
                float pitch = (float) (random.nextFloat() * Math.PI - (Math.PI / 2)) / 2;
                float width = random.nextFloat() * 2.0F + (random.nextFloat() * 3F) + 4.5F;
                if (random.nextInt(config.engorgementChance.get(random)) == 0) {
                    width *= random.nextFloat() * random.nextFloat() * 1.50F + 1.0F;
                }

                width *= config.widthMultiplier.get(random);

                int maxBranches = size - random.nextInt(size / 4);

                this.carveTunnels(context, config, chunk, posToBiome, carvingMask, random.nextLong(), sampler, x, y, z, width, yaw, pitch, yawToPitchRatio, maxBranches, ((context1, scaledRelativeX, scaledRelativeY, scaledRelativeZ, y1) -> isPositionExcluded(scaledRelativeX, scaledRelativeY, scaledRelativeZ)));
            }
        }
        return true;
    }

    protected void carveTunnels(CarverContext context, CloudCarverConfig config, Chunk chunk, Function<BlockPos, RegistryEntry<Biome>> posToBiome, CarvingMask carvingMask, long seed, AquiferSampler sampler, double x, double y, double z, float width, float yaw, float pitch, float yawToPitchRatio, int branchCount, SkipPredicate skipPredicate) {
        Random random = new Random(seed);

        int nextBranch = random.nextInt(branchCount / 2) + branchCount / 4;

        boolean steeperCave = random.nextInt(6) == 0;
        float yawChange = 0.0F;
        float pitchChange = 0.0F;

        for (int i = 0; i < branchCount; ++i) {
            double scaledYaw = 1.5 + (double) (MathHelper.sin(3.1415927F * (float) i / (float) branchCount) * width);
            double scaledPitch = scaledYaw * yawToPitchRatio;
            float delta = MathHelper.cos(pitch);

            x += MathHelper.cos(yaw) * delta;
            y += MathHelper.sin(pitch);
            z += MathHelper.sin(yaw) * delta;

            pitch *= steeperCave ? 0.82F : 0.65F;
            pitch += pitchChange * 0.1F;
            yaw += yawChange * 0.1F;
            pitchChange *= 0.9F;
            yawChange *= 0.75F;
            pitchChange += (random.nextFloat() - random.nextFloat()) * random.nextFloat();
            yawChange += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * random.nextFloat() * config.maxYaw.get(random);

            if (i == nextBranch && width > 1.0F && random.nextBoolean())
                return;

            if (random.nextInt(4) != 0) {
                carveRegion(context, config, chunk, posToBiome, sampler, x, y, z, scaledYaw, scaledPitch, carvingMask, skipPredicate);
            }
        }
    }

    @Override
    public boolean shouldCarve(CloudCarverConfig config, Random random) {
        return random.nextFloat() <= config.probability;
    }

    @Nullable
    @Override
    protected BlockState getState(CarverContext context, CloudCarverConfig config, BlockPos pos, AquiferSampler sampler) {
        return config.cloudState.getBlockState(new Random(), pos);
    }

    @Override
    protected boolean canAlwaysCarveBlock(BlockState state) {
        return state.isAir();
    }
}
