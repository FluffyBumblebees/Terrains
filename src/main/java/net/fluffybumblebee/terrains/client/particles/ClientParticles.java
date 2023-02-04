package net.fluffybumblebee.terrains.client.particles;

import net.fluffybumblebee.terrains.common.instances.particle.ChromaticSplashParticle;

import static net.fluffybumblebee.terrains.common.registry.particle.TerrainsParticles.CHROMATIC_SPLASH;
import static net.fluffybumblebee.terrains.util.registration.particle.ParticleUtil.registerClient;

public class ClientParticles {
    public static void init() {}

    static {
        registerClient(CHROMATIC_SPLASH, ChromaticSplashParticle.FACTORY);
    }
}
