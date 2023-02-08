package net.fluffybumblebee.terrains.util.registration.mass;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;

@SuppressWarnings("unused")
public record SafeTriSet(
        Optional<Block> block,
        Optional<Item> item,
        Optional<Identifier> identifier
) {
    public SafeTriSet(final Block block, final Item item, final Identifier identifier) {
        this(Optional.of(block), Optional.of(item), Optional.of(identifier));
    }

    public SafeTriSet(final Block block) {
        this(Optional.of(block), Optional.empty(), Optional.empty());
    }

    public SafeTriSet(final Block block, final Item item) {
        this(Optional.of(block), Optional.of(item), Optional.empty());
    }

    public SafeTriSet(final Item item) {
        this(Optional.empty(), Optional.of(item), Optional.empty());
    }

    public SafeTriSet(final Item item, final Identifier identifier) {
        this(Optional.empty(), Optional.of(item), Optional.of(identifier));
    }

    public SafeTriSet(final Identifier identifier) {
        this(Optional.empty(), Optional.empty(), Optional.of(identifier));
    }

    public SafeTriSet(final Block block, final Identifier identifier) {
        this(Optional.of(block), Optional.empty(), Optional.of(identifier));
    }
}