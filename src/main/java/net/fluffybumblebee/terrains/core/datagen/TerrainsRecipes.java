package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageType.FlowerTypes;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSet;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.CRYSTAL_GEODES;
import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.FULL_TREES;
import static net.fluffybumblebee.terrains.common.registry.sets.foliage.flower.FlowerFoliageAccess.accessFlowerConfig;
import static net.fluffybumblebee.terrains.util.datagen.DatagenRecipeUtil.*;

public class TerrainsRecipes extends FabricRecipeProvider {

    public TerrainsRecipes(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        CRYSTAL_GEODES.getConfigQuickerator().forEach(element -> {
            offerStonecuttingRecipe(exporter, element.CORUNDUM_STAIRS.BLOCK, element.WAXED_CORUNDUM.BLOCK);
            offerStonecuttingRecipe(exporter, element.CRYSTAL_STAIRS.BLOCK, element.CRYSTAL.BLOCK);
            offerStonecuttingRecipe(exporter, element.CORUNDUM_SLAB.BLOCK, element.CORUNDUM_STAIRS.BLOCK);
            offerStonecuttingRecipe(exporter, element.CRYSTAL_SLAB.BLOCK, element.CRYSTAL_STAIRS.BLOCK);
            offerStonecuttingRecipe(exporter, element.CORUNDUM_SLAB.BLOCK, element.WAXED_CORUNDUM.BLOCK, 2);
            offerStonecuttingRecipe(exporter, element.CRYSTAL_SLAB.BLOCK, element.CRYSTAL.BLOCK, 2);
            twoSquared(exporter, 1, element.CORUNDUM, element.CORUNDUM_CLUSTER);
            smelt(exporter, element.CRYSTAL, element.CORUNDUM);
            smelt(exporter, element.CRYSTAL, element.WAXED_CORUNDUM);
            slab(exporter, element.CORUNDUM_SLAB, element.WAXED_CORUNDUM);
            slab(exporter, element.CRYSTAL_SLAB, element.CRYSTAL);
            stairs(exporter, element.CORUNDUM_STAIRS, element.WAXED_CORUNDUM);
            stairs(exporter, element.CRYSTAL_STAIRS, element.CRYSTAL);
            pane(exporter, element.CORUNDUM_PANE, element.WAXED_CORUNDUM);
            pane(exporter, element.CRYSTAL_PANE, element.CRYSTAL);
        });

        FULL_TREES.getConfigQuickerator().forEach(element -> woodSet(exporter, element.WOOD_SET));

        for (FlowerTypes flowerTypes : FlowerTypes.values())
            single(exporter, flowerTypes.DYE, accessFlowerConfig(flowerTypes).block());
    }


    private static void woodSet(
            final Consumer<RecipeJsonProvider> exporter,
            final WoodSet set
    ) {
        single(exporter, set.PLANKS, 4, set.LOG);
        single(exporter, set.PLANKS, 4, set.WOOD);
        single(exporter, set.PLANKS, 4, set.STRIPPED_LOG);
        single(exporter, set.PLANKS, 4, set.STRIPPED_WOOD);
        stairs(exporter, set.STAIRS, set.PLANKS);
        slab(exporter, set.SLAB, set.PLANKS);
        boat(exporter, set.getBoat(), set.TYPE, set.PLANKS);
        sign(exporter, set.SIGN, set.PLANKS);
        single(exporter, set.BUTTON, 1, set.PLANKS);
        pressurePlate(exporter, set.PRESSURE_PLATE, set.PLANKS);
        fence(exporter, set.FENCE, set.PLANKS);
        fenceGate(exporter, set.FENCE_GATE, set.PLANKS);
        twoSquared(exporter, 3, set.WOOD, set.LOG);
        twoSquared(exporter, 3, set.STRIPPED_WOOD, set.STRIPPED_LOG);
    }
}
