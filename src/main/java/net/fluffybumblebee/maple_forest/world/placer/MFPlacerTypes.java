package net.fluffybumblebee.maple_forest.world.placer;

import com.terraformersmc.terraform.tree.placer.PlacerTypes;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class MFPlacerTypes {
	public static final FoliagePlacerType<NoneFoliagePlacer> NONE_FOLIAGE_PLACER = PlacerTypes.registerFoliagePlacer( MapleForest.NAMESPACE + ":none", NoneFoliagePlacer.CODEC);
	public static final TrunkPlacerType<FallenTrunkPlacer> FALLEN_TRUNK_PLACER = PlacerTypes.registerTrunkPlacer(MapleForest.NAMESPACE + ":fallen", FallenTrunkPlacer.CODEC);
	public static final FoliagePlacerType<ConeFoliagePlacer> CONE_FOLIAGE_PLACER = PlacerTypes.registerFoliagePlacer(MapleForest.NAMESPACE + ":cone", ConeFoliagePlacer.CODEC);

	public static void addToRegistry() {
	}
}
