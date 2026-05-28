package net.doppelr.lemonmates.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum UmbrellaVariants implements StringRepresentable {
    NONE,
    RED_WHITE,
    YELLOW_WHITE,
    BLACK_PURPLE,
    ORANGE_WHITE,;

    UmbrellaVariants() {}

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
