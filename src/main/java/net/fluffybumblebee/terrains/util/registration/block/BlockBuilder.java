package net.fluffybumblebee.terrains.util.registration.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class BlockBuilder <B extends Block> {

    private boolean isRegistered;
    private final B block;
    private final Identifier identifier;
    private BlockItem blockItem;

    public BlockBuilder(B block, Identifier identifier) {
        this.block = block;
        this.identifier = identifier;
        isRegistered = false;
        blockItem = null;
    }

    private BlockBuilder<B> instance(InstanceReturnable instanceReturnable) {
        instanceReturnable.sideEffect();
        return this;
    }

    public BlockBuilder<B> flammable(int burn, int spread) {
        return instance(() -> FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread));
    }

    public BlockBuilder<B> blockItem(ItemGroup group) {
        return instance(() -> blockItem = new BlockItem(block, new Item.Settings().group(group)));
    }

    public BlockBuilder<B> blockItem() {
        return blockItem(ItemGroup.DECORATIONS);
    }

    public BlockBuilder<B> build() {
        Registry.register(Registry.BLOCK, identifier, block);
        isRegistered = true;
        if (blockItem != null)
            Registry.register(Registry.ITEM, identifier, blockItem);
        return this;
    }

    public B getBlock() {
        if (isRegistered)
            return block;
        else throw new RuntimeException("Called getBlock() without registering it first! Register it first to avoid this error.");
    }

    public BlockItem getBlockItem() {
        if (blockItem != null)
            return blockItem;
        else throw new RuntimeException("Tried to get a blockItem from " + this + " but it wasn't registered!");
    }

    @Override
    public String toString() {
        return identifier.toString();
    }

    private interface InstanceReturnable {
        void sideEffect();
    }
}
