package net.fluffybumblebee.terrains.util.registration.render;

import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class RendererRegistration {
    public static void registerTextures(Identifier texture) {
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, texture));
    }
}
