package net.stockieslad.terrains.util.registration.render;

import net.minecraft.util.Identifier;
import net.stockieslad.abstractium.init.AbstractiumClient;

public class SignRenderingUtil {
    public static void registerTextures(Identifier texture) {
        AbstractiumClient.CLIENT_ABSTRACTION_HANDLER.abstraction.getRenderCalls().registerSign(texture);
        //SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, texture));
    }
}
