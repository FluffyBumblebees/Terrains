package net.fluffybumblebee.terrains.common.world.feature.raw.component;

import com.terraformersmc.terraform.tree.placer.PlacerTypes;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class Placers {
	public static final FoliagePlacerType<NoneFoliagePlacer> NONE_FOLIAGE_PLACER = PlacerTypes.registerFoliagePlacer( TerrainsDefaults.NAMESPACE + ":none", NoneFoliagePlacer.CODEC);
	public static final TrunkPlacerType<FallenTrunkPlacer> FALLEN_TRUNK_PLACER = PlacerTypes.registerTrunkPlacer(TerrainsDefaults.NAMESPACE + ":fallen", FallenTrunkPlacer.CODEC);
	public static final FoliagePlacerType<ConeFoliagePlacer> CONE_FOLIAGE_PLACER = PlacerTypes.registerFoliagePlacer(TerrainsDefaults.NAMESPACE + ":cone", ConeFoliagePlacer.CODEC);

	public static void register() {}
}
