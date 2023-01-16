package net.fluffybumblebee.terrains.common.registry.sets.tree.component;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.terrains.common.instances.block.wood.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet.Builder;
import net.fluffybumblebee.terrains.util.registration.entity.BoatRegistration;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildFlammableBlock;

public class WoodSet implements RegistrySetCreator {
    private final List<BlockSet<?>> ALL_BLOCKS;
    public final String TYPE;

    public static TerraformBoatType BOAT;
    public final BlockSet<?> LOG;
    public final BlockSet<?> WOOD;
    public final BlockSet<?> STRIPPED_LOG;
    public final BlockSet<?> STRIPPED_WOOD;
    public final BlockSet<?> PLANKS;
    public final BlockSet<?> STAIRS;
    public final BlockSet<?> SLAB;
    public final BlockSet<?> FENCE;
    public final BlockSet<?> FENCE_GATE;
    public final BlockSet<?> PRESSURE_PLATE;
    public final BlockSet<?> DOOR;
    public final BlockSet<?> TRAPDOOR;
    public final BlockSet<?> BUTTON;

    public final BlockSet<TerraformSignBlock> SIGN;
    public final BlockSet<?> WALL_SIGN;

    public WoodSet(String woodType) {
        ALL_BLOCKS = new ArrayList<>();
        TYPE = woodType;
        BoatRegistration.register(TYPE, () -> BOAT, item -> BOAT = new TerraformBoatType.Builder().item(item).build());

        LOG = buildFlammableBlock(new WoodBlock(), TYPE + "_log");
        WOOD = buildFlammableBlock(new WoodBlock(), TYPE + "_wood");
        STRIPPED_LOG = buildFlammableBlock(new WoodBlock(), "stripped_" + TYPE + "_log");
        STRIPPED_WOOD = buildFlammableBlock(new WoodBlock(), "stripped_" + TYPE + "_wood");
        PLANKS = buildFlammableBlock(new WoodBlock(), TYPE + "_planks");
        STAIRS = buildFlammableBlock(new WoodStairs(), TYPE + "_stairs");
        SLAB = buildFlammableBlock(new WoodSlabs(), TYPE + "_slab");
        FENCE = buildFlammableBlock(new WoodFence(), TYPE + "_fence", ItemGroup.DECORATIONS);
        FENCE_GATE = buildFlammableBlock(new WoodFenceGate(), TYPE + "_fence_gate", ItemGroup.REDSTONE);
        PRESSURE_PLATE =  buildFlammableBlock(new WoodPressureplate(), TYPE + "_pressure_plate", ItemGroup.REDSTONE);
        DOOR = buildFlammableBlock(new WoodDoor(), TYPE + "_door", ItemGroup.REDSTONE);
        TRAPDOOR = buildFlammableBlock(new WoodTrapDoor(), TYPE + "_trapdoor", ItemGroup.REDSTONE);
        BUTTON = buildFlammableBlock(new WoodButton(), TYPE + "_button", ItemGroup.REDSTONE);

        Identifier SIGN_TEXTURE = getIdentifier("entity/signs/" + TYPE);
        WALL_SIGN = new Builder<>(new TerraformWallSignBlock(SIGN_TEXTURE,
                FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)), getIdentifier(TYPE + "_wall_sign")).build();
        SIGN = new Builder<>(new TerraformSignBlock(SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)),
                getIdentifier(TYPE + "_sign"))
                .addBlockItem(block -> new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), block, WALL_SIGN.BLOCK))
                .build();

        addAllBlocks(new BlockSet<?>[] {
                LOG,
                WOOD,
                STRIPPED_LOG,
                STRIPPED_WOOD,
                PLANKS,
                STAIRS,
                SLAB,
                FENCE,
                FENCE_GATE,
                PRESSURE_PLATE,
                DOOR,
                TRAPDOOR,
                BUTTON,
                SIGN
        });

        StrippableBlockRegistry.register(LOG.BLOCK, STRIPPED_LOG.BLOCK);
        StrippableBlockRegistry.register(WOOD.BLOCK, STRIPPED_WOOD.BLOCK);
    }

    @Override
    public List<Item> getAllItems() {
        return List.of(BOAT.getItem());
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }

}
