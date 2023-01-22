package net.fluffybumblebee.terrains.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fluffybumblebee.terrains.common.registry.sets.tree.component.WoodSet;
import net.fluffybumblebee.terrains.util.registration.block.TriSet;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.fluffybumblebee.terrains.util.registration.registry_set.registrars.SetRegistry.Storage;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;

import static net.fluffybumblebee.terrains.common.registry.sets.AllRegistrySets.*;
import static net.minecraft.tag.BlockTags.*;

public class TerrainsBlockTags extends FabricTagProvider.BlockTagProvider {
    public TerrainsBlockTags(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTags() {
        FULL_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element ->
                addBlockTags(element, LEAVES, HOE_MINEABLE)
        );

        STAINED_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element ->
                addBlockTags(element, LEAVES, HOE_MINEABLE)
        );

        FULL_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element ->
                addBlockTags(element, SAPLINGS)
        );

        STAINED_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element ->
                addBlockTags(element, SAPLINGS)
        );

        FULL_TREES.iterateRegistry(RegistryTypes.POTTED_BLOCK).forEach(element ->
                addBlockTags(element, FLOWER_POTS)
        );

        STAINED_TREES.iterateRegistry(RegistryTypes.POTTED_BLOCK).forEach(element ->
                addBlockTags(element, FLOWER_POTS)
        );

        FULL_TREES.getConfigQuickerator().forEach(element -> addWoodBlockTags(element.WOOD_SET));
        FULL_TREES.getConfigQuickerator().forEach(element ->
                element.LOG_VARIANTS.iterator().forEachRemaining(triSet ->
                        addBlockTags(triSet.BLOCK, LOGS_THAT_BURN, LOGS))
        );

        FOLIAGE.iterateRegistry(RegistryTypes.FLOWER).forEach(element ->
            addBlockTags(FLOWERS, element.block().orElseThrow())
        );
    }

    @SafeVarargs
    public final void addBlockTags(final Block block, final TagKey<Block>... tags) {
        for (TagKey<Block> tag : tags)
            getOrCreateTagBuilder(tag).add(block);
    }

    @SafeVarargs
    public final void addBlockTags(final TriSet<?> block, final TagKey<Block>... tags) {
        addBlockTags(block.BLOCK, tags);
    }

    @SafeVarargs
    public final void addBlockTags(final Storage block, final TagKey<Block>... tags) {
        addBlockTags(block.block().orElseThrow(), tags);
    }

    public final void addBlockTags(final TagKey<Block> tag, final Block... blocks) {
        for (Block block : blocks)
            getOrCreateTagBuilder(tag).add(block);
    }

    public final void addBlockTags(final TagKey<Block> tag, final TriSet<?>... blocks)  {
        for (TriSet<?> set : blocks)
            addBlockTags(tag, set.BLOCK);
    }

    @SuppressWarnings("unused")
    public final void addBlockTags(final TagKey<Block> tag, final Storage... blocks)  {
        for (Storage storage : blocks)
            addBlockTags(tag, storage.block().orElseThrow());
    }


    public final void addWoodBlockTags(final WoodSet woodSet) {
        addBlockTags(woodSet.LOG, LOGS_THAT_BURN, LOGS);
        addBlockTags(woodSet.PLANKS, PLANKS);
        addBlockTags(woodSet.SIGN, STANDING_SIGNS);
        addBlockTags(woodSet.WALL_SIGN, WALL_SIGNS);
        addBlockTags(woodSet.BUTTON, WOODEN_BUTTONS);
        addBlockTags(woodSet.DOOR, WOODEN_DOORS);
        addBlockTags(woodSet.FENCE, WOODEN_FENCES);
        addBlockTags(woodSet.FENCE_GATE, FENCE_GATES, AXE_MINEABLE);
        addBlockTags(woodSet.PRESSURE_PLATE, WOODEN_PRESSURE_PLATES);
        addBlockTags(woodSet.SLAB, WOODEN_SLABS);
        addBlockTags(woodSet.STAIRS, WOODEN_STAIRS);
        addBlockTags(woodSet.TRAPDOOR, WOODEN_TRAPDOORS);
        addBlockTags(LOGS, woodSet.LOG, woodSet.WOOD, woodSet.STRIPPED_LOG, woodSet.STRIPPED_WOOD);
    }
}
