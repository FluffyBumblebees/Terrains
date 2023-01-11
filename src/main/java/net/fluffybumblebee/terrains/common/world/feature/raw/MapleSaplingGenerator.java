package net.fluffybumblebee.terrains.common.world.feature.raw;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class MapleSaplingGenerator extends SaplingGenerator {

    private final Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> treeType;
    public MapleSaplingGenerator(Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> treeType) {
        this.treeType = treeType;
    }
    @Nullable @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return treeType.get();
    }
}
