package net.doppelr.lemonmates.datagen;

import net.doppelr.lemonmates.AllTags;
import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.block.properties.StrawsVariants;
import net.doppelr.lemonmates.block.properties.UmbrellaVariants;
import net.doppelr.lemonmates.fluid.ModFluids;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, LemonMates.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(net.minecraft.tags.ItemTags.LOGS).add(
            ModBlocks.ORANGE_LOG.get().asItem(),
            ModBlocks.ORANGE_WOOD.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_LOG.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_WOOD.get().asItem(),
            ModBlocks.CITRON_LOG.get().asItem(),
            ModBlocks.CITRON_WOOD.get().asItem(),
            ModBlocks.STRIPPED_CITRON_LOG.get().asItem(),
            ModBlocks.STRIPPED_CITRON_WOOD.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.LOGS_THAT_BURN).add(
            ModBlocks.ORANGE_LOG.get().asItem(),
            ModBlocks.ORANGE_WOOD.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_LOG.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_WOOD.get().asItem(),
            ModBlocks.CITRON_LOG.get().asItem(),
            ModBlocks.CITRON_WOOD.get().asItem(),
            ModBlocks.STRIPPED_CITRON_LOG.get().asItem(),
            ModBlocks.STRIPPED_CITRON_WOOD.get().asItem()
        );
        tag(Tags.Items.STRIPPED_WOODS).add(
            ModBlocks.STRIPPED_ORANGE_WOOD.get().asItem(),
            ModBlocks.STRIPPED_CITRON_WOOD.get().asItem()
        );
        tag(Tags.Items.STRIPPED_LOGS).add(
            ModBlocks.STRIPPED_ORANGE_LOG.get().asItem(),
            ModBlocks.STRIPPED_CITRON_LOG.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.LEAVES).add(
            ModBlocks.ORANGE_LEAVES.get().asItem(),
            ModBlocks.CITRON_LEAVES.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.SAPLINGS).add(
            ModBlocks.ORANGE_SAPLING.get().asItem(),
            ModBlocks.CITRON_SAPLING.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.PLANKS).add(
            ModBlocks.ORANGE_PLANKS.get().asItem(),
            ModBlocks.CITRON_PLANKS.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.STAIRS).add(
            ModBlocks.ORANGE_STAIRS.get().asItem(),
            ModBlocks.CITRON_STAIRS.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.SLABS).add(
            ModBlocks.ORANGE_SLAB.get().asItem(),
            ModBlocks.CITRON_SLAB.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.BOATS).add(
            ModItems.CITRON_BOAT.get(),
            ModItems.ORANGE_BOAT.get(),
            ModItems.CITRON_CHEST_BOAT.get(),
            ModItems.ORANGE_CHEST_BOAT.get()
        );
        tag(net.minecraft.tags.ItemTags.CHEST_BOATS).add(
            ModItems.CITRON_CHEST_BOAT.get(),
            ModItems.ORANGE_CHEST_BOAT.get()
        );
        tag(AllTags.ItemTags.ORANGE_LOGS).add(
            ModBlocks.ORANGE_LOG.get().asItem(),
            ModBlocks.ORANGE_WOOD.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_LOG.get().asItem(),
            ModBlocks.STRIPPED_ORANGE_WOOD.get().asItem()
        );
        tag(AllTags.ItemTags.CITRON_LOGS).add(
            ModBlocks.CITRON_LOG.get().asItem(),
            ModBlocks.CITRON_WOOD.get().asItem(),
            ModBlocks.STRIPPED_CITRON_LOG.get().asItem(),
            ModBlocks.STRIPPED_CITRON_WOOD.get().asItem()
        );
        tag(com.simibubi.create.AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).addTag(
            AllTags.ItemTags.BOTTLES
        );
        tag(com.simibubi.create.AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).add(
            ModItems.PREFORM_MOLD.get().asItem(),
            ModItems.PREFORM_MOLD_WITH_PREFORM.get().asItem(),
            ModItems.BOTTLE_MOLD.get().asItem(),
            ModItems.BOTTLE_MOLD_WITH_PREFORM.get().asItem(),
            ModItems.BOTTLE_MOLD_WITH_BOTTLE.get().asItem(),

            ModItems.INCOMPLETE_CITRON_LEMONADE.get(),
            ModItems.INCOMPLETE_ORANGE_LEMONADE.get(),
            ModItems.INCOMPLETE_RASPBERRY_LEMONADE.get(),
            ModItems.INCOMPLETE_SUMMERMIX_LEMONADE.get(),
            ModItems.INCOMPLETE_WATERMELON_LEMONADE.get()
        );
        for (StrawsVariants variants : StrawsVariants.values())
            if (variants.getValue() != 0)
                tag(com.simibubi.create.AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).add(variants.getRegisteredItem().get());
        for (UmbrellaVariants variants : UmbrellaVariants.values())
            if (variants.getValue() != 0)
                tag(com.simibubi.create.AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).add(variants.getRegisteredItem().get());
        tag(AllTags.ItemTags.MOLDS).add(
            ModItems.PREFORM_MOLD.get().asItem(),
            ModItems.BOTTLE_MOLD.get().asItem(),
            ModItems.CAP_MOLD.get().asItem()
        );
        tag(AllTags.ItemTags.BOTTLES).add(
            ModItems.BOTTLE_EMPTY.get().asItem(),

            ModItems.CITRON_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_CITRON_LEMONADE_BOTTLE.get(),

            ModItems.ORANGE_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_ORANGE_LEMONADE_BOTTLE.get(),

            ModItems.EMPTY_RASPBERRY_LEMONADE_BOTTLE.get(),
            ModItems.RASPBERRY_LEMONADE_BOTTLE.get(),

            ModItems.SUMMERMIX_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_SUMMERMIX_LEMONADE_BOTTLE.get(),

            ModItems.EMPTY_WATERMELON_LEMONADE_BOTTLE.get(),
            ModItems.WATERMELON_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADE_FRUITS).add(
            ModItems.CITRON_SLICE.get(),
            ModItems.ORANGE_SLICE.get(),
            ModItems.RASPBERRY.get(),
            Items.MELON_SLICE
        );
        tag(AllTags.ItemTags.LEMONADE_LABEL_DYES).add(
            Items.YELLOW_DYE,
            Items.ORANGE_DYE,
            Items.RED_DYE,
            Items.LIME_DYE,
            Items.MAGENTA_DYE
        );
        tag(AllTags.ItemTags.LEMONADES_EMPTY).add(
            ModItems.EMPTY_CITRON_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_ORANGE_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_RASPBERRY_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_SUMMERMIX_LEMONADE_BOTTLE.get(),
            ModItems.EMPTY_WATERMELON_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES).add(
            ModItems.CITRON_LEMONADE_BOTTLE.get(),
            ModItems.ORANGE_LEMONADE_BOTTLE.get(),
            ModItems.RASPBERRY_LEMONADE_BOTTLE.get(),
            ModItems.SUMMERMIX_LEMONADE_BOTTLE.get(),
            ModItems.WATERMELON_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES_CITRON).add(
            ModItems.CITRON_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES_ORANGE).add(
            ModItems.ORANGE_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES_RASPBERRY).add(
            ModItems.RASPBERRY_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES_SUMMERMIX).add(
            ModItems.SUMMERMIX_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LEMONADES_WATERMELON).add(
            ModItems.WATERMELON_LEMONADE_BOTTLE.get()
        );
        tag(AllTags.ItemTags.LABELS_COMPLETE).add(
            ModItems.LABEL_CITRON_LEMONADE.get(),
            ModItems.LABEL_ORANGE_LEMONADE.get(),
            ModItems.LABEL_RASPBERRY_LEMONADE.get(),
            ModItems.LABEL_SUMMERMIX_LEMONADE.get(),
            ModItems.LABEL_WATERMELON_LEMONADE.get()
        );
        tag(AllTags.ItemTags.LABELS).add(
            ModItems.LABEL_EMPTY.get(),
            ModItems.LABEL_TEXT.get(),
            ModItems.LABEL_TEXT_COMPANY.get()
        ).addTag(
            AllTags.ItemTags.LABELS_COMPLETE
        );

        tag(AllTags.ItemTags.BUCKETS_LEMONADES).add(
            ModFluids.CITRON_LEMONADE_BUCKET.get(),
            ModFluids.ORANGE_LEMONADE_BUCKET.get(),
            ModFluids.RASPBERRY_LEMONADE_BUCKET.get(),
            ModFluids.SUMMERMIX_LEMONADE_BUCKET.get(),
            ModFluids.WATERMELON_LEMONADE_BUCKET.get()
        );
        tag(AllTags.ItemTags.C_BUCKETS_PLASTIC).add(
            ModFluids.PLASTIC_BUCKET.get()
        );
        tag(AllTags.ItemTags.C_BUCKETS).addTag(
            AllTags.ItemTags.BUCKETS_LEMONADES).addTag(
            AllTags.ItemTags.C_BUCKETS_PLASTIC
        );
        tag(AllTags.ItemTags.BUCKETS_LEMONADES_CITRON).add(
            ModFluids.CITRON_LEMONADE_BUCKET.get()
        );
        tag(AllTags.ItemTags.BUCKETS_LEMONADES_ORANGE).add(
            ModFluids.ORANGE_LEMONADE_BUCKET.get()
        );
        tag(AllTags.ItemTags.BUCKETS_LEMONADES_RASPBERRY).add(
            ModFluids.RASPBERRY_LEMONADE_BUCKET.get()
        );
        tag(AllTags.ItemTags.BUCKETS_LEMONADES_SUMMERMIX).add(
            ModFluids.SUMMERMIX_LEMONADE_BUCKET.get()
        );
        tag(AllTags.ItemTags.BUCKETS_LEMONADES_WATERMELON).add(
            ModFluids.WATERMELON_LEMONADE_BUCKET.get()
        );
        tag(Tags.Items.DRINKS).addTag(
            AllTags.ItemTags.LEMONADES
        );
        tag(Tags.Items.FOODS).addTag(
            AllTags.ItemTags.LEMONADES
        );
        tag(Tags.Items.FOODS_FRUIT).add(
            ModItems.CITRON.get(),
            ModItems.CITRON_SLICE.get(),
            ModItems.ORANGE.get(),
            ModItems.ORANGE_SLICE.get()
        );
        tag(Tags.Items.FOODS_BERRY).add(
            ModItems.RASPBERRY.get()
        );
    }
}
