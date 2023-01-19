package net.fluffybumblebee.terrains.common.world.inbuilt_features.component.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import net.fluffybumblebee.terrains.util.janky_random.MinecraftRandomUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.BiConsumer;

public class HemiEllipsoidFoliagePlacer extends FoliagePlacer {
    public static final Codec<HemiEllipsoidFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance).apply(instance, HemiEllipsoidFoliagePlacer::new));

    public HemiEllipsoidFoliagePlacer(final IntProvider radius, final IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return Placers.CONE_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(
            final TestableWorld world, final BiConsumer<BlockPos, BlockState> replacer, final Random random,
            final TreeFeatureConfig config, final int trunkHeight, final TreeNode treeNode, final int foliageHeight,
            final int radius, final int offset
    ) {
        final var randomUtil = new MinecraftRandomUtil(random);
        final double newRadius = (Math.min(trunkHeight * 1.25, 2 * radius) - offset - 0.5) - randomUtil
                .randomFloatInHundredths(25);
        final double heightAffectsRadius = (trunkHeight + (newRadius * 4)) / 5;

        Shapes.hemiEllipsoid(newRadius, newRadius, heightAffectsRadius * 1.45
                        - (1.25 * newRadius) / newRadius - randomUtil.randomFloatInHundredths(35))
                .applyLayer(TranslateLayer.of(Position.of(treeNode.getCenter()).move(0,
                        - 2 - randomUtil.randomFloatInHundredths(100), 0)))
                .stream()
                .forEach((block) -> setBlockState(world, block.toBlockPos(), random, replacer, config));
    }

    private void setBlockState(
            final TestableWorld world, final BlockPos currentPosition, final Random random, final BiConsumer<BlockPos,
            BlockState> replacer, TreeFeatureConfig config
    ) {
        if (TreeFeature.canReplace(world, currentPosition)) {
            replacer.accept(currentPosition.toImmutable(), config.foliageProvider.getBlockState(random, currentPosition));
        }
    }

    @Override
    public int getRandomHeight(final Random random, final int trunkHeight, final TreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(
            final Random random, final int baseHeight, final int dx, final int dy, final int dz, final boolean bl
    ) {
        return false;
    }
}
