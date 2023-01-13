package net.fluffybumblebee.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class BoatRegistration {

    public static void register(String material, Supplier<TerraformBoatType> type, RegisterWithItem registerWithItem) {
        var item = TerraformBoatItemHelper.registerBoatItem(new Identifier(TerrainsDefaults.NAMESPACE,
                material + "_boat"), type);

        registerWithItem.register(item);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(TerrainsDefaults.NAMESPACE, material), type.get());

    }

    public interface RegisterWithItem {
        void register(Item item);
    }
}
