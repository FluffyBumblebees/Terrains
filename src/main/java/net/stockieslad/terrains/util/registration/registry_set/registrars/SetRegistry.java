package net.stockieslad.terrains.util.registration.registry_set.registrars;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.stockieslad.terrains.util.registration.mass.SafeTriSet;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.helper.Quickerator;

import java.util.*;


public final class SetRegistry {

    public final Object2ObjectMap<RegistryTypes, List<SafeTriSet>> storage;

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

    public void triSet(final RegistryTypes type, final UnsafeTriSet<?>... unsafeTriSets) {
        for (UnsafeTriSet<?> set : unsafeTriSets) {
            storage.get(type).add(new SafeTriSet(
                    Optional.of(set.BLOCK),
                    Optional.of(set.ITEM),
                    Optional.of(set.IDENTIFIER)
            ));
        }
    }

    public void triSetPotted(final RegistryTypes type, final UnsafeTriSet<?>... unsafeTriSets) {
        for (UnsafeTriSet<?> set : unsafeTriSets) {
            storage.get(type).add(new SafeTriSet(
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

}
