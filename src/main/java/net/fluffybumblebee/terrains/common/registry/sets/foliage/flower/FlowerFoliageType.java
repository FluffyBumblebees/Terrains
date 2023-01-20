package net.fluffybumblebee.terrains.common.registry.sets.foliage.flower;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.component.TypesFoliage;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;

import java.util.Arrays;

public final  class FlowerFoliageType {
    public static final TypesFoliage<FlowerBlock, FlowerTypes> ALL_FLOWERS = new TypesFoliage<>(
            Arrays.asList(FlowerTypes.values()),
            (types) -> new FlowerBlock(
                    types.EFFECT, types.EFFECT_TIME,
                    FabricBlockSettings
                            .of(Material.PLANT)
                            .breakInstantly()
                            .noCollision()
                            .sounds(BlockSoundGroup.GRASS)
            ),
            "flower",
            true
    );

    public enum FlowerTypes {
        LAVENDER(StatusEffects.REGENERATION, 3);




        private final StatusEffect EFFECT;
        private final int EFFECT_TIME;

        FlowerTypes(StatusEffect effect, int effectTime) {
            EFFECT = effect;
            EFFECT_TIME = effectTime;
        }
    }
}
