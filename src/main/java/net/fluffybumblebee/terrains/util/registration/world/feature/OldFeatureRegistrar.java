package net.fluffybumblebee.terrains.util.registration.world.feature;

import com.mojang.datafixers.util.Either;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryOwner;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.fluffybumblebee.terrains.core.TerrainsDefaults.getIdentifier;

@SuppressWarnings("unchecked")
public class OldFeatureRegistrar {
    public static RegistryEntry<PlacedFeature> registerPlaced(
            final String s, final RegistryEntry<ConfiguredFeature<?,?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return new RegisteredObj<>(
                getIdentifier(s),
                new PlacedFeature(registryEntry, modifiers)
        );
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC,?>> registerConfigured(
            String s, F feature, FC config
    ) {
        return new RegisteredObj<>(getIdentifier(s), new ConfiguredFeature<>(feature, config));
    }

    private record RegisteredObj<T>(Identifier key, T value) implements RegistryEntry<T>
    {
        @Override
        public boolean hasKeyAndValue() {
            return true;
        }

        @Override
        public boolean matchesId(Identifier id) {
            return false;
        }

        @Override
        public boolean matchesKey(RegistryKey<T> key) {
            return false;
        }

        @Override
        public boolean isIn(TagKey<T> tag) {
            return false;
        }

        @Override
        public boolean matches(Predicate<RegistryKey<T>> predicate) {
            return false;
        }

        @Override
        public Either<RegistryKey<T>, T> getKeyOrValue() {
            return Either.right(this.value);
        }

        @Override
        public Optional<RegistryKey<T>> getKey() {
            return Optional.of(RegistryKey.of(Registries.ROOT_KEY, key));
        }

        @Override
        public Type getType() {
            return Type.DIRECT;
        }

        @Override
        public String toString() {
            return "Direct{" + this.value + "}";
        }

        @Override
        public boolean ownerEquals(RegistryEntryOwner<T> owner) {
            return true;
        }

        @Override
        public Stream<TagKey<T>> streamTags() {
            return Stream.of(new TagKey[0]);
        }
    }
}
