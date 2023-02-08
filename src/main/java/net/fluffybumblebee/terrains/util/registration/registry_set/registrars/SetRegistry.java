package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.fluffybumblebee.terrains.util.registration.block.TriSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.*;


public final class SetRegistry {

    public final Object2ObjectMap<RegistryTypes, List<Storage>> storage;

    @SuppressWarnings("unused")
    public SetRegistry(final RegistryTypes[] allTypes) {
        this(Arrays.asList(allTypes));
    }

    public SetRegistry(final List<RegistryTypes> allTypes) {
        storage = new Object2ObjectLinkedOpenHashMap<>();
        for (RegistryTypes type : allTypes) {
            storage.put(type, new ArrayList<>());
        }
    }

    public void triSet(final RegistryTypes type, final TriSet<?>... triSets) {
        for (TriSet<?> set : triSets) {
            storage.get(type).add(new Storage(
                    Optional.of(set.BLOCK),
                    Optional.of(set.ITEM),
                    Optional.of(set.IDENTIFIER)
            ));
        }
    }

    public void triSetPotted(final RegistryTypes type, final TriSet<?>... triSets) {
        for (TriSet<?> set : triSets) {
            storage.get(type).add(new Storage(
                    set.BLOCK
            ));
        }
    }

    public <T> Quickerator<T> iterate(final List<T> list) {
        return () -> list;
    }

    public <T> Quickerator<T> iterate(final T[] array) {
        return iterate(Arrays.asList(array));
    }

    @SuppressWarnings("unused")
    public record Storage(
            Optional<Block> block,
            Optional<Item> item,
            Optional<Identifier> identifier
    ) {
        public Storage(final Block block, final Item item, final Identifier identifier) {
            this(Optional.of(block), Optional.of(item), Optional.of(identifier));
        }

        public Storage(final Block block) {
            this(Optional.of(block), Optional.empty(), Optional.empty());
        }

        public Storage(final Block block, final Item item) {
            this(Optional.of(block), Optional.of(item), Optional.empty());
        }

        public Storage(final Item item) {
            this(Optional.empty(), Optional.of(item), Optional.empty());
        }

        public Storage(final Item item, final Identifier identifier) {
            this(Optional.empty(), Optional.of(item), Optional.of(identifier));
        }

        public Storage(final Identifier identifier) {
            this (Optional.empty(), Optional.empty(), Optional.of(identifier));
        }

        public Storage(final Block block, final Identifier identifier) {
            this (Optional.of(block), Optional.empty(), Optional.of(identifier));
        }
    }
}
