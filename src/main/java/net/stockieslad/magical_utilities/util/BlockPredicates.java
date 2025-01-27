package net.stockieslad.magical_utilities.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@SuppressWarnings("unused")
public class BlockPredicates {
    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
    public static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
