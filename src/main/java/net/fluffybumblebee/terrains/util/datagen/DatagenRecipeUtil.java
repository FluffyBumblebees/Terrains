package net.fluffybumblebee.terrains.util.datagen;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import net.fluffybumblebee.terrains.util.registration.mass.UnsafeTriSet;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.minecraft.data.server.RecipeProvider.*;
import static net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder.create;

public class DatagenRecipeUtil {
    public static CriterionConditions defaultCriterion(Item item) {
        return InventoryChangedCriterion.Conditions.items(item);
    }

    public static void stairs(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 4)
                .criterion("check_block", defaultCriterion(output.ITEM))
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
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 6)
                .criterion("check_block", defaultCriterion(output.ITEM))
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
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 16)
                .criterion("check_block", defaultCriterion(output.ITEM))
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
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, outputCount)
                .criterion("check_block", defaultCriterion(output.ITEM))
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

    public static void twoSquared(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        twoSquared(exporter, 1, output, input);
    }

    public static void boat(
            final Consumer<RecipeJsonProvider> exporter,
            final TerraformBoatType output,
            final String type,
            final UnsafeTriSet<?> input
    ) {
        final var boat = getIdentifier(type+ "_boat");

        create(output.getItem(), 1)
                .criterion("check_block", defaultCriterion(output.getItem()))
                .group("boat")
                .pattern("x x")
                .pattern("xxx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                boat.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void sign(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 1)
                .criterion("check_block", defaultCriterion(output.ITEM))
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
            final Item output,
            final Identifier outputID,
            final int outputCount,
            final UnsafeTriSet<?> input
    ) {
        ShapelessRecipeJsonBuilder.create(output, outputCount)
                .criterion("check_block", defaultCriterion(output))
                .group("single")
                .input(input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                outputID.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void single(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final int outputCount,
            final UnsafeTriSet<?> input
    ) {
        single(exporter, output.ITEM, output.IDENTIFIER, outputCount, input);
    }

    public static void single(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        single(exporter, output.ITEM, output.IDENTIFIER, 1, input);
    }

    public static void pressurePlate(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 1)
                .criterion("check_block", defaultCriterion(output.ITEM))
                .group("pressure_plate")
                .pattern("xx")
                .input('x', input.BLOCK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void fence(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 3)
                .criterion("check_block", defaultCriterion(output.ITEM))
                .group("fence")
                .pattern("xsx")
                .pattern("xsx")
                .input('x', input.BLOCK)
                .input('s', Items.STICK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void fenceGate(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        create(output.BLOCK, 1)
                .criterion("check_block", defaultCriterion(output.ITEM))
                .group("fence_gate")
                .pattern("sxs")
                .pattern("sxs")
                .input('x', input.BLOCK)
                .input('s', Items.STICK)
                .offerTo(exporter,
                        getIdentifier(
                                output.IDENTIFIER.getPath() + "_crafted_from_" +
                                        input.IDENTIFIER.getPath())
                );
    }

    public static void smelt(
            final Consumer<RecipeJsonProvider> exporter,
            final UnsafeTriSet<?> output,
            final UnsafeTriSet<?> input
    ) {
        offerSmelting(
                exporter,
                List.of(input.BLOCK),
                output.BLOCK,
                0.5F,
                200,
                "smelting"
        );

        offerBlasting(
                exporter,
                List.of(input.BLOCK),
                output.BLOCK,
                0.5F,
                100,
                "blasting"
        );
    }
}
