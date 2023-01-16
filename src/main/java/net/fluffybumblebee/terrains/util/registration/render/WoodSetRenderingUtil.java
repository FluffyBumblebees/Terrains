package net.fluffybumblebee.terrains.util.registration.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSet;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.util.Identifier;

public class WoodSetRenderingUtil {
    public static void registerWoodSetEntities(WoodSet woodSet) {
        woodSet.getBlockIterator().forEach(element -> {
            if (element.BLOCK instanceof TerraformSignBlock signBlock)
                SignRenderingUtil.registerTextures(signBlock.getTexture());
        });
        TerraformBoatClientHelper.registerModelLayer(new Identifier(TerrainsDefaults.NAMESPACE,
                woodSet.TYPE));
    }
}
