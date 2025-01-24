package net.stockieslad.magical_utilities.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.MagicalUtilities;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CloudMixingRecipeSerializer implements RecipeSerializer<CloudMixingRecipe> {

    public static final CloudMixingRecipeSerializer INSTANCE = new CloudMixingRecipeSerializer();
    public static final Identifier ID = MagicalUtilities.getIdentifier("cloud_mixing");

    @Override
    public CloudMixingRecipe read(Identifier id, JsonObject json) {
        var rawIngredients = json.get("ingredients");
        var rawResults = json.get("result");
        var rawSuccessChance = json.get("successChance");

        List<Block> ingredients = mapIds(rawIngredients).stream().map(CloudMixingRecipeSerializer::findBlock).toList();
        Block result = findBlock(rawResults.getAsString());
        float successChance = rawSuccessChance.getAsFloat();

        return new CloudMixingRecipe(id, ingredients, result, successChance);
    }

    @Override
    public CloudMixingRecipe read(Identifier id, PacketByteBuf buf) {
        var ingredients = Arrays.stream(buf.readString().split(";")).map(string -> Registries.BLOCK.get(new Identifier(string))).toList();
        var result = findBlock(buf.readString());
        var successChance = buf.readFloat();
        return new CloudMixingRecipe(id, ingredients, result, successChance);
    }

    @Override
    public void write(PacketByteBuf buf, CloudMixingRecipe recipe) {
        buf.writeString(inLineStringIds(recipe.ingredients().stream().map(block -> Registries.BLOCK.getId(block).toString()).toList()));
        buf.writeString(recipe.result().toString());
        buf.writeFloat(recipe.successChance());
    }

    protected static String inLineStringIds(List<String> ids) {
        var builder = new StringBuilder(ids.get(0));
        for (int i = 1; i < ids.size(); i++)
            builder.append(";").append(ids.get(i));
        return builder.toString();
    }

    private static List<String> mapIds(JsonElement element) {
        if (element.isJsonArray()) {
            return element.getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList();
        } else return List.of(element.getAsString());
    }

    protected static Block findBlock(String identifier) {
        return identifier == null ? null :
                Registries.BLOCK.getOrEmpty(new Identifier(identifier)).orElseThrow(() -> new JsonSyntaxException("No such Block: " + identifier));
    }

    protected static Identifier findId(Block block) {
        return Optional.of(Registries.BLOCK.getId(block)).orElseThrow(() -> new JsonSyntaxException("No such Block: " + block));
    }
}
