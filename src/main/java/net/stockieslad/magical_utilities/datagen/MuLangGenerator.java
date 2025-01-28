package net.stockieslad.magical_utilities.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.stockieslad.magical_utilities.core.Cloud;

public class MuLangGenerator extends FabricLanguageProvider {
    protected MuLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add("itemgroup.magical_utilities.clouds", "Magical Clouds");
        builder.add("recipe.magical_utilities.cloud_mixing", "Cloud Mixing");
        builder.add("recipe.magical_utilities.cloud_mixing.description", "Place in world to mix");
        for (Cloud value : Cloud.values()) {
            var name = value.name().toLowerCase();
            var nameBuilder = new StringBuilder(name);
            nameBuilder.setCharAt(0, Character.toUpperCase(name.charAt(0)));
            builder.add(value.block, nameBuilder + " Cloud");
            builder.add(value.item.tooltipKey, value.tooltip);
            builder.add(value.item.pacifierTooltipKey, value.pacifierTooltip);
            builder.add(value.item.activatorTooltipKey, value.activatorTooltip);
        }
    }
}
