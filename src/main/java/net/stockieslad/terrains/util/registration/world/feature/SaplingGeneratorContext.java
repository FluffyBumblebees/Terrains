package net.stockieslad.terrains.util.registration.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public interface SaplingGeneratorContext {
    RegistryEntry<? extends ConfiguredFeature<?, ?>> getTree(String context);

}
