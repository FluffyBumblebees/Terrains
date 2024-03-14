package net.stockieslad.terrains.common.registry.sets.tree.component;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.wood.WoodBlock;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureCreator;
import net.stockieslad.terrains.common.registry.sets.tree.component.PrimitiveTreeSet.FeatureSupplier;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.helper.Quickerator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WholeTreeSet<
        Generator extends SaplingGenerator,
        FeatureProvider extends FeatureCreator<Generator>,
        UniqueFeatures>
        implements RegistrySetCreator {

    public final HashMap<String, PrimitiveTreeSet<Generator, FeatureProvider>> PRIMITIVE_TREE_CONFIGS;
    public final UniqueFeatures UNIQUE_FEATURES;

    public final String WOOD_TYPE;
    public final String[] TREE_VARIANTS;
    public final List<UnsafeTriSet<?>> LOG_VARIANTS;
    public final WoodSet WOOD_SET;

    public WholeTreeSet(
            @NotNull final TreeType<Generator, FeatureProvider, UniqueFeatures> config
    ) {
        WOOD_TYPE = config.woodType;
        TREE_VARIANTS = config.treeVariants;
        PRIMITIVE_TREE_CONFIGS = new HashMap<>();
        WOOD_SET = new WoodSet(WOOD_TYPE);
        LOG_VARIANTS = new ArrayList<>();

        final var logVariants = config.additionalLogVariants;
        if (logVariants != null) {
            for (String logVariant : logVariants) {
                LOG_VARIANTS.add(UnsafeTriSet.buildFlammableBlock(new WoodBlock(),
                        logVariant));
            }
        }

        final List<Block> logs = new ArrayList<>();
        logs.add(WOOD_SET.LOG.BLOCK);
        for (UnsafeTriSet<?> log : LOG_VARIANTS) {
            logs.add(log.BLOCK);
        }

        final List<Block> leaves = new ArrayList<>();
        for (String treeVariant : config.treeVariants) {
            PRIMITIVE_TREE_CONFIGS.put(treeVariant, new PrimitiveTreeSet<>(
                    new PrimitiveTreeSet.Config<>(
                            treeVariant + "_" + WOOD_TYPE ,
                            logs,
                            config.featureSupplier
                    )
            ));
            leaves.add(PRIMITIVE_TREE_CONFIGS.get(treeVariant).LEAVES.BLOCK);
        }

        UNIQUE_FEATURES = config.uniqueFeatureSupplier.get(logs, leaves, WOOD_TYPE);

        Quickerator<UnsafeTriSet<?>> woodSetIterator = () -> LOG_VARIANTS;
        woodSetIterator.forEach(variants ->
                StrippableBlockRegistry.register(variants.BLOCK, WOOD_SET.STRIPPED_LOG.BLOCK)
        );
    }

    @Override
    public void register(final SetRegistry registry) {
        registry.iterate(LOG_VARIANTS).forEach(element -> registry.triSet(RegistryTypes.WOOD, element));
        registry.iterate(LOG_VARIANTS).forEach(element -> registry.triSet(RegistryTypes.ALL_WOOD_BLOCKS, element));
        WOOD_SET.register(registry);
        registry.iterate(TREE_VARIANTS).forEach(featureSets -> PRIMITIVE_TREE_CONFIGS.get(featureSets).register(registry));
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.CUTOUT);
    }

    public record TreeType<
            Generator extends SaplingGenerator,
            FeatureProvider extends FeatureCreator<Generator>,
            UniqueFeatures>(
            String woodType,
            String[] treeVariants,
            @Nullable String[] additionalLogVariants,
            FeatureSupplier<Generator, FeatureProvider> featureSupplier,
            UniqueFeatureSupplier<UniqueFeatures> uniqueFeatureSupplier
    ) {}

    public interface UniqueFeatureSupplier <UniqueFeatures> {
        UniqueFeatures get(final List<Block> allLogs, final List<Block> allLeaves, final String type);
    }
}
