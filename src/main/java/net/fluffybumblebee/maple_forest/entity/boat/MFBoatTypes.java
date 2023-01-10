package net.fluffybumblebee.maple_forest.entity.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fluffybumblebee.terrains.Terrains;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MFBoatTypes {
    private TerraformBoatType boatType;

    public MFBoatTypes(String name, TerraformBoatType boatType) {
        this.boatType = boatType;
        register(name);
    }
    void register(String name) {
        var item = TerraformBoatItemHelper.registerBoatItem(new Identifier(Terrains.NAMESPACE, name + "_boat"), () -> boatType);

        boatType = new TerraformBoatType.Builder().item(item).build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Terrains.NAMESPACE, name), boatType);

    }
}
