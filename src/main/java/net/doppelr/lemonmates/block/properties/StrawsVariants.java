package net.doppelr.lemonmates.block.properties;

import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.IntFunction;

public enum StrawsVariants implements StringRepresentable {
    NONE(0, false),
    BASIC(1, true),
    RAINBOW(2, true),
    TRANS(3, true),
    NONBINARY(4, true),
    LESBIAN(5, true),
    GAY(6, true),
    GENDERFLUID(7, true),
    ACE(8, true),
    ARO(9, true),
    AROACE(10, true),
    AGENDER(11, true),
    BI(12, true),
    PAN(13, true),
    GERMAN(14, true),
    OMNISEXUAL(15, true),;
    private static final IntFunction<StrawsVariants> BY_ID = ByIdMap.continuous(StrawsVariants::getValue, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

    DeferredItem<Item> registeredItem;
    final int value;

    StrawsVariants(int value, boolean registerItem) {
        this.value = value;
        if (registerItem)
            registeredItem = ModItems.ITEMS.register("straw_" + this.name().toLowerCase(Locale.ROOT), () -> new Item(new Item.Properties()));
    }

    public DeferredItem<Item> getRegisteredItem() {
        return registeredItem;
    }

    public int getValue() {
        return value;
    }

    public static StrawsVariants byValue(int value) {
        return BY_ID.apply(value);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static void include() {}
}
