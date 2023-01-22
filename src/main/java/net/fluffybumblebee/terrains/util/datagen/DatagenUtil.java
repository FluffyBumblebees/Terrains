package net.fluffybumblebee.terrains.util.datagen;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.minecraft.data.server.RecipeProvider.offerSmelting;

public class DatagenUtil {
    public static CriterionConditions emptyCriterion(Identifier identifier) {
        return new AbstractCriterionConditions(identifier, EntityPredicate.Extended.EMPTY) {
            @Override
            public Identifier getId() {
                return super.getId();
            }
        };
    }

    public static void stairs(
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
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void slab(
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
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void pane(
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
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void twoSquared(
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
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void boat(
            final Consumer<RecipeJsonProvider> exporter,
            final TerraformBoatType output,
            final String type,
            final BlockSet<?> input
    ) {
        final var boat = getIdentifier(type+ "_boat");

        ShapedRecipeJsonBuilder.create(output.getItem(), 1)
                .criterion("", emptyCriterion(getIdentifier(type+ "_boat")))
                .group("boat")
                .pattern("x x")
                .pattern("xxx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                boat + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void sign(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, 1)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("sign")
                .pattern("xxx")
                .pattern("xxx")
                .pattern(" y ")
                .input('x', input.BLOCK)
                .input('y', Items.STICK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void single(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final int outputCount,
            final BlockSet<?> input
    ) {
        ShapelessRecipeJsonBuilder.create(output.BLOCK, outputCount)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("single")
                .input(input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void pressurePlate(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        ShapedRecipeJsonBuilder.create(output.BLOCK, 1)
                .criterion("", emptyCriterion(output.IDENTIFIER))
                .group("pressure_plate")
                .pattern("xx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void smelt(
            final Consumer<RecipeJsonProvider> exporter,
            final BlockSet<?> output,
            final BlockSet<?> input
    ) {
        offerSmelting(
                exporter,
                List.of(input.BLOCK),
                output.BLOCK,
                0.25F,
                200,
                "smelting"
        );
    }
}
