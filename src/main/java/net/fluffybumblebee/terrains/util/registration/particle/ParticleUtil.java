package net.fluffybumblebee.terrains.util.registration.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

import static net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.getInstance;

@SuppressWarnings("deprecation")
public class ParticleUtil {
    public static <T extends ParticleEffect> ParticleType<T> registerCommon(
            String id, ParticleEffect.Factory<T> factory
    ) {
        return Registry.register(
                Registry.PARTICLE_TYPE,
                TerrainsDefaults.getIdentifier(id),
                FabricParticleTypes.complex(true, factory)
        );
    }

    public static <T extends ParticleEffect> void registerClient(
            ParticleType<T> particle, ParticleFactoryRegistry.PendingParticleFactory<T> factory
    ) {
        getInstance().register(particle, factory);
    }
}
