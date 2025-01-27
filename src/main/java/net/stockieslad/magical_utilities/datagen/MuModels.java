package net.stockieslad.magical_utilities.datagen;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;

import java.util.Optional;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

public class MuModels {
    public static final Model
            CUBE_INTERNAL = block("cube_internal_all", TextureKey.ALL),
            COLUMN_INTERNAL = block("column_internal", TextureKey.END, TextureKey.SIDE),
            COLUMN_INTERNAL_HORIZONTAL = block("column_internal_horizontal", "_horizontal", TextureKey.END, TextureKey.SIDE);

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(getIdentifier("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    @SuppressWarnings("SameParameterValue")
    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(getIdentifier("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
}
