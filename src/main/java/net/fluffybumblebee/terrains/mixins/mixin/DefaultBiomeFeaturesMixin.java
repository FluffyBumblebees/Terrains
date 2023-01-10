package net.fluffybumblebee.terrains.mixins.mixin;

import net.fluffybumblebee.terrains.common.registry.category.GeodeCollections;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addAmethystGeodes", at = @At("HEAD"))
    private static void addQCGeneration(GenerationSettings.Builder generationSettings, CallbackInfo ci) {
        GeodeCollections.generateGeodes(generationSettings);
    }
}
