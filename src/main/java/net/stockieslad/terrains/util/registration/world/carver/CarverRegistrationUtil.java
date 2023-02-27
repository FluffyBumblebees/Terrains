package net.stockieslad.terrains.util.registration.world.carver;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;

import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;

@SuppressWarnings("unchecked")
public class CarverRegistrationUtil {
    public static <T extends CarverConfig> Carver<T> register(String name, Carver<?> carver) {
        return (Carver<T>) Registry.register(Registry.CARVER, getIdentifier(name), carver);
    }

    public static RegistryEntry<ConfiguredCarver<?>> register(String name, ConfiguredCarver<?> carver) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_CARVER, getIdentifier(name), carver);
    }
}
