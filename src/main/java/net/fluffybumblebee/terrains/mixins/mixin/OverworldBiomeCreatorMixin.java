package net.fluffybumblebee.terrains.mixins.mixin;

import net.fluffybumblebee.terrains.util.MeadowDefaults;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fluffybumblebee.better_meadows.world.feature.BMPlaced.MEADOW_BUSH;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.*;

@Mixin(OverworldBiomeCreator.class)
public class OverworldBiomeCreatorMixin {

    @Shadow private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        addLandCarvers(generationSettings);
        addAmethystGeodes(generationSettings);
        addDungeons(generationSettings);
        addMineables(generationSettings);
        addSprings(generationSettings);
        addFrozenTopLayer(generationSettings);
    }

    @Inject(method = "createMeadow", at = @At("HEAD"), cancellable = true)
    private static void changeMeadow(CallbackInfoReturnable<Biome> cir) {
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1, 2)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 6)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 2, 4));
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        addBatsAndMonsters(spawnBuilder);
        addBasicFeatures(generationBuilder);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_BUSH);
        addPlainsTallGrass(generationBuilder);
        addForestFlowers(generationBuilder);
        addDefaultGrass(generationBuilder);
        addDefaultOres(generationBuilder);
        addDefaultDisks(generationBuilder);
        addMeadowFlowers(generationBuilder);
        addExtraDefaultFlowers(generationBuilder);
        addEmeraldOre(generationBuilder);
        addInfestedStone(generationBuilder);
        cir.setReturnValue(new Biome.Builder()
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.MOUNTAIN)
                .temperature(0.5F)
                .downfall(0.8F)
                .effects(MeadowDefaults.EFFECTS)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build()
        );
    }

    static {
        MeadowDefaults.ACCESS = OverworldBiomeCreatorMixin::addBasicFeatures;
    }
}
