package net.stockieslad.terrains.cancer.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.stockieslad.terrains.common.registry.sets.tree.component.WoodSet;
import net.stockieslad.terrains.util.registration.mass.UnsafeTriSet;
import net.stockieslad.terrains.util.registration.registry_set.registrars.RegistryTypes;
import net.stockieslad.terrains.util.registration.mass.SafeTriSet;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.stockieslad.terrains.common.registry.sets.RegistrySetManager.*;
import static net.minecraft.tag.ItemTags.*;


public class TerrainsItemTags extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> PLANKS_THAT_BURN = TagKey.of(
            Registry.ITEM_KEY,
            new Identifier("c", "planks_that_burn")
    );

    public TerrainsItemTags(FabricDataGenerator dataGenerator) {
        super(dataGenerator, null);
    }

    @Override
    public void generateTags() {
        FULL_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element ->
                addItemTags(element, LEAVES)
        );
        STAINED_TREES.iterateRegistry(RegistryTypes.LEAVES).forEach(element ->
                addItemTags(element, LEAVES)
        );

        FULL_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element ->
                addItemTags(element, SAPLINGS)
        );

        STAINED_TREES.iterateRegistry(RegistryTypes.SAPLING).forEach(element ->
                addItemTags(element, SAPLINGS)
        );

        FULL_TREES.getConfigQuickerator().forEach(element -> addWoodBlockTags(element.WOOD_SET));

        FOLIAGE.iterateRegistry(RegistryTypes.FLOWER).forEach(element ->
                addItemTags(FLOWERS, element.item().orElseThrow())
        );
    }

    @SafeVarargs
    public final void addItemTags(final Item item, final TagKey<Item>... tags) {
        for (TagKey<Item> tag : tags)
            getOrCreateTagBuilder(tag).add(item);
    }

    @SafeVarargs
    public final void addItemTags(final UnsafeTriSet<?> block, final TagKey<Item>... tags) {
        addItemTags(block.ITEM, tags);
    }

    @SafeVarargs
    public final void addItemTags(final SafeTriSet block, final TagKey<Item>... tags) {
        addItemTags(block.item().orElseThrow(), tags);
    }

    public final void addItemTags(final TagKey<Item> tag, final Item... items) {
        for (Item item : items)
            getOrCreateTagBuilder(tag).add(item);
    }

    public final void addItemTags(final TagKey<Item> tag, final UnsafeTriSet<?>... blocks)  {
        for (UnsafeTriSet<?> set : blocks)
            addItemTags(tag, set.ITEM);
    }

    @SuppressWarnings("unused")
    public final void addItemTags(final TagKey<Item> tag, final SafeTriSet... blocks)  {
        for (SafeTriSet safeTriSet : blocks)
            addItemTags(tag, safeTriSet.item().orElseThrow());
    }


    public final void addWoodBlockTags(final WoodSet woodSet) {
        addItemTags(woodSet.LOG, LOGS_THAT_BURN);
        addItemTags(woodSet.PLANKS, PLANKS, PLANKS_THAT_BURN);
        //addItemTags(woodSet.SIGN, SIGNS);
        addItemTags(woodSet.BUTTON, WOODEN_BUTTONS);
        addItemTags(woodSet.DOOR, WOODEN_DOORS);
        addItemTags(woodSet.FENCE, WOODEN_FENCES);
        addItemTags(woodSet.PRESSURE_PLATE, WOODEN_PRESSURE_PLATES);
        addItemTags(woodSet.SLAB, WOODEN_SLABS);
        addItemTags(woodSet.STAIRS, WOODEN_STAIRS);
        addItemTags(woodSet.TRAPDOOR, WOODEN_TRAPDOORS);
        addItemTags(LOGS, woodSet.LOG, woodSet.WOOD, woodSet.STRIPPED_LOG, woodSet.STRIPPED_WOOD);
    }
}
