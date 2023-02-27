package net.stockieslad.terrains.common.registry.sets.clouds;

import net.stockieslad.terrains.common.instances.block.cloud.*;
import net.stockieslad.terrains.common.registry.sets.clouds.component.CloudSet;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3f;

import static net.stockieslad.terrains.common.registry.sets.clouds.component.CloudSet.aercloud;

public class TypesCloudSet {
    public static final CloudSet.Config
            BLAZING,
            BLUE,
            COLD,
            GOLDEN,
            GREEN,
            IRRADIATED,
            PINK,
            PURPLE,
            STORM,
            TEAL;

    public static final CloudSet.Config[] ALL = {
            BLAZING = of(new ParticleEmittingCloudBlock(aercloud(), new DustParticleEffect(new Vec3f(0.89F, 0.65F,
            0.9F), 1F), true), 0.008F, "blazing", true),
            BLUE = of(new RisingCloudBlock(aercloud()), 0.03F, "blue"),
            COLD = of(new BasicCloudBlock(aercloud()), 0.1F, "cold"),
            GOLDEN = of(new SinkingCloudBlock(aercloud()), 0.01F, "golden"),
            GREEN = of(new OmniDirectionalCloudBlock(aercloud()), 0.01F, "green"),
            IRRADIATED = of(new RedstoneCloudBlock(aercloud()), 0.008F, "irradiated", true),
            PINK = of(new ParticleEmittingCloudBlock(aercloud(), new DustParticleEffect(new Vec3f(0.89F, 0.65F, 0.9F), 1F),
                    true), 0.002F, "pink"),
            PURPLE = of(new DirectionalCloudBlock(aercloud()), 0.005F, "purple"),
            STORM = of(new BasicCloudBlock(aercloud()), 0.01F, "storm"),
            TEAL = of(new BasicCloudBlock(aercloud()), 0.01F, "teal")
    };

    private static CloudSet.Config of(
            final BasicCloudBlock cloudBlock, final float probability, final String id, final boolean burnable
    ) {
        return new CloudSet.Config(cloudBlock, probability, id, burnable);
    }

    private static CloudSet.Config of(final BasicCloudBlock cloudBlock, final float probability, final String id) {
        return of(cloudBlock, probability, id, false);
    }
}
