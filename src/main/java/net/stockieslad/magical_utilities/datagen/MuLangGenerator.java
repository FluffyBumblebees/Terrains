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
        for (Cloud value : Cloud.values()) {
            var name = value.name().toLowerCase();
            var nameBuilder = new StringBuilder(name);
            nameBuilder.setCharAt(0, Character.toUpperCase(name.charAt(0)));
            builder.add(value.block, nameBuilder + " Cloud");
        }
    }
}
