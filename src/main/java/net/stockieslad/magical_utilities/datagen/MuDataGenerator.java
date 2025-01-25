package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.stockieslad.magical_utilities.world.MuConfiguredFeatures;
import net.stockieslad.magical_utilities.world.MuPlacedFeatures;

public class MuDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(MuLangGenerator::new);
        pack.addProvider(MuLootGenerator::new);
        pack.addProvider(MuModelGenerator::new);
        pack.addProvider(MuBlockTagGenerator::new);
        pack.addProvider(MuItemTagGenerator::new);
        pack.addProvider(MuRecipeGenerator::new);
        pack.addProvider(MuWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MuConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MuPlacedFeatures::bootstrap);
    }
}
