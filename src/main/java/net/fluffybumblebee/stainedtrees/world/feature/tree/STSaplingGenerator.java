package net.fluffybumblebee.stainedtrees.world.feature.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class STSaplingGenerator extends SaplingGenerator {
    private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> def;
    private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> defBees;
    private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fat;
    private final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fatBees;
    public STSaplingGenerator(
            Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> def,
            Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> defBees,
            Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fat,
            Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> fatBees
    ) {
        this.def = def;
        this.defBees = defBees;
        this.fat = fat;
        this.fatBees = fatBees;
    }
    @Nullable @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return bees ? fatBees.get() : fat.get();
        } else {
            return bees ? defBees.get() : def.get();
        }
    }
}
