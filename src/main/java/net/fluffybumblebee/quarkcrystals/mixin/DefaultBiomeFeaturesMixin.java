package net.fluffybumblebee.quarkcrystals.mixin;

import net.fluffybumblebee.quarkcrystals.world.gen.feature.QCDefaultBiomeFeatures;
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
            QCDefaultBiomeFeatures.addQCGeodes(generationSettings);
    }
}
