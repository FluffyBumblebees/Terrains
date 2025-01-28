package net.stockieslad.magical_utilities.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.MagicalUtilities;
import net.stockieslad.magical_utilities.core.Cloud;
import net.stockieslad.magical_utilities.recipe.CloudMixingRecipe;
import net.stockieslad.magical_utilities.util.MoreMath;
import net.stockieslad.magical_utilities.util.TextHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CloudMixingCategory implements IRecipeCategory<CloudMixingRecipe> {
    private static final Identifier ID = MagicalUtilities.getIdentifier("cloud_mixing");
    private static final Text TITLE = Text.translatable("recipe.magical_utilities.cloud_mixing");
    private static final Text DESCRIPTION = Text.translatable("recipe.magical_utilities.cloud_mixing.description");
    public static final RecipeType<CloudMixingRecipe> CLOUD_MIXING_TYPE = new RecipeType<>(ID, CloudMixingRecipe.class);

    private final IDrawable icon;
    private final IDrawableAnimated background;

    public CloudMixingCategory(IGuiHelper helper) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, Cloud.STEAM.item.getDefaultStack());
        this.background = helper.createAnimatedRecipeArrow(10);
    }

    @Override
    public @NotNull RecipeType<CloudMixingRecipe> getRecipeType() {
        return CLOUD_MIXING_TYPE;
    }

    @Override
    public @NotNull Text getTitle() {
        return TITLE;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public int getHeight() {
        return 75;
    }

    @Override
    public void draw(CloudMixingRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        var textRenderer = MinecraftClient.getInstance().textRenderer;
        TextHelper.drawTextWrapped(textRenderer, DESCRIPTION, 77, 85, 150,
                (renderer, text, x, y) -> guiGraphics.drawCenteredTextWithShadow(renderer, text, x, y, Colors.WHITE));
        guiGraphics.drawCenteredTextWithShadow(textRenderer, (int)(recipe.successChance() * 100) + "%", 77, 60, Colors.WHITE);
        background.draw(guiGraphics, 68, 29);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CloudMixingRecipe recipe, IFocusGroup focuses) {
        var size = recipe.getIngredients().size();
        var yRelative = 38 - (int) (Math.ceil((double) size / 3) * 10);

        for (int i = 0; i < size; i++) {
            var y = (int) (20 * Math.floor((double) i / 3)) + yRelative;
            var x = 20 * MoreMath.limit(i, 3);
            builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                    .addIngredients(recipe.getIngredients().get(i));
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 29).addItemLike(recipe.result());
    }
}
