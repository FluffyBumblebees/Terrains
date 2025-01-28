package net.stockieslad.magical_utilities.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.MuRecipes;

import java.util.List;
import java.util.Objects;

public final class CloudMixingRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final List<Block> ingredients;
    private final DefaultedList<Ingredient> defaultedIngredients;

    private final Block result;
    private final float successChance;

    public CloudMixingRecipe(Identifier id, List<Block> ingredients, Block result, float successChance) {
        this.id = id;
        this.ingredients = ingredients;
        this.defaultedIngredients = DefaultedList.copyOf(Ingredient.EMPTY, ingredients.stream().map(Ingredient::ofItems).toList().toArray(new Ingredient[] {}));
        this.result = result;
        this.successChance = successChance;
    }

    public List<Block> ingredients() {
        return ingredients;
    }

    public Block result() {
        return result;
    }

    public float successChance() {
        return successChance;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return defaultedIngredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient)
            return false;

        var matches = true;
        for (int i = 0; i < defaultedIngredients.size(); i++)
            if (!defaultedIngredients.get(i).test(inventory.getStack(i)))
                matches = false;
        return matches;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return result.asItem().getDefaultStack();
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return result.asItem().getDefaultStack();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
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
        return "CloudMixingRecipe[" +
                "id=" + id + ", " +
                "ingredients=" + ingredients + ", " +
                "results=" + result +
                "]@" + hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CloudMixingRecipe) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.ingredients, that.ingredients) &&
                Objects.equals(this.result, that.result) &&
                Float.floatToIntBits(this.successChance) == Float.floatToIntBits(that.successChance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredients, result, successChance);
    }

}
