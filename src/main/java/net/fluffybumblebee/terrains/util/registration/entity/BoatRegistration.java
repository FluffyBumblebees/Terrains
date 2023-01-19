package net.fluffybumblebee.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

public class BoatRegistration {
    public static void register(String material, Supplier<TerraformBoatType> type, RegisterWithItem registerWithItem) {
        register(material, type, registerWithItem, true);
        register(material, type, registerWithItem, false);
    }

    public static void register(String material, Supplier<TerraformBoatType> type, RegisterWithItem registerWithItem,
                                boolean chest) {
        var item = TerraformBoatItemHelper.registerBoatItem(getIdentifier(material + "_boat"), type, chest);

        registerWithItem.register(item);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, getIdentifier(material), type.get());

    }

    public interface RegisterWithItem {
        void register(Item item);
    }
}
