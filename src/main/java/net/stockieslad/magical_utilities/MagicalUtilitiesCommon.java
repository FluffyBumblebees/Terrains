package net.stockieslad.magical_utilities;

import net.fabricmc.api.ModInitializer;
import net.stockieslad.magical_utilities.core.Clouds;
import net.stockieslad.magical_utilities.core.MUItemgroups;

public class MagicalUtilitiesCommon implements ModInitializer {
    @Override
    public void onInitialize() {
        Clouds.init();
        MUItemgroups.init();
    }
}
