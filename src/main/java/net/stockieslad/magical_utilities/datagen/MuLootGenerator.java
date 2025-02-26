package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.stockieslad.magical_utilities.core.Cloud;

public class MuLootGenerator extends FabricBlockLootTableProvider {
    protected MuLootGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (Cloud value : Cloud.values()) {
            this.addDrop(value.block);
        }
    }
}
