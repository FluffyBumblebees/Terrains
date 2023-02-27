package net.stockieslad.terrains.common.world.inbuilt_structures.features.component;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.stockieslad.terrains.util.registration.world.feature.SaplingGeneratorContext;
import org.jetbrains.annotations.Nullable;


public class StandardSaplingGenerator extends SaplingGenerator {

    private final SaplingGeneratorContext treeNoBees;
    private final SaplingGeneratorContext treeBees;

    public StandardSaplingGenerator(
            SaplingGeneratorContext treeNoBees,
            SaplingGeneratorContext treeBees
    ) {
        this.treeNoBees = treeNoBees;
        this.treeBees = treeBees;
    }
    @Nullable @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? treeBees.getTree("bees") : treeNoBees.getTree("no_bees");
    }

}
