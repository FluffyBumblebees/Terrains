package net.fluffybumblebee.terrains.util.registration.feature_set;

import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.fluffybumblebee.terrains.util.registration.feature_set.EasyIf.onIf;

public interface AbstractRegistrySet<Types, RegistryConfig extends RegistrySetCreator> extends Quickerator<BlockSet<?>> {
    Quickerator<Types> getIterator();

    Map<Types, RegistryConfig> getTypeMap();

    Quickerator<Item> getItemIterator();

    default void addAllToStack(List<ItemStack> stacks, boolean itemConditions,
                               boolean blockConditions, @Nullable Access access) {
        final BooleanHolder booleanHolder = new BooleanHolder(true);
        forEach(element -> onIf(blockConditions, () -> {
            if (access != null) {
                access.invoke(element, booleanHolder, getItemIterator(), itemConditions, blockConditions);
            } else {
                getItemIterator().forEach(variant -> onIf(itemConditions, () -> stacks.add(variant.getDefaultStack())));
            }
            stacks.add(element.ITEM.getDefaultStack());
        }));
    }

    @SuppressWarnings("unused")
    default void addAllToStack(List<ItemStack> stacks) {
        addAllToStack(stacks, true, true, null);
    }

    default List<BlockSet<?>> getAllRegistryEntries() {
        List<BlockSet<?>> list = new ArrayList<>();
        getIterator().forEach(colour -> list.addAll(getTypeMap().get(colour).getAllBlockSets()));
        return list;
    }

    @Override
    default List<BlockSet<?>> getValues() {
        return getAllRegistryEntries();
    }

    final class BooleanHolder {
        public boolean bool;

        private BooleanHolder(boolean bool) {
            this.bool = bool;
        }
    }

    interface Access {
        void invoke(BlockSet<?> element,
                    BooleanHolder booleanHolder,
                    Quickerator<Item> iterator,
                    boolean itemConditions,
                    boolean blockConditions);
    }
}
