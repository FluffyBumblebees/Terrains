package net.stockieslad.magical_utilities;

import net.fabricmc.api.ModInitializer;
import net.stockieslad.magical_utilities.core.Cloud;
import net.stockieslad.magical_utilities.core.MuItemgroups;
import net.stockieslad.magical_utilities.core.MuRecipes;
import net.stockieslad.magical_utilities.world.MuFeatures;

public class MagicalUtilitiesCommon implements ModInitializer {
    @Override
    public void onInitialize() {
        MuFeatures.init();
        Cloud.init();
        MuItemgroups.init();
        MuRecipes.init();
    }
}
