package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.stockieslad.magical_utilities.core.Cloud;

import static net.minecraft.data.client.VariantSettings.*;
import static net.minecraft.data.client.VariantSettings.Rotation.*;

public class MuModelGenerator  extends FabricModelProvider {
    public MuModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        for (Cloud value : Cloud.values()) {
            if (value.equals(Cloud.INDIGO)) {
                Identifier vertical = TexturedModel.CUBE_COLUMN.upload(value.block, generator.modelCollector);
                Identifier horizontal = TexturedModel.CUBE_COLUMN_HORIZONTAL.upload(value.block, generator.modelCollector);
                generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(value.block).coordinate(BlockStateVariantMap.create(Properties.FACING)
                        .register(Direction.UP, BlockStateVariant.create().put(MODEL, horizontal))
                        .register(Direction.DOWN, BlockStateVariant.create().put(MODEL, horizontal).put(X, R180).put(Y, R180))
                        .register(Direction.NORTH, BlockStateVariant.create().put(MODEL, horizontal).put(X, R90))
                        .register(Direction.SOUTH, BlockStateVariant.create().put(MODEL, horizontal).put(X, R270))
                        .register(Direction.WEST, BlockStateVariant.create().put(MODEL, vertical).put(X, R270).put(Y, R90))
                        .register(Direction.EAST, BlockStateVariant.create().put(MODEL, vertical).put(X, R90).put(Y, R90)))
                );
            } else generator.registerSimpleCubeAll(value.block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
