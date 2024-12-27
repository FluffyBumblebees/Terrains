package net.stockieslad.magical_utilities;

import net.fabricmc.api.ModInitializer;
import net.stockieslad.magical_utilities.common.Clouds;

public class MagicalUtilitiesCommon implements ModInitializer {
    @Override
    public void onInitialize() {
        Clouds.init();
    }
}
