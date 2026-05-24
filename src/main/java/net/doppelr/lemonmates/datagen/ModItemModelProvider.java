package net.doppelr.lemonmates.datagen;


import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.fluid.ModFluids;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LemonMates.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CITRON.get());
        basicItem(ModItems.CITRON_SLICE.get());
        saplingItem(ModBlocks.CITRON_SAPLING);
        buttonItem(ModBlocks.CITRON_BUTTON, ModBlocks.CITRON_PLANKS);
        fenceItem(ModBlocks.CITRON_FENCE, ModBlocks.CITRON_PLANKS);
        basicItem(ModBlocks.CITRON_DOOR.asItem());
        basicItem(ModItems.CITRON_SIGN.get());
        basicItem(ModItems.CITRON_HANGING_SIGN.get());
        basicItem(ModItems.CITRON_BOAT.get());
        basicItem(ModItems.CITRON_CHEST_BOAT.get());

        basicItem(ModItems.ORANGE.get());
        basicItem(ModItems.ORANGE_SLICE.get());
        saplingItem(ModBlocks.ORANGE_SAPLING);
        buttonItem(ModBlocks.ORANGE_BUTTON, ModBlocks.ORANGE_PLANKS);
        fenceItem(ModBlocks.ORANGE_FENCE, ModBlocks.ORANGE_PLANKS);
        basicItem(ModBlocks.ORANGE_DOOR.asItem());
        basicItem(ModItems.ORANGE_SIGN.get());
        basicItem(ModItems.ORANGE_HANGING_SIGN.get());
        basicItem(ModItems.ORANGE_BOAT.get());
        basicItem(ModItems.ORANGE_CHEST_BOAT.get());

        basicItem(ModItems.RASPBERRY.get());

        basicItem(ModItems.SEED_FLOUR.get());
        basicItem(ModItems.STARCH.get());
        basicItem(ModItems.BIOPLASTIC_MASS.get());
        basicItem(ModItems.BROKEN_BOTTLE.get());

        basicItem(ModItems.PREFORM_MOLD.get());
        basicItem(ModItems.PREFORM_MOLD_WITH_PREFORM.get());
        basicItem(ModItems.PREFORM.get());
        basicItem(ModItems.BOTTLE_MOLD.get());
        basicItem(ModItems.BOTTLE_MOLD_WITH_PREFORM.get());
        basicItem(ModItems.BOTTLE_MOLD_WITH_BOTTLE.get());
        basicItem(ModItems.CAP_MOLD.get());
        basicItem(ModItems.CAP_MOLD_WITH_CAP.get());

        lemonadeBottleItem(ModItems.CITRON_LEMONADE_BOTTLE, "citron");
        lemonadeBottleItem(ModItems.ORANGE_LEMONADE_BOTTLE, "orange");
        lemonadeBottleItem(ModItems.RASPBERRY_LEMONADE_BOTTLE, "raspberry");
        lemonadeBottleItem(ModItems.SUMMERMIX_LEMONADE_BOTTLE, "summermix");
        lemonadeBottleItem(ModItems.WATERMELON_LEMONADE_BOTTLE, "watermelon");

        emptyLemonadeBottleItem(ModItems.EMPTY_CITRON_LEMONADE_BOTTLE, "citron");
        emptyLemonadeBottleItem(ModItems.EMPTY_ORANGE_LEMONADE_BOTTLE, "orange");
        emptyLemonadeBottleItem(ModItems.EMPTY_RASPBERRY_LEMONADE_BOTTLE, "raspberry");
        emptyLemonadeBottleItem(ModItems.EMPTY_SUMMERMIX_LEMONADE_BOTTLE, "summermix");
        emptyLemonadeBottleItem(ModItems.EMPTY_WATERMELON_LEMONADE_BOTTLE, "watermelon");

        incompleteLemonadeBottleItem(ModItems.INCOMPLETE_CITRON_LEMONADE, "citron");
        incompleteLemonadeBottleItem(ModItems.INCOMPLETE_ORANGE_LEMONADE, "orange");
        incompleteLemonadeBottleItem(ModItems.INCOMPLETE_RASPBERRY_LEMONADE, "raspberry");
        incompleteLemonadeBottleItem(ModItems.INCOMPLETE_SUMMERMIX_LEMONADE, "summermix");
        incompleteLemonadeBottleItem(ModItems.INCOMPLETE_WATERMELON_LEMONADE, "watermelon");

        labelItem(ModItems.LABEL_EMPTY);
        labelItem(ModItems.LABEL_TEXT, "text");
        labelItem(ModItems.LABEL_TEXT_COMPANY, "text", "company");
        labelItem(ModItems.LABEL_CITRON_LEMONADE, "text", "company", "citron");
        labelItem(ModItems.LABEL_ORANGE_LEMONADE, "text", "company", "orange");
        labelItem(ModItems.LABEL_RASPBERRY_LEMONADE, "text", "company", "raspberry");
        labelItem(ModItems.LABEL_SUMMERMIX_LEMONADE, "text", "company", "summermix");
        labelItem(ModItems.LABEL_WATERMELON_LEMONADE, "text", "company", "watermelon");

        basicItem(ModItems.BOTTLE_CAP.get());
        basicItem(ModItems.BOTTLE_EMPTY.get());

        ModFluids.BUCKETS.getEntries().forEach(item -> bucketItem((DeferredHolder<Item, BucketItem>) item));
    }

    private void bucketItem(DeferredHolder<Item, BucketItem> item) {
        BucketItem bucketItem = item.get();
        String parent = item.getRegisteredName().contains("lemonade")
            ? "lemonmates:item/lemonade_bucket"
            : "neoforge:item/bucket_drip";
        withExistingParent(item.getId().getPath(), parent)
            .customLoader(DynamicFluidContainerModelBuilder::begin)
            .fluid(bucketItem.content);
    }

    private void labelItem(DeferredItem<Item> item, String... types) {
        var labelModel = withExistingParent(item.getId().getPath(), mcLoc("item/generated"));
        labelModel.texture("layer0", LemonMates.rl("item/label/empty"));
        int i = 1;
        for (String type: types) {
            labelModel.texture("layer" + i, LemonMates.rl("item/label/" + type));
            i++;
        }
    }

    private void lemonadeBottleItem(DeferredItem<Item> item, String variant) {
        ModelFile bottleDrinkingModel = withExistingParent(item.getId().getPath() + "_drinking", mcLoc("item/generated"))
            .texture("layer0", LemonMates.rl("item/bottle_empty_for_label"))
            .texture("layer1", LemonMates.rl(String.format("item/bottle_fluid/%s", variant)))
            .texture("layer2", LemonMates.rl("item/label/bottle_empty"))
            .texture("layer3", LemonMates.rl("item/label/company"))
            .texture("layer4", LemonMates.rl(String.format("item/label/%s", variant)));
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
            .texture("layer0", LemonMates.rl("item/bottle_empty_cap_for_label"))
            .texture("layer1", LemonMates.rl(String.format("item/bottle_fluid/%s", variant)))
            .texture("layer2", LemonMates.rl("item/label/bottle_empty"))
            .texture("layer3", LemonMates.rl("item/label/company"))
            .texture("layer4", LemonMates.rl(String.format("item/label/%s", variant)))
            .override()
                .predicate(LemonMates.rl("drinking_stage"), 1.0F)
                .model(bottleDrinkingModel);
    }

    private void emptyLemonadeBottleItem(DeferredItem<Item> item, String variant) {
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
            .texture("layer0", LemonMates.rl("item/bottle_empty_cap_for_label"))
            .texture("layer1", LemonMates.rl("item/label/bottle_empty"))
            .texture("layer2", LemonMates.rl("item/label/company"))
            .texture("layer3", LemonMates.rl(String.format("item/label/%s", variant)));
    }

    private void incompleteLemonadeBottleItem(DeferredItem<Item> item, String variant) {
        ModelFile capBottleModel = withExistingParent(item.getId().getPath() + "_cap", mcLoc("item/generated"))
            .texture("layer0", LemonMates.rl(String.format("item/bottle_fluid/%s", variant)))
            .texture("layer1", LemonMates.rl("item/bottle_empty_cap"));
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
            .texture("layer0", LemonMates.rl(String.format("item/bottle_fluid/%s", variant)))
            .texture("layer1", LemonMates.rl("item/bottle_empty"))
            .override()
                .predicate(LemonMates.rl("assembly_step"), 2)
                .model(capBottleModel);
    }

    private void saplingItem(DeferredBlock<SaplingBlock> item) {
        withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(LemonMates.MOD_ID, "block/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<ButtonBlock> button, DeferredBlock<Block> baseBlock) {
        withExistingParent(button.getId().getPath(), mcLoc("block/button_inventory"))
            .texture("texture", ResourceLocation.fromNamespaceAndPath(LemonMates.MOD_ID, "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<FenceBlock> fence, DeferredBlock<Block> baseBlock) {
        withExistingParent(fence.getId().getPath(), mcLoc("block/fence_inventory"))
            .texture("texture", ResourceLocation.fromNamespaceAndPath(LemonMates.MOD_ID, "block/" + baseBlock.getId().getPath()));
    }
}
