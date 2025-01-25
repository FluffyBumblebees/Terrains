package net.stockieslad.magical_utilities.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.structure.rule.BlockStateMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.Target;
import net.stockieslad.magical_utilities.core.Cloud;

import java.util.List;

import static net.minecraft.world.gen.feature.OreFeatureConfig.createTarget;

public class MuConfiguredFeatures {
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> ctx) {
        RuleTest airReplaceables = new BlockStateMatchRuleTest(Blocks.AIR.getDefaultState());
        RuleTest caveAirReplaceables = new BlockStateMatchRuleTest(Blocks.CAVE_AIR.getDefaultState());
        for (Cloud value : Cloud.values()) {
            if (value.configuredFeature != null) {
                var state = value.block.getDefaultState();
                List<Target> overworldOres = List.of(createTarget(airReplaceables, state), createTarget(caveAirReplaceables, state));
                ctx.register(value.configuredFeature, new ConfiguredFeature<>(MuFeatures.CLOUD, new OreFeatureConfig(overworldOres, 25)));
            }
        }
    }
}
