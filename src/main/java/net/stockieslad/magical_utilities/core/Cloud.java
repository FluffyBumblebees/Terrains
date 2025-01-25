package net.stockieslad.magical_utilities.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.stockieslad.magical_utilities.block.cloud.*;
import net.stockieslad.magical_utilities.util.BlockHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature;
import static net.fabricmc.fabric.api.biome.v1.BiomeSelectors.*;
import static net.minecraft.registry.tag.BiomeTags.IS_NETHER;
import static net.minecraft.registry.tag.BiomeTags.IS_OCEAN;
import static net.minecraft.world.biome.BiomeKeys.*;
import static net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION;
import static net.minecraft.world.gen.YOffset.fixed;
import static net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier.trapezoid;
import static net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier.uniform;
import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;
import static net.stockieslad.magical_utilities.util.BiomeHelper.IS_COLD;
import static net.stockieslad.magical_utilities.util.FeatureHelper.modifiersWithRarity;

/*
 *  TODO: Fix rendering of clouds to work when you are inside of it.
 *  TODO: Stair and slab variants with the same functionality.
 *  TODO: Rename Magmatic cloud to Blazing Cloud
 *  TODO: Dispenser support for cloud activation
 *  TODO: Tooltips for cloud activation/pacification ingredients
 *  TODO: Recipe Viewer support
 *  TODO: Change dormant from boolean to enum (congealed (like wool), dormant (stable), passive (crafting), active (ability))
 *  TODO: Fix the noise spamming to happen only when entering/leaving
 *  TODO: Tough as nails compat with dense cloud (hydration - quench thirst)
 *  TODO: Add spawns in aether
 */
public enum Cloud {
    ENERGIZED(
            new RedstoneCloud(),
            modifiersWithRarity(64, trapezoid(YOffset.BOTTOM, fixed(64))),
            cloud -> addFeature(excludeByKey(LUSH_CAVES).and(excludeByKey(DRIPSTONE_CAVES)).and(excludeByKey(DEEP_DARK)).and(foundInOverworld()), VEGETAL_DECORATION, cloud.placedFeature)
    ),
    MAGMATIC(new MagmaticCloud(),
            modifiersWithRarity(64, uniform(fixed(30), fixed(40))),
            cloud -> addFeature(tag(IS_NETHER), VEGETAL_DECORATION, cloud.placedFeature)),
    SULFUR(
            new DamagingCloud(),
            modifiersWithRarity(64, trapezoid(YOffset.BOTTOM, YOffset.TOP)),
            cloud -> addFeature(tag(IS_NETHER), VEGETAL_DECORATION, cloud.placedFeature)
    ),
    FERROUS(
            new MagneticCloud(),
            modifiersWithRarity(4, trapezoid(YOffset.BOTTOM, YOffset.TOP)),
            cloud -> addFeature(includeByKey(DRIPSTONE_CAVES).and(foundInOverworld()), VEGETAL_DECORATION, cloud.placedFeature)
    ),
    CHAOS(new RandomCloud()),
    LIVING(
            new HealingCloud(),
            modifiersWithRarity(4, trapezoid(YOffset.BOTTOM, YOffset.TOP)),
            cloud -> addFeature(includeByKey(LUSH_CAVES).and(foundInOverworld()), VEGETAL_DECORATION, cloud.placedFeature)
    ),
    ENDER(new TeleportingCloud()),
    CHARGED(new SmitingCloud()),
    GELID(new ColdCloud(),
            modifiersWithRarity(64, uniform(fixed(65), fixed(75))),
            cloud -> addFeature(IS_COLD, VEGETAL_DECORATION, cloud.placedFeature)
    ),
    DENSE(
            new HydroCloud(),
            modifiersWithRarity(64, uniform(fixed(65), fixed(75))),
            cloud -> addFeature(tag(IS_OCEAN).and(IS_COLD.negate()), VEGETAL_DECORATION, cloud.placedFeature)
    ),
    INDIGO(new DirectionalCloud()),
    IRRADIATED(new DestructiveCloud()),
    CHERRY(new NourishingCloud()),
    STEAM(
            new RisingCloud(),
            modifiersWithRarity(32, uniform(fixed(86), YOffset.belowTop(64))),
            cloud -> addFeature(foundInOverworld().and(tag(IS_OCEAN).negate()), VEGETAL_DECORATION, cloud.placedFeature)
    );

    public final List<PlacementModifier> modifiers;
    public final RegistryKey<ConfiguredFeature<?, ?>> configuredFeature;
    public final RegistryKey<PlacedFeature> placedFeature;

    public final Identifier identifier;
    public final BasicCloud block;
    public final BlockItem item;

    Cloud(BasicCloud block, @Nullable List<PlacementModifier> modifiers, @Nullable Consumer<Cloud> withCloud) {
        this.identifier = getIdentifier(this.name().toLowerCase() + "_cloud");
        this.block = block;
        this.item = new BlockItem(block, new FabricItemSettings());
        BlockHelper.registerBlockAndItem(identifier, block, item);

        if (modifiers != null && withCloud != null) {
            configuredFeature = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, identifier);
            placedFeature = RegistryKey.of(RegistryKeys.PLACED_FEATURE, identifier);
            this.modifiers = modifiers;
            withCloud.accept(this);
        } else {
            configuredFeature = null;
            placedFeature = null;
            this.modifiers = null;
        }
    }

    Cloud(BasicCloud block) {
        this(block, null, null);
    }

    public static void init() {}

    public static List<? extends Block> blocks(Cloud... clouds) {
        return Arrays.stream(clouds).map(cloud -> cloud.block).toList();
    }
}
