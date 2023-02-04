package net.fluffybumblebee.terrains.common.registry.sets.foliage.component;

import net.fluffybumblebee.terrains.client.render.RenderTypes;
import net.fluffybumblebee.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.fluffybumblebee.terrains.common.registry.sets.foliage.TypesFoliage;
import net.fluffybumblebee.terrains.util.registration.block.TriSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.List;

import static net.fluffybumblebee.terrains.common.registry.sets.foliage.component.FoliageSet.FeatureCreator;
import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;
import static net.fluffybumblebee.terrains.util.registration.block.TriSet.buildFlammableBlock;
import static net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes.POTTED_BLOCK;

public final class FoliageSet<
        B extends Block, T extends Enum<?>,
        FeatureInstance extends FeatureCreator>
        implements RegistrySetCreator {
    public final HashMap<T, UniqueFoliageSetHolder<FeatureInstance>> foliageHolder;
    private final RegistryTypes type;
    private final List<T> types;


    public FoliageSet(final TypesFoliage<B, T, FeatureInstance> foliageTypes) {
        foliageHolder = new HashMap<>();
        types = foliageTypes.types();
        type = foliageTypes.registryTypes();

        for (T type : foliageTypes.types()) {
            final String string = type.name().toLowerCase() + "_" + foliageTypes.type_suffix();

            final TriSet<B> foliage;
            foliage = buildFlammableBlock(
                    foliageTypes.blockInstanceProvider().getInstance(type),
                    string
            );

            final var potBlock = new TriSet.Builder<>(
                    new ConfiguredFlowerPotBlock(foliage.BLOCK),
                    getIdentifier("potted_" + string)
            ).build();
            foliageHolder.put(type, new UniqueFoliageSetHolder<>(
                    foliage,
                    potBlock,
                    foliageTypes.featureSupplier().get(foliage.BLOCK, string)
            ));
        }
    }

    @Override
    public void registryEvent(SetRegistry registry) {
        for (T type : types) {
            var holder = foliageHolder.get(type);
            registry.triSet(this.type, holder.block);
            registry.triSetPotted(POTTED_BLOCK, holder.potBlock);
        }
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.CUTOUT);
    }

    public record UniqueFoliageSetHolder<
            FeatureInstance extends FeatureCreator
            >(TriSet<?> block, TriSet<?> potBlock, FeatureInstance featureInstance) {}


    public interface FoliageProvider <B extends Block, T extends Enum<?>> {
        B getInstance(T type);
    }

    public interface FeatureSupplier <B extends Block, FeatureInstance extends FeatureCreator> {
        FeatureInstance get(B block, String name);
    }

    public interface FeatureCreator {}
}
