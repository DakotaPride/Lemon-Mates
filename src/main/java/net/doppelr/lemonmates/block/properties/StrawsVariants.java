package net.doppelr.lemonmates.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum StrawsVariants implements StringRepresentable {
    NONE,
    BASIC,
    RAINBOW,
    TRANS,
    NONBINARY,
    LESBIAN,
    GAY,
    GENDERFLUID,
    ACE,
    ARO,
    AROACE,
    AGENDER,
    BI,
    PAN,
    GERMAN,
    OMNISEXUAL,;

    StrawsVariants() {}

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
