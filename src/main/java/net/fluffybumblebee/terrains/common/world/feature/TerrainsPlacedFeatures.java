package net.fluffybumblebee.terrains.common.world.feature;

import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiersWithWouldSurvive;

public class TerrainsPlacedFeatures {
    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return register(TerrainsDefaults.getNamespaceVar() +  id, registryEntry, modifiers);
    }

    @SuppressWarnings("unused")
    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerToMod(id, registryEntry, List.of(modifiers));
    }

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE = registerToMod(
            "meadow_spruce",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.25f, 2))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE_SPARSE = registerToMod(
            "meadow_spruce_sparse",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.5f, 1))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_BUSH = registerToMod(
            "meadow_bush",
            TerrainsConfiguredFeatures.MEADOW_BUSH,
            modifiersWithWouldSurvive(createCountExtraModifier(1, 0.5f, 2), Blocks.OAK_SAPLING)
    );


    public static final RegistryEntry<PlacedFeature> MEADOW_LAKE = registerToMod(
            "meadow_lake",
            TerrainsConfiguredFeatures.MEADOW_LAKE,
            modifiers(createCountExtraModifier(0, 0.1f, 20))
    );

    public static final RegistryEntry<PlacedFeature> HUGE_RED_MUSHROOM = registerToMod(
            "huge_red_mushroom",
            TreeConfiguredFeatures.HUGE_RED_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

    public static final RegistryEntry<PlacedFeature> HUGE_BROWN_MUSHROOM = registerToMod(
            "huge_brown_mushroom",
            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM,
            modifiers(createCountExtraModifier(0, 0.1f, 1))
    );

}
