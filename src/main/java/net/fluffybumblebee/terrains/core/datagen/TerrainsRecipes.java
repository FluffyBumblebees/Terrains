package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class TerrainsRecipes extends FabricRecipeProvider {
    private static CriterionConditions emptyCriterion(Identifier identifier) {
        return new AbstractCriterionConditions(identifier, EntityPredicate.Extended.EMPTY) {
            @Override
            public Identifier getId() {
                return super.getId();
            }
        };
    }

    public TerrainsRecipes(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        //ShapedRecipeJsonBuilder.create()

        AllRegistrySets.CRYSTAL_GEODES.getConfigQuickerator().forEach(element -> {
            twoSquared(exporter, 1, element.CORUNDUM, element.CORUNDUM_CLUSTER);
            slab(exporter, element.CORUNDUM_STAIRS, element.CORUNDUM);
            stairs(exporter, element.CORUNDUM_SLAB, element.CORUNDUM);
            pane(exporter, element.CORUNDUM_PANE, element.CORUNDUM);
            slab(exporter, element.CRYSTAL_STAIRS, element.CRYSTAL);
            stairs(exporter, element.CRYSTAL_SLAB, element.CRYSTAL);
            pane(exporter, element.CRYSTAL_PANE, element.CRYSTAL);
        });
    }


    private static void stairs(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, 8)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group( "stairs")
                .pattern("x  ")
                .pattern("xx ")
                .pattern("xxx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        TerrainsDefaults.getIdentifier(
                                output.IDENTIFIER.getPath() + "_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    private static void slab(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, 6)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("slab")
                .pattern("xxx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        TerrainsDefaults.getIdentifier(
                                output.IDENTIFIER.getPath() + "_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    private static void pane(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, 16)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("pane")
                .pattern("xxx")
                .pattern("xxx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        TerrainsDefaults.getIdentifier(
                                output.IDENTIFIER.getPath() + "_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    private static void twoSquared(
            final Consumer<RecipeJsonProvider> exporter,
            final int outputCount,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, outputCount)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("two_squared")
                .pattern("xx")
                .pattern("xx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        TerrainsDefaults.getIdentifier(
                                output.IDENTIFIER.getPath() + "_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }
}
