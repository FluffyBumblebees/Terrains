package net.stockieslad.magical_utilities.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.stockieslad.magical_utilities.block.cloud.*;
import net.stockieslad.magical_utilities.util.BlockHelper;

import java.util.Arrays;
import java.util.List;

import static net.stockieslad.magical_utilities.MagicalUtilities.getIdentifier;

/*
 *  TODO: Add functionality to control shape directly
 *  TODO: Fix the noise spamming to happen only when entering/leaving
 *  TODO: Tough as nails compat with dense cloud (hydration - quench thirst)
 *  TODO: Add unique worldgen (magma spawns on lava, condensed (bottom) - steam (top) over water,etc)
 *  TODO: Add spawns in aether
 */
public enum Clouds {
    ENERGIZED(new RedstoneCloud()), // spawned
    MAGMATIC(new MagmaticCloud()), // spawned
    SULFUR(new DamagingCloud()), // spawned
    FERROUS(new MagneticCloud()), // spawned
    CHAOS(new RandomCloud()),
    LIVING(new HealingCloud()), //spawned
    ENDER(new TeleportingCloud()),
    CHARGED(new SmitingCloud()),
    GELID(new ColdCloud()),
    DENSE(new HydroCloud()), // spawned
    INDIGO(new DirectionalCloud()),
    IRRADIATED(new DestructiveCloud()),
    CHERRY(new NourishingCloud()),
    STEAM(new RisingCloud()); // spawned

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

    public static List<? extends Block> blocks(Clouds... clouds) {
        return Arrays.stream(clouds).map(cloud -> cloud.block).toList();
    }
}
