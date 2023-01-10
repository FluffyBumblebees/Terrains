package net.fluffybumblebee.maple_forest.client.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fluffybumblebee.maple_forest.client.render.cutout.MFCutouts;
import net.fluffybumblebee.maple_forest.client.render.translucent.MFTranslucents;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.minecraft.util.Identifier;

public class MFRenderer {
    public static void registerEntityRenderers(String type) {
        TerraformBoatClientHelper.registerModelLayer(new Identifier(MapleForest.NAMESPACE, type));
        MFCutouts.registerCutouts();
        MFTranslucents.registerTranslucents();
    }
}
