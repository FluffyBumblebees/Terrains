package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.stockieslad.magical_utilities.core.Clouds;
import net.stockieslad.magical_utilities.core.MuTags;

import java.util.concurrent.CompletableFuture;

public class MuItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public MuItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registry) {
        var clouds = getOrCreateTagBuilder(MuTags.ITEM_CLOUDS);
        for (Clouds value : Clouds.values()) {
            clouds.add(value.identifier);
        }
    }
}
