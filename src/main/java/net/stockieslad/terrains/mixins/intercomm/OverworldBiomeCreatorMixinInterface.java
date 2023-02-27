package net.stockieslad.terrains.mixins.intercomm;

import net.minecraft.world.biome.GenerationSettings;

public interface OverworldBiomeCreatorMixinInterface {
    void basicFeatures(GenerationSettings.Builder builder);
}
