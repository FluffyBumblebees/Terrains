package net.fluffybumblebee.terrains.core;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fluffybumblebee.terrains.core.datagen.*;

public class TerrainsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(TerrainsBlockLootTables::new);
        fabricDataGenerator.addProvider(TerrainsRecipes::new);
        fabricDataGenerator.addProvider(TerrainsBiomeTags::new);
        fabricDataGenerator.addProvider(TerrainsBlockTags::new);
        fabricDataGenerator.addProvider(TerrainsItemTags::new);
    }
}
