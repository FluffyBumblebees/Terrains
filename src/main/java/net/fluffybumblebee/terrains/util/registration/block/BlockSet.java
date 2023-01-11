package net.fluffybumblebee.terrains.util.registration.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

public class BlockSet<B extends Block> {
    public final B block;
    public final BlockItem blockItem;
    public final Identifier identifier;

    private BlockSet(B block, BlockItem blockItem, Identifier identifier) {
        this.block = block;
        this.blockItem = blockItem;
        this.identifier = identifier;
    }

    public static <B extends Block> BlockSet<B> buildBlock(B block, String name) {
        return new Builder<>(block, getIdentifier(name)).addBlockItem().build();
    }

    @Override
    public String toString() {
        return identifier.toString();
    }

    public static final class Builder<B extends Block> {

        private final Identifier identifier;
        private final B block;
        private BlockItem blockItem;

        public Builder(B block, Identifier identifier) {
            this.identifier = identifier;
            this.block = block;
            blockItem = null;
        }

        private Builder<B> getInstanceWithSideEffect(InstanceReturnable instanceReturnable) {
            instanceReturnable.sideEffect();
            return this;
        }

        @SuppressWarnings("unused")
        public Builder<B> addFlammability(int burn, int spread) {
            return getInstanceWithSideEffect(() -> FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread));
        }

        public Builder<B> addBlockItem(ItemGroup group) {
            return getInstanceWithSideEffect(() -> blockItem = new BlockItem(block, new Item.Settings().group(group)));
        }

        public Builder<B> addBlockItem() {
            return addBlockItem(ItemGroup.DECORATIONS);
        }

        public BlockSet<B> build() {
            Registry.register(Registry.BLOCK, identifier, block);
            if (blockItem != null)
                Registry.register(Registry.ITEM, identifier, blockItem);
            return new BlockSet<>(block, blockItem, identifier);
        }

        private interface InstanceReturnable {
            void sideEffect();
        }
    }
}
