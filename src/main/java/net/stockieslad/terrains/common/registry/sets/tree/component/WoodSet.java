package net.stockieslad.terrains.common.registry.sets.tree.component;


import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.feltmc.abstractium.library.common.entity.SignCollection;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.wood.*;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.stockieslad.terrains.util.registration.mass.SafeTriSet;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;

import java.util.List;

import static net.feltmc.abstractium.init.AbstractiumCommon.COMMON_ABSTRACTION_HANDLER;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;
import static net.stockieslad.terrains.util.registration.mass.UnsafeTriSet.buildFlammableBlock;

public final class WoodSet implements RegistrySetCreator {
    public final String TYPE;
    public final List<UnsafeTriSet<?>> ALL_BLOCKS;

    public Item BOAT;

    public final UnsafeTriSet<?> LOG;
    public final UnsafeTriSet<?> WOOD;
    public final UnsafeTriSet<?> STRIPPED_LOG;
    public final UnsafeTriSet<?> STRIPPED_WOOD;
    public final UnsafeTriSet<?> PLANKS;
    public final UnsafeTriSet<?> STAIRS;
    public final UnsafeTriSet<?> SLAB;
    public final UnsafeTriSet<?> FENCE;
    public final UnsafeTriSet<?> FENCE_GATE;
    public final UnsafeTriSet<?> PRESSURE_PLATE;
    public final UnsafeTriSet<?> DOOR;
    public final UnsafeTriSet<?> TRAPDOOR;
    public final UnsafeTriSet<?> BUTTON;

    public final SignCollection SIGNS;
    /*public final UnsafeTriSet<TerraformSignBlock> SIGN;
    public final UnsafeTriSet<?> WALL_SIGN;*/

    public WoodSet(final String woodType) {
        final var abstraction = COMMON_ABSTRACTION_HANDLER.abstraction;
        TYPE = woodType;

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

        SIGNS = abstraction.getRegistrar().registerSign(
                getIdentifier("entity/signs/" + TYPE),
                TerrainsDefaults.NAMESPACE,
                TYPE
        );

        /*Identifier SIGN_TEXTURE = getIdentifier("entity/signs/" + TYPE);
        WALL_SIGN = new Builder<>(new TerraformWallSignBlock(SIGN_TEXTURE,
                FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)), getIdentifier(TYPE + "_wall_sign")).build();
        SIGN = new Builder<>(new TerraformSignBlock(SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)),
                getIdentifier(TYPE + "_sign"))
                .addBlockItem(block -> new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), block, WALL_SIGN.BLOCK))
                .build();*/

        BOAT = abstraction.getRegistrar().registerBoat(
                new Identifier(TerrainsDefaults.NAMESPACE, TYPE + "_boat"),
                new Identifier(TerrainsDefaults.NAMESPACE, TYPE)
        );

        ALL_BLOCKS = List.of(
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
                //SIGN
                UnsafeTriSet.of(SIGNS.signs.get(SignCollection.State.STANDING), getIdentifier(TYPE + "_sign"))
        );

        StrippableBlockRegistry.register(LOG.BLOCK, STRIPPED_LOG.BLOCK);
        StrippableBlockRegistry.register(WOOD.BLOCK, STRIPPED_WOOD.BLOCK);
    }

    @Override
    public void registryEvent(final SetRegistry registry) {
        registry.triSet(RegistryTypes.WOOD_WITH_CUTOUT,
                DOOR,
                TRAPDOOR
        );

        registry.triSet(RegistryTypes.ALL_WOOD_BLOCKS,
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
                UnsafeTriSet.of(SIGNS.signs.get(SignCollection.State.STANDING), getIdentifier(TYPE + "_sign"))
        );

        registry.triSet(RegistryTypes.WOOD,
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
                BUTTON,
                UnsafeTriSet.of(SIGNS.signs.get(SignCollection.State.STANDING), getIdentifier(TYPE + "_sign"))
        );

        registry.storage.get(RegistryTypes.WOOD).add(new SafeTriSet(
                BOAT
                //BOAT.getItem()
        ));

        registry.storage.get(RegistryTypes.ALL_WOOD_BLOCKS).add(new SafeTriSet(
                BOAT
                //BOAT.getItem()
        ));
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of();
    }

    @Override
    public String toString() {
        return "WoodSet[Type=" + TYPE.toUpperCase() + "]";
    }
}
