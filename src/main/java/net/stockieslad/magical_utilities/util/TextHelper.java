package net.stockieslad.magical_utilities.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;

import java.util.Iterator;

public class TextHelper {
    public static void drawTextWrapped(TextRenderer renderer, StringVisitable originalText, int x, int y, int width, ConText conText) {
        for(Iterator<OrderedText> text = renderer.wrapLines(originalText, width).iterator(); text.hasNext(); y += 9)
            conText.consume(renderer, text.next(), x, y);
    }

    public interface ConText {
        void consume(TextRenderer renderer, OrderedText text, int x, int y);
    }
}
