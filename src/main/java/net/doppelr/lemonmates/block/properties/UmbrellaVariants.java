package net.doppelr.lemonmates.block.properties;

import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.IntFunction;

public enum UmbrellaVariants implements StringRepresentable {
    NONE(0, false),
    RED_WHITE(1, true),
    YELLOW_WHITE(2, true),
    BLACK_PURPLE(3, true),
    ORANGE_WHITE(4, true),;
    private static final IntFunction<UmbrellaVariants> BY_ID = ByIdMap.continuous(UmbrellaVariants::getValue, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

    DeferredItem<Item> registeredItem;
    final int value;

    UmbrellaVariants(int value, boolean registerItem) {
        this.value = value;
        if (registerItem)
            registeredItem = ModItems.ITEMS.register("drink_umbrella_" + value, () -> new Item(new Item.Properties()));
    }

    public DeferredItem<Item> getRegisteredItem() {
        return registeredItem;
    }

    public int getValue() {
        return value;
    }

    public static UmbrellaVariants byValue(int value) {
        return BY_ID.apply(value);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static void include() {}
}
