package net.fluffybumblebee.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class BoatRegistration {

    public static void register(String material, Supplier<TerraformBoatType> type, WithBoat withBoat) {
        var boat = TerraformBoatItemHelper.registerBoatItem(new Identifier(TerrainsDefaults.NAMESPACE,
                material + "_boat"), type);

        withBoat.register(new TerraformBoatType.Builder().item(boat).build());
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(TerrainsDefaults.NAMESPACE, material), type.get());

    }

    public interface WithBoat {
        void register(final TerraformBoatType item);
    }
}
