package net.fluffybumblebee.maple_forest.util.registration.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.maple_forest.blocks.custom.tree.MFSaplings;
import net.fluffybumblebee.maple_forest.blocks.custom.tree.MFWood;
import net.fluffybumblebee.maple_forest.init.MapleForest;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;
import net.fluffybumblebee.maple_forest.world.feature.tree.MFSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MFBlockRegistration {
    public static <B extends Block> B registerBlockOnly(B block, String name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, new Identifier(MapleForest.NAMESPACE, name), block);
        return block;
    }
    public static <B extends Block> B registerBlockOnly(B block, String name) {
        if (block instanceof FlowerPotBlock) {
            return registerBlockOnly(block, name, ItemGroup.DECORATIONS);
        } return registerBlockOnly(block, name, ItemGroup.BUILDING_BLOCKS);
    }
    public static Block register(Block block, String name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, new Identifier(MapleForest.NAMESPACE, name), block);
        Registry.register(Registry.ITEM, new Identifier(MapleForest.NAMESPACE, name), new BlockItem(block, new Item.Settings().group(itemGroup)));
        if (block instanceof LeavesBlock) {
            addFlammability(block, 30, 60, flammableRegistry);
        }
        if (block instanceof MFWood) {
            addFlammability(block, 5, 5, flammableRegistry);
        }
        if (    block instanceof FenceBlock ||
                block instanceof FenceGateBlock ||
                block instanceof SlabBlock ||
                block instanceof StairsBlock

        ) {
            addFlammability(block, 5, 20, flammableRegistry);
            if (block instanceof FenceBlock || block instanceof FenceGateBlock) {
                addFuels(block, 300, fuelRegistry);
            }
        }
        return block;
    }

    public static Block register(Block block, String name) {
        if (block instanceof LeavesBlock) {
            return register(block, name, ItemGroup.DECORATIONS);
        }
        if (block instanceof MFWood ||
                block instanceof FenceBlock ||
                block instanceof SlabBlock ||
                block instanceof StairsBlock
        ) {
            return register(block, name, ItemGroup.BUILDING_BLOCKS);
        } else if (block instanceof WoodenButtonBlock ||
                block instanceof DoorBlock ||
                block instanceof FenceGateBlock ||
                block instanceof PressurePlateBlock ||
                block instanceof TrapdoorBlock
        ) {
            return register(block, name, ItemGroup.REDSTONE);
        }
        return register(block, name, ItemGroup.MISC);
    }
    public static SaplingBlock registerSapling(
            RegistryEntry<? extends ConfiguredFeature<?, ?>> tree,
            String colour
    ) {
        return (SaplingBlock) register(
                new MFSaplings(
                        new MFSaplingGenerator(
                                () -> tree
                        )
                ),
                colour + "_" + MFWoodTypes.MAPLE + "_sapling");
    }
    public static void registerAndAddStripped(Block blockIn, Block blockOut, String type) {
        register(blockIn, type + "_log");
        register(blockOut,  "stripped_" + type + "_log");
        StrippableBlockRegistry.register(blockIn, blockOut);
    }

    private static final FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
    public static void addFlammability(Block block, int burn, int spread, FlammableBlockRegistry instance) {
        instance.add(block, burn, spread);
    }
    private static final FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
    private static <B extends Block> void addFuels(B block, int value, FuelRegistry fuelRegistry) {
        fuelRegistry.add(block, 300);
    }
}