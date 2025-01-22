package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MuDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(MuLangGenerator::new);
        pack.addProvider(MuLootGenerator::new);
        pack.addProvider(MuModelGenerator::new);
        pack.addProvider(MuBlockTagGenerator::new);
        pack.addProvider(MuItemTagGenerator::new);
    }
}
