package net.fluffybumblebee.terrains.client.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.fluffybumblebee.terrains.util.registration.render.RendererRegistration;
import net.fluffybumblebee.terrains.util.type.wood.MFWoodTypes;
import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;

public class RendererModels {
    public static void registerModels() {
        AllRegistrySets.FULL_TREES.forEach(element -> {
            if (element.BLOCK instanceof SignBlock)
                RendererRegistration.registerTextures(element.IDENTIFIER);
        });

        //RendererRegistration.registerTextures(MFRegistry.MAPLE_SIGN.getTexture());
        TerraformBoatClientHelper.registerModelLayer(new Identifier(TerrainsDefaults.NAMESPACE, MFWoodTypes.MAPLE));
    }
}
