package net.stockieslad.magical_utilities.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.stockieslad.magical_utilities.core.Cloud;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CloudItem extends BlockItem {
    public final String tooltipKey;
    public final String pacifierTooltipKey;
    public final String activatorTooltipKey;

    public CloudItem(Cloud cloud, Settings settings) {
        super(cloud.block, settings);
        this.tooltipKey = "tooltip." + cloud.identifier.getNamespace() + "." + cloud.identifier.getPath();
        this.activatorTooltipKey = tooltipKey + ".activator";
        this.pacifierTooltipKey = tooltipKey + ".pacifier";
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(tooltipKey).formatted(Formatting.DARK_GREEN));
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable(pacifierTooltipKey).formatted(Formatting.DARK_BLUE));
        tooltip.add(Text.translatable(activatorTooltipKey).formatted(Formatting.DARK_RED));
    }
}
