package net.fluffybumblebee.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BoatRegistration {
    private TerraformBoatType boatType;

    public BoatRegistration(String name, TerraformBoatType boatType) {
        this.boatType = boatType;
        register(name);
    }
    private void register(String name) {
        var item = TerraformBoatItemHelper.registerBoatItem(new Identifier(TerrainsDefaults.NAMESPACE, name + "_boat"), () -> boatType);

        boatType = new TerraformBoatType.Builder().item(item).build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(TerrainsDefaults.NAMESPACE, name), boatType);

    }
}
