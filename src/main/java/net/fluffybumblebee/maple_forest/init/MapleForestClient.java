package net.fluffybumblebee.maple_forest.init;

import net.fabricmc.api.ClientModInitializer;
import net.fluffybumblebee.maple_forest.client.render.MFRenderer;
import net.fluffybumblebee.maple_forest.util.registration.render.MFRenderRegistration;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;


public class MapleForestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MFRenderer.registerEntityRenderers(MFWoodTypes.MAPLE);
        MFRenderRegistration.registerTextures(MFRegistry.MAPLE_SIGN.getTexture());
    }

}
