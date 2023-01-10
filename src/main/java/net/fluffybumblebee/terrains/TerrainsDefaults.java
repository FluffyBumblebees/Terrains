package net.fluffybumblebee.terrains;

import net.fluffybumblebee.terrains.util.registration.block.BlockBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public final class TerrainsDefaults {
    private TerrainsDefaults() {}

    public static final String NAMESPACE = "terrains";

    public static Identifier getIdentifier(String name) {
        return new Identifier(NAMESPACE, name);
    }

    public static <B extends Block> B buildBlock(B block, String name, ItemGroup group) {
        return new BlockBuilder<>(block, getIdentifier(name)).blockItem(group).build().getBlock();
    }

    public static <B extends Block> B buildBlock(B block, String name) {
        return new BlockBuilder<>(block, getIdentifier(name)).blockItem().build().getBlock();
    }
}
