package net.stockieslad.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoatRegistration {

    public static void register(String material, Supplier<TerraformBoatType> type, Consumer<TerraformBoatType> consumer) {
        var boat = TerraformBoatItemHelper.registerBoatItem(new Identifier(TerrainsDefaults.NAMESPACE,
                material + "_boat"), type);
        consumer.accept(new TerraformBoatType.Builder().item(boat).build());
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(TerrainsDefaults.NAMESPACE, material), type.get());
    }
}
