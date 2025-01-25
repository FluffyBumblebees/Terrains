package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.stockieslad.magical_utilities.recipe.CloudMixingRecipeProvider;

import java.util.function.Consumer;

import static net.stockieslad.magical_utilities.core.Cloud.*;

public class MuRecipeGenerator extends FabricRecipeProvider {
    public MuRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        exporter.accept(new CloudMixingRecipeProvider(blocks(STEAM, STEAM, STEAM, STEAM), DENSE, 0.15f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(DENSE, DENSE), GELID, 0.1f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(GELID, BLAZING), CHAOS, 0.1f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(LIVING, DENSE), CHERRY, 0.05f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(CHAOS, CHAOS, FERROUS), ENDER, 0.1f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(FERROUS, ENERGIZED, CHAOS), INDIGO, 0.25f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(STEAM, CHAOS, ENERGIZED, ENERGIZED), CHARGED, 0.02f));
        exporter.accept(new CloudMixingRecipeProvider(blocks(CHAOS, ENERGIZED, ENERGIZED), IRRADIATED, 0.01f));
    }
}
