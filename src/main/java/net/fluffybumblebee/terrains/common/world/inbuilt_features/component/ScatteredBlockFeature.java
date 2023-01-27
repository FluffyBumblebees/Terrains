package net.fluffybumblebee.terrains.common.world.inbuilt_features.component;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ScatteredBlockFeature extends Feature<RandomPatchFeatureConfig> {
    public ScatteredBlockFeature(Codec<RandomPatchFeatureConfig> codec) {
        super(codec);
    }


    @Override
    public boolean generate(final FeatureContext<RandomPatchFeatureConfig> context) {
        final RandomPatchFeatureConfig randomPatchFeatureConfig = context.getConfig();
        final var random = context.getRandom();
        final BlockPos blockPos = context.getOrigin();
        final StructureWorldAccess structureWorldAccess = context.getWorld();
        final BlockPos.Mutable mutable = new BlockPos.Mutable();
        final int j = randomPatchFeatureConfig.xzSpread() + 1;
        final int k = randomPatchFeatureConfig.ySpread() + 1;
        int i = 0;

        for(int l = 0; l < 1; ++l) {
            mutable.set(blockPos, random.nextInt(j) - random.nextInt(j), random.nextInt(k) - random.nextInt(k), random.nextInt(j) - random.nextInt(j));
            if (randomPatchFeatureConfig.feature().value().generateUnregistered(structureWorldAccess, context.getGenerator(), random, mutable)) {
                ++i;
            }
        }

        return i > 0;
    }
}
