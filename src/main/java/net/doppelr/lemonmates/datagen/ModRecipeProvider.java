package net.doppelr.lemonmates.datagen;

import net.doppelr.lemonmates.AllTags;
import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.CITRON.get())
            .pattern("##")
            .pattern("##")
            .define('#', ModItems.CITRON_SLICE.get())
            .unlockedBy("has_citron_slice", has(ModItems.CITRON_SLICE))
            .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CITRON_SLICE.get(), 4)
            .requires(ModItems.CITRON.get()).unlockedBy("has_citron", has(ModItems.CITRON)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CITRON_CRATE.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', ModItems.CITRON.get())
            .unlockedBy("has_citron", has(ModItems.CITRON))
            .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CITRON.get(), 9)
            .requires(ModBlocks.CITRON_CRATE.get()).unlockedBy("has_citron", has(ModItems.CITRON)).save(recipeOutput, LemonMates.rl("citron_from_crate"));
        planksFromLog(recipeOutput, ModBlocks.CITRON_PLANKS, AllTags.ItemTags.CITRON_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.CITRON_WOOD, ModBlocks.CITRON_LOG);
        woodFromLogs(recipeOutput, ModBlocks.STRIPPED_CITRON_WOOD, ModBlocks.STRIPPED_CITRON_LOG);
        stairBuilder(ModBlocks.CITRON_STAIRS.get(), Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CITRON_SLAB.get(), ModBlocks.CITRON_PLANKS);
        pressurePlate(recipeOutput, ModBlocks.CITRON_PRESSURE_PLATE.get(), ModBlocks.CITRON_PLANKS);
        buttonBuilder(ModBlocks.CITRON_BUTTON.get(), Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        fenceBuilder(ModBlocks.CITRON_FENCE, Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        fenceGateBuilder(ModBlocks.CITRON_FENCE_GATE, Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        doorBuilder(ModBlocks.CITRON_DOOR, Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        trapdoorBuilder(ModBlocks.CITRON_TRAPDOOR, Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        signBuilder(ModBlocks.CITRON_SIGN, Ingredient.of(ModBlocks.CITRON_PLANKS))
            .unlockedBy("has_citron_planks", has(ModBlocks.CITRON_PLANKS)).save(recipeOutput);
        hangingSign(recipeOutput, ModBlocks.CITRON_HANGING_SIGN, ModBlocks.CITRON_PLANKS);
        woodenBoat(recipeOutput, ModItems.CITRON_BOAT, ModBlocks.CITRON_PLANKS);
        chestBoat(recipeOutput, ModItems.CITRON_CHEST_BOAT, ModItems.CITRON_BOAT);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.ORANGE.get())
            .pattern("##")
            .pattern("##")
            .define('#', ModItems.ORANGE_SLICE.get())
            .unlockedBy("has_orange_slice", has(ModItems.ORANGE_SLICE))
            .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORANGE_SLICE.get(), 4)
            .requires(ModItems.ORANGE.get()).unlockedBy("has_orange", has(ModItems.ORANGE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_CRATE.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', ModItems.ORANGE.get())
            .unlockedBy("has_orange", has(ModItems.ORANGE))
            .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORANGE.get(), 9)
            .requires(ModBlocks.ORANGE_CRATE.get()).unlockedBy("has_orange", has(ModItems.ORANGE)).save(recipeOutput, LemonMates.rl("orange_from_crate"));
        planksFromLog(recipeOutput, ModBlocks.ORANGE_PLANKS, AllTags.ItemTags.ORANGE_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.ORANGE_WOOD, ModBlocks.ORANGE_LOG);
        woodFromLogs(recipeOutput, ModBlocks.STRIPPED_ORANGE_WOOD, ModBlocks.STRIPPED_ORANGE_LOG);
        stairBuilder(ModBlocks.ORANGE_STAIRS.get(), Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_SLAB.get(), ModBlocks.ORANGE_PLANKS);
        pressurePlate(recipeOutput, ModBlocks.ORANGE_PRESSURE_PLATE.get(), ModBlocks.ORANGE_PLANKS);
        buttonBuilder(ModBlocks.ORANGE_BUTTON.get(), Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        fenceBuilder(ModBlocks.ORANGE_FENCE, Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        fenceGateBuilder(ModBlocks.ORANGE_FENCE_GATE, Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        doorBuilder(ModBlocks.ORANGE_DOOR, Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        trapdoorBuilder(ModBlocks.ORANGE_TRAPDOOR, Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        signBuilder(ModBlocks.ORANGE_SIGN, Ingredient.of(ModBlocks.ORANGE_PLANKS))
            .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS)).save(recipeOutput);
        hangingSign(recipeOutput, ModBlocks.ORANGE_HANGING_SIGN, ModBlocks.ORANGE_PLANKS);
        woodenBoat(recipeOutput, ModItems.ORANGE_BOAT, ModBlocks.ORANGE_PLANKS);
        chestBoat(recipeOutput, ModItems.ORANGE_CHEST_BOAT, ModItems.ORANGE_BOAT);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RASPBERRY_CRATE.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RASPBERRY.get())
                .unlockedBy("has_raspberry", has(ModItems.RASPBERRY))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RASPBERRY.get(), 9)
                .requires(ModBlocks.RASPBERRY_CRATE.get()).unlockedBy("has_raspberry", has(ModItems.RASPBERRY)).save(recipeOutput);

        // Molds
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BOTTLE_MOLD.get())
                .pattern("#S#")
                .pattern("#S#")
                .pattern("#S#")
                .define('#', Tags.Items.INGOTS_IRON)
                .define('S', AllTags.ItemTags.C_PLATES_IRON)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PREFORM_MOLD.get())
                .pattern("#S#")
                .pattern("#S#")
                .define('#', Tags.Items.INGOTS_IRON)
                .define('S', AllTags.ItemTags.C_PLATES_IRON)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CAP_MOLD.get())
                .pattern("SS")
                .pattern("##")
                .define('#', Tags.Items.INGOTS_IRON)
                .define('S', AllTags.ItemTags.C_PLATES_IRON)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);

        // Lemonade Jug/Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMONADE_JUG_TERRACOTTA.get())
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Tags.Items.BRICKS)
                .unlockedBy("has_bricks", has(Tags.Items.BRICKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMONADE_GLASS.get())
                .pattern("   ")
                .pattern("# #")
                .pattern(" B ")
                .define('#', Tags.Items.GLASS_PANES)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEMONADE_JUG_GLASS.get())
                .pattern("# #")
                .pattern("# #")
                .pattern(" B ")
                .define('#', Tags.Items.GLASS_PANES)
                .define('B', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(recipeOutput);

        // get Molds back
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PREFORM.get(), 1)
            .requires(ModItems.PREFORM_MOLD_WITH_PREFORM.get()).unlockedBy("has_preform_mold", has(ModItems.PREFORM_MOLD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOTTLE_EMPTY.get(), 1)
            .requires(ModItems.BOTTLE_MOLD_WITH_BOTTLE.get()).unlockedBy("has_mold", has(ModItems.BOTTLE_MOLD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOTTLE_CAP.get(), 1)
            .requires(ModItems.CAP_MOLD_WITH_CAP.get()).unlockedBy("has_cap_mold", has(ModItems.CAP_MOLD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LABEL_EMPTY.get(), 1)
            .requires(Items.PAPER).requires(Items.LIME_DYE).unlockedBy("has_paper", has(Items.PAPER)).save(recipeOutput);

    }
}
