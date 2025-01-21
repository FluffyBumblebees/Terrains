package net.stockieslad.magical_utilities.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.stockieslad.magical_utilities.MagicalUtilities;

public class MUItemgroups {
    public static ItemGroup CLOUDS = FabricItemGroup.builder().displayName(Text.translatable("itemgroup.itemgroup.clouds"))
            .entries((displayContext, entries) -> {
                for (Clouds value : Clouds.values())
                    entries.add(value.item);
            })
            .icon(Clouds.IRRADIATED.item::getDefaultStack).build();

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, MagicalUtilities.getIdentifier("clouds"), CLOUDS);
    }
}
