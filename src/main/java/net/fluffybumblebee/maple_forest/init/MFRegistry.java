package net.fluffybumblebee.maple_forest.init;

import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.maple_forest.blocks.custom.tree.MFLeaves;
import net.fluffybumblebee.maple_forest.blocks.custom.tree.MFSaplings;
import net.fluffybumblebee.maple_forest.blocks.custom.tree.MFWood;
import net.fluffybumblebee.maple_forest.blocks.custom.woodcraftables.*;
import net.fluffybumblebee.maple_forest.item.MFItems;
import net.fluffybumblebee.maple_forest.item.custom.MFFood;
import net.fluffybumblebee.maple_forest.util.registration.block.MFBlockRegistration;
import net.fluffybumblebee.maple_forest.util.registration.item.MFItemRegistration;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;
import net.fluffybumblebee.maple_forest.world.feature.MFConfiguredFeatures;
import net.fluffybumblebee.maple_forest.world.feature.tree.MFSaplingGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;

@SuppressWarnings({"unused"})
public class MFRegistry {
    public static void addToRegistry() {
        register();
    }
    public static final Block RED_MAPLE_LEAVES = MFBlockRegistration.register(new MFLeaves(), "red_" + MFWoodTypes.MAPLE + "_leaves");
    public static final Block ORANGE_MAPLE_LEAVES = MFBlockRegistration.register(new MFLeaves(), "orange_" + MFWoodTypes.MAPLE + "_leaves");
    public static final Block YELLOW_MAPLE_LEAVES = MFBlockRegistration.register(new MFLeaves(), "yellow_" + MFWoodTypes.MAPLE + "_leaves");
    public static final Block GREEN_MAPLE_LEAVES = MFBlockRegistration.register(new MFLeaves(), "green_" + MFWoodTypes.MAPLE + "_leaves");
    public static final Block BROWN_MAPLE_LEAVES = MFBlockRegistration.register(new MFLeaves(), "brown_" + MFWoodTypes.MAPLE + "_leaves");

    public static final SaplingBlock RED_MAPLE_SAPLING = (SaplingBlock) MFBlockRegistration.register(new MFSaplings(new MFSaplingGenerator(() -> MFConfiguredFeatures.RED_MAPLE_TREE)), "red_" + MFWoodTypes.MAPLE + "_sapling");
    public static final SaplingBlock ORANGE_MAPLE_SAPLING = (SaplingBlock) MFBlockRegistration.register(new MFSaplings(new MFSaplingGenerator(() -> MFConfiguredFeatures.ORANGE_MAPLE_TREE)), "orange_" + MFWoodTypes.MAPLE + "_sapling");
    public static final SaplingBlock YELLOW_MAPLE_SAPLING = (SaplingBlock) MFBlockRegistration.register(new MFSaplings(new MFSaplingGenerator(() -> MFConfiguredFeatures.YELLOW_MAPLE_TREE)), "yellow_" + MFWoodTypes.MAPLE + "_sapling");
    public static final SaplingBlock GREEN_MAPLE_SAPLING = (SaplingBlock) MFBlockRegistration.register(new MFSaplings(new MFSaplingGenerator(() -> MFConfiguredFeatures.GREEN_MAPLE_TREE)), "green_" + MFWoodTypes.MAPLE + "_sapling");
    public static final SaplingBlock BROWN_MAPLE_SAPLING = (SaplingBlock) MFBlockRegistration.register(new MFSaplings(new MFSaplingGenerator(() -> MFConfiguredFeatures.BROWN_MAPLE_TREE)), "brown_" + MFWoodTypes.MAPLE + "_sapling");

    public static final Item MAPLE_SAP = MFItems.register("maple_sap", new MFFood(4, 1));
    public static final Item PANCAKE_DOUGH = MFItems.register("pancake_dough", new MFFood(2, 1));
    public static final Item PANCAKE = MFItems.register("pancake", new MFFood(4, 1));
    public static final Item HONEY_FLAVOURED_PANCAKE = MFItems.register("honey_flavoured_pancake", new MFFood(6, 1));
    public static final Item MAPLE_FLAVOURED_PANCAKE = MFItems.register("maple_flavoured_pancake", new MFFood(6, 1));
    public static final Item HONEYCOMB_PANCAKE_SANDWICH = MFItems.register("honeycomb_pancake_sandwich", new MFFood(8, 1));

    public static final Block SAPPY_MAPLE_LOG = MFBlockRegistration.register(new MFWood(), "sappy_" + MFWoodTypes.MAPLE + "_log");
    public static final Block MAPLE_LOG = MFBlockRegistration.register(new MFWood(), MFWoodTypes.MAPLE + "_log");
    public static final Block MAPLE_WOOD = MFBlockRegistration.register(new MFWood(), MFWoodTypes.MAPLE + "_wood");

    public static final Block STRIPPED_MAPLE_LOG = MFBlockRegistration.register(new MFWood(), "stripped_" + MFWoodTypes.MAPLE + "_log");
    public static final Block STRIPPED_MAPLE_WOOD = MFBlockRegistration.register(new MFWood(), "stripped_" + MFWoodTypes.MAPLE + "_wood");

    public static final Block MAPLE_PLANKS = MFBlockRegistration.register(new MFWood(), MFWoodTypes.MAPLE + "_planks");
    public static final Block MAPLE_STAIRS = MFBlockRegistration.register(new MFWoodStairs(), MFWoodTypes.MAPLE + "_stairs");
    public static final Block MAPLE_SLAB = MFBlockRegistration.register(new MFWoodSlabs(), MFWoodTypes.MAPLE + "_slab");
    public static final Block MAPLE_FENCE = MFBlockRegistration.register(new MFWoodFence(), MFWoodTypes.MAPLE + "_fence");
    public static final Block MAPLE_FENCE_GATE = MFBlockRegistration.register(new MFWoodFenceGate(), MFWoodTypes.MAPLE + "_fence_gate");
    public static final Block MAPLE_PRESSURE_PLATE = MFBlockRegistration.register(new MFWoodPressureplate(), MFWoodTypes.MAPLE + "_pressure_plate");
    public static final Block MAPLE_DOOR = MFBlockRegistration.register(new MFWoodDoor(), MFWoodTypes.MAPLE + "_door");
    public static final Block MAPLE_TRAPDOOR =  MFBlockRegistration.register(new MFWoodTrapDoor(), MFWoodTypes.MAPLE + "_trapdoor");
    public static final Block MAPLE_BUTTON = MFBlockRegistration.register(new MFWoodButton(), MFWoodTypes.MAPLE + "_button");
    private static final Identifier MAPLE_SIGN_TEXTURE = new Identifier(MapleForest.NAMESPACE, "entity/signs/maple");
    public static final TerraformSignBlock MAPLE_SIGN = MFBlockRegistration.registerBlockOnly(new TerraformSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)), MFWoodTypes.MAPLE + "_sign", ItemGroup.DECORATIONS);
    public static final Block MAPLE_WALL_SIGN = MFBlockRegistration.registerBlockOnly(new TerraformWallSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)), MFWoodTypes.MAPLE + "_wall_sign", ItemGroup.DECORATIONS);
    public static final Item MAPLE_SIGN_ITEM = MFItemRegistration.register(new SignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), MAPLE_SIGN, MAPLE_WALL_SIGN), MFWoodTypes.MAPLE + "_sign");

    public static final Block POTTED_RED_MAPLE_SAPLING = MFBlockRegistration.registerBlockOnly(new FlowerPotBlock(RED_MAPLE_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)), "potted_red_" + MFWoodTypes.MAPLE + "_sapling");
    public static final Block POTTED_ORANGE_MAPLE_SAPLING = MFBlockRegistration.registerBlockOnly(new FlowerPotBlock(ORANGE_MAPLE_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)), "potted_orange_" + MFWoodTypes.MAPLE + "_sapling");
    public static final Block POTTED_YELLOW_MAPLE_SAPLING = MFBlockRegistration.registerBlockOnly(new FlowerPotBlock(YELLOW_MAPLE_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)), "potted_yellow_" + MFWoodTypes.MAPLE + "_sapling");
    public static final Block POTTED_GREEN_MAPLE_SAPLING = MFBlockRegistration.registerBlockOnly(new FlowerPotBlock(GREEN_MAPLE_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)), "potted_green_" + MFWoodTypes.MAPLE + "_sapling");
    public static final Block POTTED_BROWN_MAPLE_SAPLING = MFBlockRegistration.registerBlockOnly(new FlowerPotBlock(BROWN_MAPLE_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)), "potted_brown_" + MFWoodTypes.MAPLE + "_sapling");


    public static void register() {
        StrippableBlockRegistry.register(MAPLE_LOG, STRIPPED_MAPLE_LOG);
        StrippableBlockRegistry.register(SAPPY_MAPLE_LOG, STRIPPED_MAPLE_LOG);
        StrippableBlockRegistry.register(MAPLE_WOOD, STRIPPED_MAPLE_WOOD);
    }

}
