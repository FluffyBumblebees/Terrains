package net.fluffybumblebee.terrains.common.registry.particle;

import net.fluffybumblebee.terrains.common.instances.particle.ChromaticSplashParticleEffect;
import net.minecraft.particle.ParticleType;

import static net.fluffybumblebee.terrains.util.registration.particle.ParticleUtil.registerCommon;

public class TerrainsParticles {
    public static ParticleType<ChromaticSplashParticleEffect> CHROMATIC_SPLASH = registerCommon("chromatic_splash",
            ChromaticSplashParticleEffect.FACTORY);

    public static ChromaticSplashParticleEffect chromaticSplash(int color) {
        return new ChromaticSplashParticleEffect(CHROMATIC_SPLASH, color);
    }
}
