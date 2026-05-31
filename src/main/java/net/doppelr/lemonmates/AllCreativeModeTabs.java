package net.doppelr.lemonmates;

import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.doppelr.lemonmates.block.properties.StrawsVariants;
import net.doppelr.lemonmates.block.properties.UmbrellaVariants;
import net.doppelr.lemonmates.fluid.ModFluids;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AllCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LemonMates.MOD_ID);

    private static ItemStack lemonadeJugStack(ItemStack stack) {
        stack.set(AllDataComponents.CAN_POUR, false);
        stack.set(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER, ApplicableFluidsToFluidContainer.NONE);
        stack.set(AllDataComponents.JUG_LEVEL, 0);
        return stack;
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BASE_CREATIVE_TAB = CREATIVE_MODE_TABS.register("base",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + LemonMates.MOD_ID + ".base"))
            .withTabsBefore(com.simibubi.create.AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
            .icon(() -> new ItemStack(ModItems.WATERMELON_LEMONADE_BOTTLE.get()))
            .displayItems((params, output) -> {
                // Fruits
                output.accept(ModItems.CITRON);
                output.accept(ModItems.CITRON_SLICE);
                output.accept(ModItems.ORANGE);
                output.accept(ModItems.ORANGE_SLICE);
                output.accept(ModItems.RASPBERRY);

                // Crates
                output.accept(ModBlocks.CITRON_CRATE);
                output.accept(ModBlocks.ORANGE_CRATE);
                output.accept(ModBlocks.RASPBERRY_CRATE);

                // Misc
                output.accept(ModBlocks.LEMONADE_GLASS);
                output.accept(lemonadeJugStack(ModItems.LEMONADE_JUG_TERRACOTTA.toStack()));
                output.accept(lemonadeJugStack(ModItems.LEMONADE_JUG_GLASS.toStack()));

                // Finished Lemonades
                output.accept(ModItems.CITRON_LEMONADE_BOTTLE);
                output.accept(ModItems.ORANGE_LEMONADE_BOTTLE);
                output.accept(ModItems.RASPBERRY_LEMONADE_BOTTLE);
                output.accept(ModItems.SUMMERMIX_LEMONADE_BOTTLE);
                output.accept(ModItems.WATERMELON_LEMONADE_BOTTLE);

                // Citron Wood Stuffs
                output.accept(ModBlocks.CITRON_SAPLING);
                output.accept(ModBlocks.CITRON_LEAVES);
                output.accept(ModBlocks.CITRON_LOG);
                output.accept(ModBlocks.CITRON_WOOD);
                output.accept(ModBlocks.STRIPPED_CITRON_LOG);
                output.accept(ModBlocks.STRIPPED_CITRON_WOOD);
                output.accept(ModBlocks.CITRON_PLANKS);
                output.accept(ModBlocks.CITRON_STAIRS);
                output.accept(ModBlocks.CITRON_SLAB);
                output.accept(ModBlocks.CITRON_FENCE);
                output.accept(ModBlocks.CITRON_FENCE_GATE);
                output.accept(ModBlocks.CITRON_DOOR);
                output.accept(ModBlocks.CITRON_TRAPDOOR);
                output.accept(ModBlocks.CITRON_PRESSURE_PLATE);
                output.accept(ModBlocks.CITRON_BUTTON);
                output.accept(ModBlocks.CITRON_SIGN);
                output.accept(ModItems.CITRON_SIGN);
                output.accept(ModItems.CITRON_HANGING_SIGN);
                output.accept(ModItems.CITRON_BOAT);
                output.accept(ModItems.CITRON_CHEST_BOAT);

                // Orange Wood Stuffs
                output.accept(ModBlocks.ORANGE_SAPLING);
                output.accept(ModBlocks.ORANGE_LEAVES);
                output.accept(ModBlocks.ORANGE_LOG);
                output.accept(ModBlocks.ORANGE_WOOD);
                output.accept(ModBlocks.STRIPPED_ORANGE_LOG);
                output.accept(ModBlocks.STRIPPED_ORANGE_WOOD);
                output.accept(ModBlocks.ORANGE_PLANKS);
                output.accept(ModBlocks.ORANGE_STAIRS);
                output.accept(ModBlocks.ORANGE_SLAB);
                output.accept(ModBlocks.ORANGE_FENCE);
                output.accept(ModBlocks.ORANGE_FENCE_GATE);
                output.accept(ModBlocks.ORANGE_DOOR);
                output.accept(ModBlocks.ORANGE_TRAPDOOR);
                output.accept(ModBlocks.ORANGE_PRESSURE_PLATE);
                output.accept(ModBlocks.ORANGE_BUTTON);
                output.accept(ModItems.ORANGE_SIGN);
                output.accept(ModItems.ORANGE_HANGING_SIGN);
                output.accept(ModItems.ORANGE_BOAT);
                output.accept(ModItems.ORANGE_CHEST_BOAT);
            })
        .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PROCESSING_CREATIVE_TAB = CREATIVE_MODE_TABS.register("processing",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + LemonMates.MOD_ID + ".processing"))
            .withTabsBefore(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey())
            .icon(() -> new ItemStack(ModItems.BOTTLE_MOLD_WITH_BOTTLE.get()))
            .displayItems((params, output) -> {
                // Plastic
                output.accept(ModItems.SEED_FLOUR);
                output.accept(ModItems.STARCH);
                output.accept(ModItems.BIOPLASTIC_MASS);
                output.accept(ModItems.BROKEN_BOTTLE);
                output.accept(ModFluids.PLASTIC_BUCKET.get());

                // Bottle form related
                output.accept(ModItems.PREFORM_MOLD);
                output.accept(ModItems.PREFORM_MOLD_WITH_PREFORM);
                output.accept(ModItems.PREFORM);
                output.accept(ModItems.BOTTLE_MOLD);
                output.accept(ModItems.BOTTLE_MOLD_WITH_PREFORM);
                output.accept(ModItems.BOTTLE_MOLD_WITH_BOTTLE);
                output.accept(ModItems.BOTTLE_EMPTY);
                output.accept(ModItems.CAP_MOLD);
                output.accept(ModItems.CAP_MOLD_WITH_CAP);
                output.accept(ModItems.BOTTLE_CAP);

                // Liquids
                output.accept(ModFluids.CITRON_LEMONADE_BUCKET.get());
                output.accept(ModFluids.ORANGE_LEMONADE_BUCKET.get());
                output.accept(ModFluids.RASPBERRY_LEMONADE_BUCKET.get());
                output.accept(ModFluids.SUMMERMIX_LEMONADE_BUCKET.get());
                output.accept(ModFluids.WATERMELON_LEMONADE_BUCKET.get());

                // Labels
                output.accept(ModItems.LABEL_EMPTY);
                output.accept(ModItems.LABEL_TEXT);
                output.accept(ModItems.LABEL_TEXT_COMPANY);
                output.accept(ModItems.LABEL_CITRON_LEMONADE);
                output.accept(ModItems.LABEL_ORANGE_LEMONADE);
                output.accept(ModItems.LABEL_RASPBERRY_LEMONADE);
                output.accept(ModItems.LABEL_SUMMERMIX_LEMONADE);
                output.accept(ModItems.LABEL_WATERMELON_LEMONADE);

                // Citron
                output.accept(ModItems.CITRON_LEMONADE_BOTTLE);
                output.accept(ModItems.EMPTY_CITRON_LEMONADE_BOTTLE);

                // Orange
                output.accept(ModItems.ORANGE_LEMONADE_BOTTLE);
                output.accept(ModItems.EMPTY_ORANGE_LEMONADE_BOTTLE);

                // Raspberry
                output.accept(ModItems.RASPBERRY_LEMONADE_BOTTLE);
                output.accept(ModItems.EMPTY_RASPBERRY_LEMONADE_BOTTLE);

                // Summer-Mix
                output.accept(ModItems.SUMMERMIX_LEMONADE_BOTTLE); //
                output.accept(ModItems.EMPTY_SUMMERMIX_LEMONADE_BOTTLE);

                // Watermelon
                output.accept(ModItems.WATERMELON_LEMONADE_BOTTLE);
                output.accept(ModItems.EMPTY_WATERMELON_LEMONADE_BOTTLE);
            })
        .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SERVING_CREATIVE_TAB = CREATIVE_MODE_TABS.register("serving",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + LemonMates.MOD_ID + ".serving"))
            .withTabsBefore(AllCreativeModeTabs.PROCESSING_CREATIVE_TAB.getKey())
            .icon(() -> new ItemStack(ModItems.LEMONADE_GLASS_DECORATED.get()))
            .displayItems((params, output) -> {

                // Glass
                output.accept(ModBlocks.LEMONADE_GLASS);
                output.accept(lemonadeJugStack(ModItems.LEMONADE_JUG_TERRACOTTA.toStack()));
                output.accept(lemonadeJugStack(ModItems.LEMONADE_JUG_GLASS.toStack()));
                output.accept(ModItems.ICE_CUBES);

                // Straws
                for (StrawsVariants variants : StrawsVariants.values())
                    if (variants.getValue() != 0)
                        output.accept(variants.getRegisteredItem());


                // Umbrellas
                for (UmbrellaVariants variants : UmbrellaVariants.values())
                    if (variants.getValue() != 0)
                        output.accept(variants.getRegisteredItem());

                // Fruit
                output.accept(ModItems.CITRON_SLICE);
                output.accept(ModItems.ORANGE_SLICE);

                // Finished Lemonades
                output.accept(ModItems.CITRON_LEMONADE_BOTTLE);
                output.accept(ModItems.ORANGE_LEMONADE_BOTTLE);
                output.accept(ModItems.RASPBERRY_LEMONADE_BOTTLE);
                output.accept(ModItems.SUMMERMIX_LEMONADE_BOTTLE);
                output.accept(ModItems.WATERMELON_LEMONADE_BOTTLE);
            })
        .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
