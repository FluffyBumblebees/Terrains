package net.stockieslad.magical_utilities.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.MuRecipes;

import java.util.List;

public record CloudMixingRecipe(Identifier id, List<Block> ingredients, Block result, float successChance) implements Recipe<SimpleInventory> {
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return ItemStack.EMPTY;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CloudMixingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return MuRecipes.CLOUD_MIXING_RECIPE_TYPE;
    }

    @Override
    public String toString() {
        return  "BurnRecipe[" +
                "id=" + id + ", " +
                "ingredients=" + ingredients + ", " +
                "results=" + result +
                "]@" + hashCode();
    }
}
