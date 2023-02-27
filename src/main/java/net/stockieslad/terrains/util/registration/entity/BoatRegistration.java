package net.stockieslad.terrains.util.registration.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

import static com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper.registerBoatItem;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

public class BoatRegistration {

    public static void register(
            String material,
            final Supplier<TerraformBoatType> type,
            final Item planks,
            final BoatItemProvider boatItemProvider

    ) {
        final var boat = registerBoatItem(getIdentifier(material + "_boat"), type, false);
        final var chestBoat = registerBoatItem(getIdentifier(material + "_chest_boat"), type, true);

        boatItemProvider.register(new TerraformBoatType.Builder()
                .item(boat)
                .chestItem(chestBoat)
                .planks(planks)
                .build());

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, getIdentifier(material), type.get());
    }

    public interface BoatItemProvider {
        void register(final TerraformBoatType boatType);
    }
}
