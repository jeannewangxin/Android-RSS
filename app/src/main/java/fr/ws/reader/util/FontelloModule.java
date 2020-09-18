package fr.ws.reader.util;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 图标文字fontello
 */
public class FontelloModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "fontello.ttf";
    }

    @Override
    public Icon[] characters() {
        return FontelloIcons.values();
    }
}