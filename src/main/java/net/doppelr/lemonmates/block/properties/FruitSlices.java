package net.doppelr.lemonmates.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum FruitSlices implements StringRepresentable {
    NONE,
    CITRON,
    ORANGE,;

    FruitSlices() {}

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
