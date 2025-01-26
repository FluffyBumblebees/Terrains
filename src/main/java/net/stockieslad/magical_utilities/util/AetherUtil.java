package net.stockieslad.magical_utilities.util;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class AetherUtil {
    public static final TagKey<Biome> IS_AETHER = TagKey.of(RegistryKeys.BIOME, new Identifier("aether", "is_aether"));
}
