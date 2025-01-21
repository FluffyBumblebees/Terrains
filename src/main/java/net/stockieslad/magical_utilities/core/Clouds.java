package net.stockieslad.magical_utilities.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.block.cloud.*;
import net.stockieslad.magical_utilities.util.BlockHelper;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

/*
    I'll finish this first before adding any other content or worldgen.
 */
public enum Clouds {
    CHARGED, // Summons lightning when walked in; Overworld; Indigo
    CHERRY, // Slowly fills up players hunger bars; Overworld; Pink
    CONDENSED(new LiquidCloud(Items.WATER_BUCKET)), // Stops fall damage; sinks; Gives water; Overworld; Blue
    ENDER, // Teleports players randomly; Gives ender pearls; Teal
    ENERGIZED(new RedstoneCloud()), // Lights up when entities are inside; gives redstone; Overworld; Red
    FERROUS, // Attract entities into it; Gives iron; Overworld; Iron
    IRRADIATED(new DestructionCloud()), // Destroys blocks randomly in a radius; Purple;
    MAGMATIC(new MagmaticCloud(Items.LAVA_BUCKET)), // Damages entities & sets things on fire; gives 1 lava bucket, can be made with lava bucket as well; Nether; Orange
    ORGANIC(new HealingCloud()), // Heals players slowly; Overworld; Green
    SLIME(new BouncyCloud()), //Bounces entities; NOT DONE
    STEAM(new RisingCloud()), // Shoots entities up; Made by placing water and magmatic cloud together; Overworld; White
    DIMENSIONAL, // Shoots entities directionally; flips when powered by redstone
    SULFUR(new DamagingCloud()); // Damaging; Gives sulfur; Made by placing magmatic & luminous cloud together; Nether; Yellow

    public final Identifier identifier;
    public final BasicCloud block;
    public final BlockItem item;

    Clouds(BasicCloud block) {
        this.identifier = getIdentifier(this.name().toLowerCase() + "_cloud");
        this.block = block;
        this.item = new BlockItem(block, new FabricItemSettings());
        BlockHelper.registerBlockAndItem(identifier, block, item);
    }

    Clouds() {
        this(new BasicCloud());
    }

    public static void init() {}
}
