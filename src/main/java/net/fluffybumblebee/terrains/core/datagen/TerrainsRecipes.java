package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSet;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

import static net.fluffybumblebee.terrains.util.datagen.DatagenUtil.*;

public class TerrainsRecipes extends FabricRecipeProvider {

    public TerrainsRecipes(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        //ShapedRecipeJsonBuilder.create()

        AllRegistrySets.CRYSTAL_GEODES.getConfigQuickerator().forEach(element -> {
            twoSquared(exporter, 1, element.CORUNDUM, element.CORUNDUM_CLUSTER);
            smelt(exporter, element.CORUNDUM, element.CRYSTAL);
            slab(exporter, element.CORUNDUM_STAIRS, element.CORUNDUM);
            stairs(exporter, element.CORUNDUM_SLAB, element.CORUNDUM);
            pane(exporter, element.CORUNDUM_PANE, element.CORUNDUM);
            slab(exporter, element.CRYSTAL_STAIRS, element.CRYSTAL);
            stairs(exporter, element.CRYSTAL_SLAB, element.CRYSTAL);
            pane(exporter, element.CRYSTAL_PANE, element.CRYSTAL);
        });
    }


    private static void woodSet(
            final Consumer<RecipeJsonProvider> exporter,
            final WoodSet set
    ) {
        stairs(exporter, set.STAIRS, set.PLANKS);
        slab(exporter, set.SLAB, set.PLANKS);
        boat(exporter, set.getBoat(), set.TYPE, set.PLANKS);
        sign(exporter, set.SIGN, set.PLANKS);
        single(exporter, set.BUTTON, 1, set.PLANKS);
        pressurePlate(exporter, set.PRESSURE_PLATE, set.PLANKS);
    }
}
