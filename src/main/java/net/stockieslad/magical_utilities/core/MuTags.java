package net.stockieslad.magical_utilities.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

public class MuTags {
    public static final TagKey<Block> BLOCK_CLOUDS = TagKey.of(Registries.BLOCK.getKey(), getIdentifier("clouds"));
    public static final TagKey<Item> ITEM_CLOUDS = TagKey.of(Registries.ITEM.getKey(), getIdentifier("clouds"));
}
