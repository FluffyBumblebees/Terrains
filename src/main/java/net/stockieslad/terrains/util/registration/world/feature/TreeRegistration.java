package net.stockieslad.terrains.util.registration.world.feature;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import static net.minecraft.tag.BlockTags.DIRT;
import static net.minecraft.util.math.Direction.DOWN;
import static net.minecraft.world.gen.blockpredicate.BlockPredicate.matchingBlockTag;
import static net.minecraft.world.gen.feature.PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP;

public class TreeRegistration {

    public static ImmutableList.Builder<PlacementModifier> standardPlacementModifiers(PlacementModifier countModifier) {
        var list = new ImmutableList.Builder<PlacementModifier>();
        list    .add(countModifier)
                .add(SquarePlacementModifier.of())
                .add(MOTION_BLOCKING_HEIGHTMAP)
                .add(BiomePlacementModifier.of());
        return list;
    }

    public static ImmutableList<PlacementModifier> treePlacementModifiers(PlacementModifier countModifier) {
        return standardPlacementModifiers(countModifier)
                .add(PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING))
                .add(BlockFilterPlacementModifier.of(matchingBlockTag(DOWN.getVector(), DIRT)))
                .build();
    }
}
