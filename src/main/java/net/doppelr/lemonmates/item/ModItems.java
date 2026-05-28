package net.doppelr.lemonmates.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.doppelr.lemonmates.AllFoodProperties;
import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.entity.ModBoatEntity;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LemonMates.MOD_ID);

    // Sequenced Ingredient
    public static final DeferredItem<Item> INCOMPLETE_CITRON_LEMONADE = ITEMS.register("incomplete_citron_lemonade",
        () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_ORANGE_LEMONADE = ITEMS.register("incomplete_orange_lemonade",
        () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_RASPBERRY_LEMONADE = ITEMS.register("incomplete_raspberry_lemonade",
        () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_SUMMERMIX_LEMONADE = ITEMS.register("incomplete_summermix_lemonade",
        () -> new SequencedAssemblyItem(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_WATERMELON_LEMONADE = ITEMS.register("incomplete_watermelon_lemonade",
        () -> new SequencedAssemblyItem(new Item.Properties()));

    // Plastic
    public static final DeferredItem<Item> SEED_FLOUR = ITEMS.register("seed_flour",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STARCH = ITEMS.register("starch",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BIOPLASTIC_MASS = ITEMS.register("bioplastic_mass",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BROKEN_BOTTLE = ITEMS.register("broken_bottle",
        () -> new Item(new Item.Properties()));

    // Molds
    public static final DeferredItem<Item> PREFORM_MOLD = ITEMS.register("preform_mold",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PREFORM_MOLD_WITH_PREFORM = ITEMS.register("preform_mold_with_preform",
        () -> new Item(new Item.Properties().craftRemainder(ModItems.PREFORM_MOLD.get())));
    public static final DeferredItem<Item> BOTTLE_MOLD = ITEMS.register("bottle_mold",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOTTLE_MOLD_WITH_PREFORM = ITEMS.register("bottle_mold_with_preform",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOTTLE_MOLD_WITH_BOTTLE = ITEMS.register("bottle_mold_with_bottle",
        () -> new Item(new Item.Properties().craftRemainder(ModItems.BOTTLE_MOLD.get())));
    public static final DeferredItem<Item> CAP_MOLD = ITEMS.register("cap_mold",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CAP_MOLD_WITH_CAP = ITEMS.register("cap_mold_with_cap",
        () -> new Item(new Item.Properties().craftRemainder(ModItems.CAP_MOLD.get())));

    // Bottle Basics
    public static final DeferredItem<Item> PREFORM = ITEMS.register("preform",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOTTLE_EMPTY = ITEMS.register("bottle_empty",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOTTLE_CAP = ITEMS.register("bottle_cap",
        () -> new Item(new Item.Properties()));

    // Lemonade related
    // Labels Basic
    public static final DeferredItem<Item> LABEL_EMPTY = ITEMS.register("label_empty",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_TEXT = ITEMS.register("label_text",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_TEXT_COMPANY = ITEMS.register("label_text_company",
        () -> new Item(new Item.Properties()));

    // Labels Lemonades
    public static final DeferredItem<Item> LABEL_CITRON_LEMONADE = ITEMS.register("label_citron_lemonade",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_ORANGE_LEMONADE = ITEMS.register("label_orange_lemonade",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_RASPBERRY_LEMONADE = ITEMS.register("label_raspberry_lemonade",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_SUMMERMIX_LEMONADE = ITEMS.register("label_summermix_lemonade",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LABEL_WATERMELON_LEMONADE = ITEMS.register("label_watermelon_lemonade",
        () -> new Item(new Item.Properties()));

    // Citron
    public static final DeferredItem<Item> EMPTY_CITRON_LEMONADE_BOTTLE = ITEMS.register("empty_citron_lemonade_bottle",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CITRON_LEMONADE_BOTTLE = ITEMS.register("citron_lemonade_bottle",
        () -> new LemonadeDrinkItem(AllFoodProperties.lemonadeDrinks(ModItems.EMPTY_CITRON_LEMONADE_BOTTLE)));

    // Orange
    public static final DeferredItem<Item> EMPTY_ORANGE_LEMONADE_BOTTLE = ITEMS.register("empty_orange_lemonade_bottle",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORANGE_LEMONADE_BOTTLE = ITEMS.register("orange_lemonade_bottle",
        () -> new LemonadeDrinkItem(AllFoodProperties.lemonadeDrinks(ModItems.EMPTY_ORANGE_LEMONADE_BOTTLE)));

    // Raspberry
    public static final DeferredItem<Item> EMPTY_RASPBERRY_LEMONADE_BOTTLE = ITEMS.register("empty_raspberry_lemonade_bottle",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RASPBERRY_LEMONADE_BOTTLE = ITEMS.register("raspberry_lemonade_bottle",
        () -> new LemonadeDrinkItem(AllFoodProperties.lemonadeDrinks(ModItems.EMPTY_RASPBERRY_LEMONADE_BOTTLE)));

    // Summer Mix
    public static final DeferredItem<Item> EMPTY_SUMMERMIX_LEMONADE_BOTTLE = ITEMS.register("empty_summermix_lemonade_bottle",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SUMMERMIX_LEMONADE_BOTTLE = ITEMS.register("summermix_lemonade_bottle",
        () -> new LemonadeDrinkItem(AllFoodProperties.lemonadeDrinks(ModItems.EMPTY_SUMMERMIX_LEMONADE_BOTTLE)));

    // Watermelon
    public static final DeferredItem<Item> EMPTY_WATERMELON_LEMONADE_BOTTLE = ITEMS.register("empty_watermelon_lemonade_bottle",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WATERMELON_LEMONADE_BOTTLE = ITEMS.register("watermelon_lemonade_bottle",
        () -> new LemonadeDrinkItem(AllFoodProperties.lemonadeDrinks(ModItems.EMPTY_WATERMELON_LEMONADE_BOTTLE)));

    // Plants
    public static final DeferredItem<Item> CITRON = ITEMS.register("citron",
        () -> new Item(new Item.Properties().food(AllFoodProperties.CITRON)));
    public static final DeferredItem<Item> CITRON_SLICE = ITEMS.register("citron_slice",
        () -> new Item(new Item.Properties().food(AllFoodProperties.CITRON_SLICE)));
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange",
        () -> new Item(new Item.Properties().food(AllFoodProperties.ORANGE)));
    public static final DeferredItem<Item> ORANGE_SLICE = ITEMS.register("orange_slice",
        () -> new Item(new Item.Properties().food(AllFoodProperties.ORANGE_SLICE)));
    public static final DeferredItem<Item> RASPBERRY = ITEMS.register("raspberry",
        () -> new ItemNameBlockItem(ModBlocks.RASPBERRY_BUSH.get(), new Item.Properties().food(AllFoodProperties.RASPBERRY)));

    // Wood Items
    public static final DeferredItem<SignItem> CITRON_SIGN = ITEMS.register("citron_sign",
        () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.CITRON_SIGN.get(), ModBlocks.CITRON_WALL_SIGN.get()));
    public static final DeferredItem<HangingSignItem> CITRON_HANGING_SIGN = ITEMS.register("citron_hanging_sign",
        () -> new HangingSignItem(ModBlocks.CITRON_HANGING_SIGN.get(), ModBlocks.CITRON_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> CITRON_BOAT = ITEMS.register("citron_boat",
        () -> new ModBoatItem(false, ModBoatEntity.Type.CITRON, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CITRON_CHEST_BOAT = ITEMS.register("citron_chest_boat",
        () -> new ModBoatItem(true, ModBoatEntity.Type.CITRON, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<SignItem> ORANGE_SIGN = ITEMS.register("orange_sign",
        () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.ORANGE_SIGN.get(), ModBlocks.ORANGE_WALL_SIGN.get()));
    public static final DeferredItem<HangingSignItem> ORANGE_HANGING_SIGN = ITEMS.register("orange_hanging_sign",
        () -> new HangingSignItem(ModBlocks.ORANGE_HANGING_SIGN.get(), ModBlocks.ORANGE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> ORANGE_BOAT = ITEMS.register("orange_boat",
        () -> new ModBoatItem(false, ModBoatEntity.Type.ORANGE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ORANGE_CHEST_BOAT = ITEMS.register("orange_chest_boat",
        () -> new ModBoatItem(true, ModBoatEntity.Type.ORANGE, new Item.Properties().stacksTo(1)));

    // Straws
    // 2 = rainbow, 3 = trans, 4 = nonbinary, 5 = lesbian, 6 = gay, 7 = genderfluid, 8 = ace, 9 = aro, 10 = agender, 11 = aroace, 12 = bisexual, 13 = pansexual, 14 = Germany
    public static final DeferredItem<Item> STRAW_BASIC = ITEMS.register("straw_basic",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_RAINBOW = ITEMS.register("straw_rainbow",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_TRANS = ITEMS.register("straw_trans",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_NONBINARY = ITEMS.register("straw_nonbinary",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_LESBIAN = ITEMS.register("straw_lesbian",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_GAY = ITEMS.register("straw_gay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_GENDERFLUID = ITEMS.register("straw_genderfluid",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_ACE = ITEMS.register("straw_ace",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_ARO = ITEMS.register("straw_aro",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_AROACE = ITEMS.register("straw_aroace",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_AGENDER = ITEMS.register("straw_agender",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_BI = ITEMS.register("straw_bi",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_PAN = ITEMS.register("straw_pan",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_GERMAN = ITEMS.register("straw_german",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STRAW_OMNISEXUAL = ITEMS.register("straw_omnisexual",
            () -> new Item(new Item.Properties()));

    // Umbrellas
    // Red-White
    public static final DeferredItem<Item> DRINK_UMBRELLA_1 = ITEMS.register("drink_umbrella_1",
            () -> new Item(new Item.Properties()));
    // Yellow-White
    public static final DeferredItem<Item> DRINK_UMBRELLA_2 = ITEMS.register("drink_umbrella_2",
            () -> new Item(new Item.Properties()));
    // Black-Purple
    public static final DeferredItem<Item> DRINK_UMBRELLA_3 = ITEMS.register("drink_umbrella_3",
            () -> new Item(new Item.Properties()));
    // Orange-White
    public static final DeferredItem<Item> DRINK_UMBRELLA_4 = ITEMS.register("drink_umbrella_4",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TERRACOTTA_LEMONADE_JUG = ITEMS.register("terracotta_lemonade_jug",
            () -> new ModJugItem(ModBlocks.TERRACOTTA_LEMONADE_JUG.get(), new Item.Properties().stacksTo(1)));

    // Serving related
    public static final DeferredItem<Item> ICE_CUBES = ITEMS.register("ice_cubes",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
         ITEMS.register(eventBus);
    }
}
