package net.fluffybumblebee.quarkcrystals;

import net.fabricmc.api.ClientModInitializer;
import net.fluffybumblebee.quarkcrystals.block.QCRendering;

public class QuarkCrystalsClient implements ClientModInitializer {

    @Override public void onInitializeClient() {
        QCRendering.setUpRendering();
    }
}
