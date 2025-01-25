package net.stockieslad.magical_utilities.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.core.Cloud;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CloudMixingRecipeProvider implements RecipeJsonProvider {

    private final Identifier id;
    private final List<? extends Block> ingredients;
    private final Block result;
    private final float successChance;

    public CloudMixingRecipeProvider(Identifier id, List<? extends Block> ingredients, Block result, float successChance) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
        this.successChance = successChance;
    }

    public CloudMixingRecipeProvider(List<? extends Block> ingredients, Cloud result, float successChance) {
        this(result.identifier, ingredients, result.block, successChance);
    }

    @Override
    public void serialize(JsonObject json) {
        json.add("ingredients", serializeBlocks(ingredients));
        json.add("result", new JsonPrimitive(CloudMixingRecipeSerializer.findId(result).toString()));
        json.add("successChance", new JsonPrimitive(successChance));
    }

    @Override
    public Identifier getRecipeId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CloudMixingRecipeSerializer.INSTANCE;
    }

    @Nullable
    @Override
    public JsonObject toAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    public Identifier getAdvancementId() {
        return null;
    }

    protected JsonElement serializeBlocks(List<? extends Block> blocks) {
        var array = new JsonArray();
        for (Block block : blocks) {
            array.add(CloudMixingRecipeSerializer.findId(block).toString());
        }
        return array;
    }
}
