package net.stockieslad.terrains.util.registration.render;

import net.feltmc.abstractium.init.AbstractiumClient;
import net.minecraft.util.Identifier;

public class SignRenderingUtil {
    public static void registerTextures(Identifier texture) {
        AbstractiumClient.CLIENT_ABSTRACTION_HANDLER.abstraction.getRenderCalls().registerSign(texture);
        //SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, texture));
    }
}
