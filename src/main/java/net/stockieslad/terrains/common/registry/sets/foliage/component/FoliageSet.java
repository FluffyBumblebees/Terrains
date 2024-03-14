package net.stockieslad.terrains.common.registry.sets.foliage.component;

import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.plant.ConfiguredFlowerPotBlock;
import net.stockieslad.terrains.common.registry.sets.foliage.TypesFoliage;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.List;

import static net.stockieslad.terrains.common.registry.sets.foliage.component.FoliageSet.FeatureCreator;
import static net.stockieslad.terrains.core.TerrainsDefaults.getIdentifier;
import static net.stockieslad.terrains.util.registration.mass.UnsafeTriSet.buildFlammableBlock;
import static net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes.POTTED_BLOCK;

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

            final UnsafeTriSet<B> foliage;
            foliage = buildFlammableBlock(
                    foliageTypes.blockInstanceProvider().getInstance(type),
                    string
            );

            final var potBlock = new UnsafeTriSet.Builder<>(
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
    public void register(final SetRegistry registry) {
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
            >(UnsafeTriSet<?> block, UnsafeTriSet<?> potBlock, FeatureInstance featureInstance
    ) {}


    public interface FoliageProvider <B extends Block, T extends Enum<?>> {
        B getInstance(final T type);
    }

    public interface FeatureSupplier <B extends Block, FeatureInstance extends FeatureCreator> {
        FeatureInstance get(final B block, final String name);
    }

    public interface FeatureCreator {}
}
