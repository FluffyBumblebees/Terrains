package net.fluffybumblebee.terrains.common.registry.sets.tree.component;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fluffybumblebee.terrains.common.instances.block.wood_set.*;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet.Builder;
import net.fluffybumblebee.terrains.util.registration.entity.BoatRegistration;
import net.fluffybumblebee.terrains.util.registration.feature_set.FeatureRegistrar;
import net.fluffybumblebee.terrains.util.type.wood.MFWoodTypes;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.BlockSet.buildFlammableBlock;

public class WoodSetConfig <E extends Enum<?>> implements FeatureRegistrar<BlockSet<?>> {
    private final BlockSet<?>[] ALL_BLOCKS;
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

    public final BlockSet<?> SIGN;
    public final BlockSet<?> WALL_SIGN;

    public WoodSetConfig(E woodType) {
        final String type = woodType.name();
        new BoatRegistration(MFWoodTypes.MAPLE, BOAT);

        LOG = buildFlammableBlock(new WoodBlock(), type + "_log");
        WOOD = buildFlammableBlock(new WoodBlock(), type + "_wood");
        STRIPPED_LOG = buildFlammableBlock(new WoodBlock(), "stripped_" + type + "_log");
        STRIPPED_WOOD = buildFlammableBlock(new WoodBlock(), "stripped_" + type + "_wood");
        PLANKS = buildFlammableBlock(new WoodBlock(), type + "_planks");
        STAIRS = buildFlammableBlock(new WoodStairs(), type + "_stairs");
        SLAB = buildFlammableBlock(new WoodSlabs(), type + "_slab");
        FENCE = buildFlammableBlock(new WoodFence(), type + "_fence");
        FENCE_GATE = buildFlammableBlock(new WoodFenceGate(), type + "_fence_gate");
        PRESSURE_PLATE =  buildFlammableBlock(new WoodPressureplate(), type + "_pressure_plate");
        DOOR = buildFlammableBlock(new WoodDoor(), type + "_door");
        TRAPDOOR = buildFlammableBlock(new WoodTrapDoor(), type + "_trapdoor");
        BUTTON = buildFlammableBlock(new WoodButton(), type + "_button");

        Identifier MAPLE_SIGN_TEXTURE = getIdentifier("entity/signs/maple");
        WALL_SIGN = new Builder<>(new TerraformWallSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)), getIdentifier(type + "_wall_sign")).build();
        SIGN = new Builder<>(new TerraformSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)), getIdentifier(type + "_sign"))
                .addBlockItem(block -> new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), block, WALL_SIGN.BLOCK))
                .build();

        ALL_BLOCKS = new BlockSet<?>[] {
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
        };
    }


    @Override
    public BlockSet<?>[] getAll() {
        return ALL_BLOCKS;
    }
}
