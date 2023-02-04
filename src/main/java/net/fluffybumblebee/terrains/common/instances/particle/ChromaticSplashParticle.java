package net.fluffybumblebee.terrains.common.instances.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.RainSplashParticle;
import net.minecraft.client.world.ClientWorld;

/**
 * Code from Paradise Lost under MIT License.
 */
@Environment(EnvType.CLIENT)
public class ChromaticSplashParticle extends RainSplashParticle {
    /**
     * The constant of 1/255
     */
    private static final float BYTE_TO_FLOAT = 0.003921569F;
    
    /**
     * The client side factory for this particle.
     */
    public static final ParticleFactoryRegistry.PendingParticleFactory<ChromaticSplashParticleEffect> FACTORY = (provider)->(parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> {
        var particle = new ChromaticSplashParticle(world, x, y, z, velocityX, velocityY, velocityX, provider);
        particle.setColor(parameters.red() * BYTE_TO_FLOAT, parameters.green() * BYTE_TO_FLOAT, parameters.blue() * BYTE_TO_FLOAT);
        return particle;
    };
    
    /**
     * Creates a new particle.
     *
     * @param clientWorld The client's world
     * @param x The X position of the particle
     * @param y The Y position of the particle
     * @param z The Z position of the particle
     * @param velocityX The X velocity of the particle
     * @param velocityY The Y velocity of the particle
     * @param velocityZ The Z velocity of the particle
     * @param provider The particle provider for this particle
     */
    ChromaticSplashParticle(ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ, FabricSpriteProvider provider) {
        super(clientWorld, x, y, z);
    
        setSprite(provider);
        
        gravityStrength = 0.04f;
        if (velocityY == 0.0 && (velocityX != 0.0 || velocityZ != 0.0)) {
            this.velocityX = velocityX;
            this.velocityY = 0.1;
            this.velocityZ = velocityZ;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
