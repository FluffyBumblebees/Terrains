package net.fluffybumblebee.terrains.common.registry.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fluffybumblebee.terrains.common.registry.CrystalGeodes;
import net.fluffybumblebee.terrains.util.IdentifierUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class CrystalItemGroup {
    public static void register() {}
    public static final ItemGroup CRYSTALS;

    static {
        CRYSTALS = FabricItemGroupBuilder.create(new Identifier(IdentifierUtil.ID, IdentifierUtil.ID))
                .icon(() -> new ItemStack(CrystalGeodes.allGeodes.get("purple").corundumBlock.getBlockItem().asItem()))
                .appendItems(stacks -> {
                    for (Item item : CrystalGeodes.getAllItems()) {
                        stacks.add(item.getDefaultStack());
                    }
                }).build();
    }
}
