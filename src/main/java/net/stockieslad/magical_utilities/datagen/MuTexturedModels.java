package net.stockieslad.magical_utilities.datagen;

import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;

import static net.minecraft.data.client.TexturedModel.makeFactory;

public class MuTexturedModels {
    public static final TexturedModel.Factory
            CUBE_INTERNAL = makeFactory(TextureMap::all, MuModels.CUBE_INTERNAL),
            COLUMN_INTERNAL = makeFactory(TextureMap::sideEnd, MuModels.COLUMN_INTERNAL),
            COLUMN_INTERNAL_HORIZONTAL = makeFactory(TextureMap::sideEnd, MuModels.COLUMN_INTERNAL_HORIZONTAL);
}
