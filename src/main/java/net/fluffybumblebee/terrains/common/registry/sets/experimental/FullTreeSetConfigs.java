package net.fluffybumblebee.terrains.common.registry.sets.experimental;

import net.fluffybumblebee.terrains.common.registry.sets.experimental.FullTreeSet.TreeType;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistrySet;

import java.util.List;

public class FullTreeSetConfigs {
    public static final RegistrySet<TreeType<?, ?>, FullTreeSet<?, ?>> FULL_TREES = new RegistrySet<>(
            List.of(
                    MapleTreeType.CONFIG
            ),
            FullTreeSet::new
    );
}


