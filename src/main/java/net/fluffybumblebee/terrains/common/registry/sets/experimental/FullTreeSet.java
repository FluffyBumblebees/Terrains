package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fluffybumblebee.terrains.common.instances.block.wood.WoodBlock;
import net.fluffybumblebee.terrains.common.registry.sets.experimental.PrimitiveTreeSet.SaplingGeneratorProvider;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodRegistrySetConfig;
import net.fluffybumblebee.terrains.util.registration.block.BlockSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.helper.Quickerator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static net.fluffybumblebee.terrains.util.registration.registry_set.helper.EasyIf.onIf;

public class FullTreeSet<Generator extends SaplingGenerator,
        Configurator extends FullTreeSet.FeatureConfigurator<Generator>>
        implements RegistrySetCreator {
    public final HashMap<String, PrimitiveTreeSet<Generator>> PRIMITIVE_TREE_CONFIGS;
    public final Configurator FEATURE_CONFIGURATOR;

    private final List<BlockSet<?>> ALL_BLOCKS;
    public final WoodRegistrySetConfig WOOD_SET;

    public FullTreeSet(
            @NotNull final TreeType<Generator, Configurator> config
    ) {
        final String woodType = config.woodType;
        final String[] treeVariants = config.treeVariants;
        final List<BlockSet<WoodBlock>> additionalLogVariants = Objects.requireNonNullElseGet(config.additionalLogVariants
                , List::of);
        final Configurator featureConfigurator = config.featureConfigurator.get(this);
        final SaplingGeneratorProvider<Generator> saplingGeneratorProvider =
                featureConfigurator.getSaplingGenerator();

        FEATURE_CONFIGURATOR = featureConfigurator;

        ALL_BLOCKS = new ArrayList<>();
        WOOD_SET = new WoodRegistrySetConfig(woodType);
        PRIMITIVE_TREE_CONFIGS = new HashMap<>();

        for (String treeVariant : treeVariants) {
            PRIMITIVE_TREE_CONFIGS.put(treeVariant, new PrimitiveTreeSet<>(
                    new PrimitiveTreeSet.Config<>(
                            treeVariant + "_" + woodType,
                            saplingGeneratorProvider
                    )
            ));
        }

        ALL_BLOCKS.addAll(additionalLogVariants);
        ALL_BLOCKS.addAll(WOOD_SET.getAllBlockSets());

        Quickerator<String> treeVariantIterator = () -> Arrays.asList(config.treeVariants());
        treeVariantIterator.forEach(featureSets -> PRIMITIVE_TREE_CONFIGS.get(featureSets).getBlockIterator().forEach(blockSet ->
                onIf(!(blockSet.BLOCK instanceof FlowerPotBlock),() -> ALL_BLOCKS.add(blockSet)))
        );

        Quickerator<BlockSet<WoodBlock>> woodSetIterator = () -> additionalLogVariants;
        woodSetIterator.forEach(variants ->
                StrippableBlockRegistry.register(variants.BLOCK, WOOD_SET.STRIPPED_LOG.BLOCK)
        );
    }

    @Override
    public List<Item> getAllItems() {
        return WOOD_SET.getAllItems();
    }

    @Override
    public List<BlockSet<?>> getAllBlockSets() {
        return ALL_BLOCKS;
    }


    public record TreeType<Generator extends SaplingGenerator, Configurator extends FeatureConfigurator<Generator
            >>(
            String woodType,
            String[] treeVariants,
            @Nullable List<BlockSet<WoodBlock>> additionalLogVariants,
            ConfiguratorSupplier<Generator, Configurator> featureConfigurator
    ) {}

    public interface ConfiguratorSupplier<Generator extends SaplingGenerator, Configurator extends FeatureConfigurator<Generator
            >> {
        Configurator get(FullTreeSet<Generator, Configurator> set);
    }

    public interface FeatureConfigurator <Generator extends SaplingGenerator> {
        SaplingGeneratorProvider<Generator> getSaplingGenerator();
    }
}
