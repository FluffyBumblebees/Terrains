package net.fluffybumblebee.terrains.util.registration.registry_set.registrars;

import net.fluffybumblebee.terrains.util.registration.block.TriSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.*;


public final class SetRegistry {

    public final HashMap<RegistryTypes, List<Storage>> storage;

    @SuppressWarnings("unused")
    public SetRegistry(RegistryTypes[] allTypes) {
        this(Arrays.asList(allTypes));
    }

    public SetRegistry(List<RegistryTypes> allTypes) {
        storage = new HashMap<>();
        for (RegistryTypes type : allTypes) {
            storage.put(type, new ArrayList<>());
        }
    }

    public void massItems(List<RegistryTypes> types, List<Item> items) {
        for (RegistryTypes type: types)
            for (Item item : items) {
                storage.get(type).add(new Storage(item));
            }
    }

    @SuppressWarnings("unused")
    public void massBlocks(List<RegistryTypes> types, List<Block> blocks) {
        for (RegistryTypes type: types)
            for (Block block : blocks) {
                storage.get(type).add(new Storage(block));
            }
    }

    public void blockSet(RegistryTypes type, TriSet<?>... triSets) {
        for (TriSet<?> set : triSets) {
            storage.get(type).add(new Storage(
                    Optional.of(set.BLOCK),
                    Optional.of(set.ITEM),
                    Optional.of(set.IDENTIFIER)
            ));
        }
    }

    public void blockSetPotted(RegistryTypes type, TriSet<?>... triSets) {
        for (TriSet<?> set : triSets) {
            storage.get(type).add(new Storage(
                    set.BLOCK
            ));
        }
    }

    public <T> Quickerator<T> iterate(List<T> list) {
        return () -> list;
    }

    public <T> Quickerator<T> iterate(T[] array) {
        return iterate(Arrays.asList(array));
    }

    @SuppressWarnings("unused")
    public record Storage(
            Optional<Block> block,
            Optional<Item> item,
            Optional<Identifier> identifier
    ) {
        public Storage(Block block, Item item, Identifier identifier) {
            this(Optional.of(block), Optional.of(item), Optional.of(identifier));
        }

        public Storage(Block block) {
            this(Optional.of(block), Optional.empty(), Optional.empty());
        }

        public Storage(Block block, Item item) {
            this(Optional.of(block), Optional.of(item), Optional.empty());
        }

        public Storage(Item item) {
            this(Optional.empty(), Optional.of(item), Optional.empty());
        }

        public Storage(Item item, Identifier identifier) {
            this(Optional.empty(), Optional.of(item), Optional.of(identifier));
        }

        public Storage(Identifier identifier) {
            this (Optional.empty(), Optional.empty(), Optional.of(identifier));
        }

        public Storage(Block block, Identifier identifier) {
            this (Optional.of(block), Optional.empty(), Optional.of(identifier));
        }
    }
}
