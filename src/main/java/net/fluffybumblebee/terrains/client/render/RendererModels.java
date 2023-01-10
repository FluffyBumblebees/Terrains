package net.fluffybumblebee.terrains.client.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fluffybumblebee.maple_forest.init.MFRegistry;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.render.RendererRegistration;
import net.fluffybumblebee.terrains.util.type.wood.MFWoodTypes;
import net.minecraft.util.Identifier;

public class RendererModels {
    public static void registerModels() {
        RendererRegistration.registerTextures(MFRegistry.MAPLE_SIGN.getTexture());
        TerraformBoatClientHelper.registerModelLayer(new Identifier(TerrainsDefaults.NAMESPACE, MFWoodTypes.MAPLE));
    }
}
