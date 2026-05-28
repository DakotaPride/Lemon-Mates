package net.doppelr.lemonmates;

import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.doppelr.lemonmates.block.properties.FruitSlices;
import net.doppelr.lemonmates.block.properties.StrawsVariants;
import net.doppelr.lemonmates.block.properties.UmbrellaVariants;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AllBlockStateProperties {
    public static final IntegerProperty DRINK_LEVEL = IntegerProperty.create("drink_level", 0, 2);
    public static final EnumProperty<StrawsVariants> STRAWS = EnumProperty.create("straw", StrawsVariants.class);
    public static final BooleanProperty ICE_CUBES = BooleanProperty.create("has_ice");
    public static final EnumProperty<FruitSlices> FRUIT_SLICES = EnumProperty.create("fruit_slice", FruitSlices.class);
    public static final EnumProperty<UmbrellaVariants> UMBRELLAS = EnumProperty.create("umbrella", UmbrellaVariants.class);
    public static final EnumProperty<ApplicableFluidsToFluidContainer> APPLICABLE_FLUID_TO_CONTAINER = EnumProperty.create("fluid", ApplicableFluidsToFluidContainer.class);

    public static final IntegerProperty JUG_LEVEL = IntegerProperty.create("jug_level", 0, 8);
    public static final BooleanProperty CAN_POUR = BooleanProperty.create("can_pour");
}
