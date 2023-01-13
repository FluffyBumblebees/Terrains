package net.fluffybumblebee.terrains.common.instances.item.food;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FoodItem extends Item {
    public FoodItem(int hunger, float saturation) {
        super(new FabricItemSettings()
                .group(ItemGroup.FOOD)
                .food(
                        new FoodComponent.Builder()
                                .hunger(hunger)
                                .saturationModifier(saturation)
                                .build()
                ));
    }
    public FoodItem(int hunger) {
        this(hunger, 0.5F);
    }
}
