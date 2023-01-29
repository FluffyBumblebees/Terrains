package net.fluffybumblebee.terrains.util.registration.render;

import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSet;

import static com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper.registerModelLayers;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.render.SignRenderingUtil.registerTextures;

public class WoodSetRenderingUtil {
    public static void registerWoodSetEntities(WoodSet woodSet) {
        registerTextures(woodSet.getSign().BLOCK.getTexture());
        registerModelLayers(getIdentifier(woodSet.TYPE));
    }
}
