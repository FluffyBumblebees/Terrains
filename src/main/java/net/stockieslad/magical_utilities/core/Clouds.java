package net.stockieslad.magical_utilities.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.block.cloud.*;
import net.stockieslad.magical_utilities.util.BlockHelper;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

/*
 *  TODO: Redstone changes ferrous cloud's entity type, not force type
 *  TODO: Add functionality to control shape directly
 *  TODO: Fix the noise spamming to happen only when entering/leaving
 *  TODO: Tough as nails compat with dense cloud (hydration - quench thirst)
 *  TODO: Add unique worldgen (magma spawns on lava, condensed (bottom) - steam (top) over water,etc)
 *  TODO: Add in world crafting
 *  TODO: Add spawns in aether
 */
public enum Clouds {
    ENERGIZED(new RedstoneCloud()), // Lights up when entities are inside; gives redstone; Overworld; Red
    MAGMATIC(new MagmaticCloud()), // Damages entities & sets things on fire; gives 1 lava bucket, can be made with lava bucket as well; Nether; Orange
    SULFUR(new DamagingCloud()), // Damaging; Gives sulfur; Made by placing magmatic & luminous cloud together; Nether; Yellow
    FERROUS(new MagneticCloud()), // Attract entities into it; Gives iron; Overworld; Iron
    CHAOS(new RandomCloud()), //Random Direction;
    LIVING(new HealingCloud()), // Heals players slowly; Overworld; Green
    ENDER(new TeleportingCloud()), // Teleports players randomly; Gives ender pearls; Teal
    CHARGED(new SmitingCloud()), // Summons lightning when walked in; Overworld; Indigo
    GELID(new ColdCloud()), // Cold; sinks
    DENSE(new HydroCloud()), // Slows Entities
    INDIGO(new DirectionalCloud()), // Shoots entities directionally; flips when powered by redstone
    IRRADIATED(new DestructiveCloud()), // Destroys blocks randomly in a radius; Purple;
    CHERRY(new NourishingCloud()), // Slowly fills up players hunger bars; Overworld; Pink
    STEAM(new RisingCloud()); // Shoots entities up; Made by placing water and magmatic cloud together; Overworld; White

    public final Identifier identifier;
    public final BasicCloud block;
    public final BlockItem item;

    Clouds(BasicCloud block) {
        this.identifier = getIdentifier(this.name().toLowerCase() + "_cloud");
        this.block = block;
        this.item = new BlockItem(block, new FabricItemSettings());
        BlockHelper.registerBlockAndItem(identifier, block, item);
    }


    /**
     * This is a placeholder for clouds that aren't finished yet.
     */
    @SuppressWarnings("unused")
    Clouds() {
        this(new BasicCloud());
    }

    public static void init() {}
}
