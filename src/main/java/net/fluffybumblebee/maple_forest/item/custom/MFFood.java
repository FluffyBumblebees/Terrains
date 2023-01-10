package net.fluffybumblebee.maple_forest.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class MFFood extends Item {
    public MFFood(int hunger, int saturation) {
        super(new FabricItemSettings()
                .group(ItemGroup.FOOD)
                .food(
                        new FoodComponent.Builder()
                                .hunger(hunger)
                                .saturationModifier(saturation)
                                .build()
                ));
    }
}
