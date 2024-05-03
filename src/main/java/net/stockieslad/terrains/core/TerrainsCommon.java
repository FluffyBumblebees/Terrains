package net.stockieslad.terrains.core;

import net.fabricmc.api.ModInitializer;
import net.stockieslad.abstractium.library.common.AbstractCommonCalls;
import net.stockieslad.terrains.common.registry.itemgroups.FoodItemGroup;
import net.stockieslad.terrains.common.registry.itemgroups.NatureItemGroup;
import net.stockieslad.terrains.common.registry.itemgroups.SkiesItemGroup;
import net.stockieslad.terrains.common.registry.itemgroups.UndergroundItemGroup;
import net.stockieslad.terrains.common.registry.sets.RegistrySets;
import net.stockieslad.terrains.common.world.inbuilt_biomes.TerrainsBiomes;
import net.stockieslad.terrains.common.world.inbuilt_biomes.TerrainsGeneration;
import net.stockieslad.terrains.common.world.inbuilt_structures.features.component.placers.Placers;

import static net.stockieslad.abstractium.init.AbstractiumCommon.COMMON_ABSTRACTION_HANDLER;
import static net.stockieslad.terrains.core.TerrainsDefaults.CHAIN_LOGGER;
import static net.stockieslad.terrains.core.TerrainsDefaults.NAMESPACE;

public class TerrainsCommon implements ModInitializer {
    public static final AbstractCommonCalls ABSTRACTION = COMMON_ABSTRACTION_HANDLER.abstraction;
    @Override
    public void onInitialize() {
        TerrainsDefaults.init();

        RegistrySets.init();

        UndergroundItemGroup.register();
        NatureItemGroup.register();
        FoodItemGroup.register();
        SkiesItemGroup.register();

        Placers.register();
        TerrainsBiomes.init();

        TerrainsGeneration.register();

        CHAIN_LOGGER.info("Initialised " + NAMESPACE + "!");
    }
}
