package net.fluffybumblebee.terrains.common.instances.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

/**
 * Code from Paradise Lost under MIT License.
 */
@SuppressWarnings("deprecation")
public record ChromaticSplashParticleEffect(
    ParticleType<ChromaticSplashParticleEffect> type,
    int red,
    int green,
    int blue
) implements ParticleEffect {
    public static final Factory<ChromaticSplashParticleEffect> FACTORY = new Factory<>() {
        // Parses the command that can be used to create this effect
        @Override
        public ChromaticSplashParticleEffect read(ParticleType<ChromaticSplashParticleEffect> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            int red = MathHelper.clamp(reader.readInt(), 0, 255);
            reader.expect(' ');
            int green = MathHelper.clamp(reader.readInt(), 0, 255);
            reader.expect(' ');
            int blue = MathHelper.clamp(reader.readInt(), 0, 255);
            return new ChromaticSplashParticleEffect(type, red, green, blue);
        }
    
        @Override
        public ChromaticSplashParticleEffect read(ParticleType<ChromaticSplashParticleEffect> type, PacketByteBuf buf) {
            return new ChromaticSplashParticleEffect(type, Byte.toUnsignedInt(buf.readByte()), Byte.toUnsignedInt(buf.readByte()), Byte.toUnsignedInt(buf.readByte()));
        }
    };
    
    public ChromaticSplashParticleEffect(ParticleType<ChromaticSplashParticleEffect> type, int color){
        this(type, (color >>> 16) & 0xFF, (color >>> 8) & 0xFF, color & 0xFF);
    }
    
    @Override
    public ParticleType<?> getType() {
        return type;
    }
    
    @Override
    public void write(PacketByteBuf buf) {
        buf.writeByte(red);
        buf.writeByte(green);
        buf.writeByte(blue);
    }
    
    // Seems to be used to convert to a command, unsure if that is the case.
    @Override
    public String asString() {
        return Registry.PARTICLE_TYPE.getId(getType()) + " " + red + " " + green + " " + blue;
    }
}
