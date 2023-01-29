package net.fluffybumblebee.terrains.util.registration.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fluffybumblebee.terrains.common.instances.block.wood.WoodBlock;
import net.fluffybumblebee.terrains.core.TerrainsDefaults;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.item.ItemGroupUtil.addGroupEntry;

public final class TriSet<B extends Block> {
    public final B BLOCK;
    public final Item ITEM;
    public final Identifier IDENTIFIER;

    private TriSet(B block, Item item, Identifier identifier) {
        BLOCK = block;
        ITEM = item;
        IDENTIFIER = identifier;
    }

    public static <B extends Block> TriSet<B> of(B block, Identifier identifier) {
        return new TriSet<>(block, block.asItem(), identifier);
    }

    public static <B extends Block> TriSet<B> of(Item item, Identifier identifier) {
        return new TriSet<>(null, item, identifier);
    }


    public static <B extends Block> TriSet<B> buildBlock(B block, String name) {
        return new Builder<>(block, getIdentifier(name)).addBlockItem().build();
    }

    public static <B extends Block> TriSet<B> buildBlock(B block, String name, ItemGroup group) {
        return new Builder<>(block, getIdentifier(name)).addBlockItem(group).build();
    }

    public static <B extends Block> TriSet<B> buildFlammableBlock(
            final B block, final String name, final ItemGroup itemGroup) {
        TriSet.Builder<B> builder = new TriSet.Builder<>(block, TerrainsDefaults.getIdentifier(name));
        if (block instanceof LeavesBlock) {
            builder.addFlammability(30, 60);
        }
        if (block instanceof WoodBlock) {
            builder.addFlammability(5,5);
        }
        if (    block instanceof FenceBlock ||
                block instanceof FenceGateBlock ||
                block instanceof SlabBlock ||
                block instanceof StairsBlock
        ) {
            builder.addFlammability(5, 20);
            if (block instanceof FenceBlock || block instanceof FenceGateBlock) {
                builder.addBurnable(300);
            }
        }
        builder.addBlockItem(itemGroup);
        return builder.build();
    }

    public static <B extends Block> TriSet<B> buildFlammableBlock(final B block, final String name) {
        return buildFlammableBlock(block, name, ItemGroups.BUILDING_BLOCKS);
    }

    @Override
    public String toString() {
        return IDENTIFIER.toString();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder<B extends Block> {

        private final Identifier identifier;
        private final B block;
        private BlockItem blockItem;

        public Builder(B block, Identifier identifier) {
            this.identifier = identifier;
            this.block = block;
            blockItem = null;
        }

        public Builder(B block, String identifier) {
            this(block, TerrainsDefaults.getIdentifier(identifier));
        }

        private Builder<B> getInstanceWithSideEffect(InstanceReturnable instanceReturnable) {
            instanceReturnable.sideEffect();
            return this;
        }

        public Builder<B> addFlammability(int burn, int spread) {
            return getInstanceWithSideEffect(() -> FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread));
        }

        public Builder<B> addBurnable(int time) {
            return getInstanceWithSideEffect(() -> FuelRegistry.INSTANCE.add(block, time));
        }

        public Builder<B> addBlockItem(BlockProvider provider) {
            return getInstanceWithSideEffect(() -> blockItem = provider.getItem(block));
        }

        public Builder<B> addBlockItem(Item.Settings settings) {
            return getInstanceWithSideEffect(() -> addBlockItem(BLOCK -> new BlockItem(BLOCK, settings)));
        }

        public Builder<B> addBlockItem(ItemGroup group) {
            var triSet = addBlockItem(new Item.Settings());
            addGroupEntry(triSet.blockItem, group);
            return addBlockItem(new Item.Settings());
        }

        public Builder<B> addBlockItem() {
            return addBlockItem(ItemGroups.NATURAL);
        }

        public TriSet<B> build() {
            Registry.register(Registries.BLOCK, identifier, block);
            if (blockItem != null)
                Registry.register(Registries.ITEM, identifier, blockItem);
            return new TriSet<>(block, blockItem, identifier);
        }

        @Override
        public String toString() {
            throw new RuntimeException("toString() called whilst building A BlockSet. Please avoid doing this!");
        }

        private interface InstanceReturnable {
            void sideEffect();
        }
    }

    public interface BlockProvider {
        BlockItem getItem(Block block);
    }
}
