package net.fluffybumblebee.terrains.common.world.feature.raw;

import net.fluffybumblebee.terrains.util.registration.world.feature.SaplingGeneratorContext;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MapleSaplingGenerator extends SaplingGenerator {

    private final SaplingGeneratorContext treeNoBees;
    private final SaplingGeneratorContext treeBees;

    public MapleSaplingGenerator(
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
