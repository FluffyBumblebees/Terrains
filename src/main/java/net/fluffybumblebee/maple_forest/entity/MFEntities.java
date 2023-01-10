package net.fluffybumblebee.maple_forest.entity;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import net.fluffybumblebee.maple_forest.entity.boat.MFBoatTypes;
import net.fluffybumblebee.maple_forest.util.type.wood.MFWoodTypes;

@SuppressWarnings("unused")
public class MFEntities {
    public static TerraformBoatType MAPLE_BOAT;
    public static void addToRegistry() {
        new MFBoatTypes(MFWoodTypes.MAPLE, MAPLE_BOAT);
    }
}
