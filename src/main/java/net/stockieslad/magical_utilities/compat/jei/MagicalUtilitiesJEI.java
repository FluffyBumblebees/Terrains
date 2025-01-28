package net.stockieslad.magical_utilities.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.core.MuRecipes;
import net.stockieslad.magical_utilities.recipe.CloudMixingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

@JeiPlugin
public class MagicalUtilitiesJEI implements IModPlugin {
    public static final Identifier PLUGIN_ID = getIdentifier("jei_plugin");

    @Override
    public @NotNull Identifier getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CloudMixingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var world = MinecraftClient.getInstance().world;

        assert world != null;
        RecipeManager manager = world.getRecipeManager();

        List<CloudMixingRecipe> cloudMixingRecipes = manager.listAllOfType(MuRecipes.CLOUD_MIXING_RECIPE_TYPE);
        registration.addRecipes(CloudMixingCategory.CLOUD_MIXING_TYPE, cloudMixingRecipes);
    }
}
