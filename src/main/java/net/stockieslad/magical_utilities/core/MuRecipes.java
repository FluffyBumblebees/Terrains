package net.stockieslad.magical_utilities.core;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.recipe.CloudMixingRecipe;

import static net.stockieslad.magical_utilities.recipe.CloudMixingRecipeSerializer.ID;
import static net.stockieslad.magical_utilities.recipe.CloudMixingRecipeSerializer.INSTANCE;

public class MuRecipes {
    public static final RecipeType<CloudMixingRecipe> CLOUD_MIXING_RECIPE_TYPE = registerRecipeType(ID);

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, ID, INSTANCE);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final Identifier id) {
        return Registry.register(Registries.RECIPE_TYPE, id, new RecipeType<T>() {
            public String toString() {
                return id.getPath();
            }
        });
    }

}
