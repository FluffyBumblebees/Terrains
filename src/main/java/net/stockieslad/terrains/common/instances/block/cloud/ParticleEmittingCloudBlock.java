package net.stockieslad.terrains.common.instances.block.cloud;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleEmittingCloudBlock extends BasicCloudBlock {

    private final ParticleEffect effect;
    private final boolean heals;

    public ParticleEmittingCloudBlock(Settings properties, ParticleEffect effect, boolean heals) {
        super(properties);
        this.effect = effect;
        this.heals = heals;
    }

    @Override
    public void onEntityCollision(final BlockState state, final World world, final BlockPos pos, final Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!world.isClient() && entity instanceof LivingEntity) {
            if (world.getTime() % 20 == 0) {
                if (heals) ((LivingEntity) entity).heal(1F);
                for (int i = world.getRandom().nextInt(3); i <= 5; i++) {
                    final double offX = (world.getRandom().nextDouble() * entity.getWidth()) - (entity.getWidth() / 2);
                    final double offZ = (world.getRandom().nextDouble() * entity.getWidth()) - (entity.getWidth() / 2);
                    final double offY = world.getRandom().nextDouble() * entity.getHeight();
                    ((ServerWorld) world).spawnParticles(effect, entity.getX() + offX, entity.getY() + offY, entity.getZ() + offZ, 3, 0, 0, 0, 1);
                }
            }
            final double offX = (world.getRandom().nextDouble() * entity.getWidth()) - (entity.getWidth() / 2);
            final double offZ = (world.getRandom().nextDouble() * entity.getWidth()) - (entity.getWidth() / 2);
            final double offY = world.getRandom().nextDouble() * entity.getHeight();
            ((ServerWorld) world).spawnParticles(effect, entity.getX() + offX, entity.getY() + offY, entity.getZ() + offZ, 1, 0, 0, 0, 0.1);
        }
    }
}
