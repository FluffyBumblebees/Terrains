package net.stockieslad.terrains.common.registry.sets.clouds.component;


import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.stockieslad.abstractium.library.common.registration.AbstractRegistrar;
import net.stockieslad.abstractium.library.common.worldgen.biome.AbstractBiomes;
import net.stockieslad.abstractium.util.dynamic.Mimic;
import net.stockieslad.terrains.client.render.RenderTypes;
import net.stockieslad.terrains.common.instances.block.cloud.BasicCloudBlock;
import net.stockieslad.terrains.common.world.inbuilt_structures.carver.cloud.CloudCarver;
import net.stockieslad.terrains.common.world.inbuilt_structures.carver.cloud.CloudCarverConfig;
import net.stockieslad.terrains.core.TerrainsDefaults;
import net.stockieslad.terrains.util.predicates.BlockPredicates;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistrySetCreator;
import net.stockieslad.terrains.util.registration.registry_set.registrars.SetRegistry;

import java.util.List;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.of;
import static net.stockieslad.abstractium.init.AbstractiumCommon.COMMON_ABSTRACTION_HANDLER;
import static net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes.TRANSPARENT_FULL_BLOCK;

public final class CloudSet implements RegistrySetCreator {
    private static final AbstractRegistrar ABSTRACT_REGISTRAR = COMMON_ABSTRACTION_HANDLER.abstraction.getRegistrar();
    public static final Carver<CloudCarverConfig> ABSTRACT_CLOUD_CARVER = ABSTRACT_REGISTRAR.registerCarver(
            TerrainsDefaults.getIdentifier("cloud_carver"), new CloudCarver(CloudCarverConfig.CODEC)
    );

    public static Settings aercloud() {
        return of(Material.ICE).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque().solidBlock(BlockPredicates::never
        ).suffocates(BlockPredicates::never).blockVision(BlockPredicates::never);
    }

    public final Mimic CLOUD_CARVER;
    public final UnsafeTriSet<BasicCloudBlock> cloudBlock;
    public CloudSet(final Config config) {
        final var id = config.id;
        if (config.burnable)
            cloudBlock = new UnsafeTriSet.Builder<>(config.cloudBlock, TerrainsDefaults.getIdentifier(id + "_cloud"))
                    .addBlockItem().addBurnable(200).addFlammability(10, 500).build();
        else cloudBlock = UnsafeTriSet.buildBlock(config.cloudBlock, id + "_cloud");
        CLOUD_CARVER = ABSTRACT_REGISTRAR.registerCarverConfig(
                TerrainsDefaults.getIdentifier(id + "_cloud_carver"),
                ABSTRACT_CLOUD_CARVER.configure(
                        new CloudCarverConfig(
                                config.probability / 10,
                                UniformHeightProvider.create(YOffset.fixed(280), YOffset.fixed(320)),
                                UniformFloatProvider.create(0.25F, 1.1F),
                                YOffset.fixed(280),
                                UniformFloatProvider.create(0.2F, 0.4F),
                                UniformFloatProvider.create(0.4F, 0.6F),
                                BlockStateProvider.of(cloudBlock.BLOCK),
                                ConstantFloatProvider.create(0.15F),
                                UniformFloatProvider.create(1.585F, 2.75F),
                                ConstantIntProvider.create(2),
                                ConstantFloatProvider.create(6),
                                ConstantIntProvider.create(1),
                                ConstantFloatProvider.create(0.125F)
                        )
                )
        );
    }

    @Override
    public void register(final SetRegistry registry) {
        registry.triSet(TRANSPARENT_FULL_BLOCK, cloudBlock);
    }

    @Override
    public List<RenderTypes> getRenderTypes() {
        return List.of(RenderTypes.TRANSLUCENT);
    }

    @Override
    public void generate() {
        COMMON_ABSTRACTION_HANDLER.abstraction.getStructureGenerator()
                .generateCarver(
                       COMMON_ABSTRACTION_HANDLER.abstraction.getRegistrar().getKeyFromEntry(CLOUD_CARVER),
                        AbstractBiomes.OVERWORLD,
                        GenerationStep.Carver.AIR
                );
        //generateCarver(CLOUD_CARVER, BiomeSelectors.foundInOverworld(), GenerationStep.Carver.AIR);
    }
    public record Config(BasicCloudBlock cloudBlock, float probability, String id, boolean burnable) {}
}
