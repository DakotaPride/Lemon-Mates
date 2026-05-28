package net.doppelr.lemonmates.block;

import com.mojang.serialization.MapCodec;
import net.doppelr.lemonmates.AllBlockStateProperties;
import net.doppelr.lemonmates.AllDataComponents;
import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.doppelr.lemonmates.block.properties.FruitSlices;
import net.doppelr.lemonmates.block.properties.StrawsVariants;
import net.doppelr.lemonmates.block.properties.UmbrellaVariants;
import net.doppelr.lemonmates.item.ModItems;
import net.doppelr.lemonmates.item.ModJugItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModDrinkingGlassBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<ModDrinkingGlassBlock> CODEC = simpleCodec(ModDrinkingGlassBlock::new);
    private static final VoxelShape SHAPE = Block.box(5.0, 0, 5, 11, 7, 11);

    public static final IntegerProperty DRINK_LEVEL = AllBlockStateProperties.DRINK_LEVEL;
    public static final EnumProperty<ApplicableFluidsToFluidContainer> FLUID = AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER;
    public static final EnumProperty<StrawsVariants> STRAW = AllBlockStateProperties.STRAWS;
    public static final EnumProperty<FruitSlices> FRUIT_SLICE = AllBlockStateProperties.FRUIT_SLICES;
    public static final EnumProperty<UmbrellaVariants> UMBRELLA = AllBlockStateProperties.UMBRELLAS;
    public static final BooleanProperty ICE_CUBES = AllBlockStateProperties.ICE_CUBES;

    protected ModDrinkingGlassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.SOUTH)
                .setValue(DRINK_LEVEL, 0)
                .setValue(FLUID, ApplicableFluidsToFluidContainer.NONE)
                .setValue(STRAW, StrawsVariants.NONE)
                .setValue(FRUIT_SLICE, FruitSlices.NONE)
                .setValue(UMBRELLA, UmbrellaVariants.NONE)
                .setValue(ICE_CUBES, false));
    }

    public void customConsumptionBehaviours(BlockState state, Player player) {}

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (player.getMainHandItem().isEmpty() && state.getValue(DRINK_LEVEL) > 0 && state.getValue(FLUID) != ApplicableFluidsToFluidContainer.NONE) {
            level.setBlockAndUpdate(pos, state.setValue(DRINK_LEVEL, state.getValue(DRINK_LEVEL) - 1));
            level.playSound(player, pos, SoundEvents.GENERIC_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);
            this.customConsumptionBehaviours(state, player);
            player.getFoodData().eat(state.getValue(FLUID).getProperties());
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (state.getValue(ICE_CUBES) && state.getValue(DRINK_LEVEL) == 1)
            level.setBlockAndUpdate(pos, state.setValue(ICE_CUBES, false));

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof ModJugItem jugItem && Boolean.TRUE.equals(stack.get(AllDataComponents.CAN_POUR))) {
            if (state.getValue(DRINK_LEVEL) != 2) {
                if (stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) != null || stack.get(AllDataComponents.JUG_LEVEL) != 0) {
                    int newDrinkLevel = stack.get(AllDataComponents.JUG_LEVEL) > 0 ? 2 - state.getValue(DRINK_LEVEL) : 0;
                    if (stack.get(AllDataComponents.JUG_LEVEL) == 1 || state.getValue(DRINK_LEVEL) == 1)
                        newDrinkLevel = 1;
                    int blockDrinkLevel = state.getValue(DRINK_LEVEL) == 1 ? 2 : newDrinkLevel;
                    ApplicableFluidsToFluidContainer pouredFluid = stack.get(AllDataComponents.JUG_LEVEL) > 0 ? stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) : state.getValue(FLUID);
                    level.setBlockAndUpdate(pos, state.setValue(FLUID, pouredFluid)
                            .setValue(DRINK_LEVEL, blockDrinkLevel));
                    jugItem.removeFromJugLevel(stack, newDrinkLevel);
                }
            } else {
                return ItemInteractionResult.FAIL;
            }
        }

        if (stack.is(ModItems.STRAW_BASIC)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.BASIC));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_RAINBOW)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.RAINBOW));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_TRANS)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.TRANS));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_NONBINARY)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.NONBINARY));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_LESBIAN)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.LESBIAN));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_GAY)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.GAY));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_GENDERFLUID)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.GENDERFLUID));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_ACE)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.ACE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_ARO)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.ARO));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_AROACE)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.AROACE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_AGENDER)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.AGENDER));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_BI)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.BI));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_PAN)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.PAN));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_GERMAN)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.GERMAN));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.STRAW_OMNISEXUAL)) {
            level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.OMNISEXUAL));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        // Each straw follows this same logic, duplicate as necessary

        if (stack.is(ModItems.CITRON_SLICE)) {
            level.setBlockAndUpdate(pos, state.setValue(FRUIT_SLICE, FruitSlices.CITRON));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (stack.is(ModItems.ORANGE_SLICE)) {
            level.setBlockAndUpdate(pos, state.setValue(FRUIT_SLICE, FruitSlices.ORANGE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        // Each slice follows this same logic, duplicate as necessary

        if (stack.is(ModItems.DRINK_UMBRELLA_1)) {
            level.setBlockAndUpdate(pos, state.setValue(UMBRELLA, UmbrellaVariants.RED_WHITE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (stack.is(ModItems.DRINK_UMBRELLA_2)) {
            level.setBlockAndUpdate(pos, state.setValue(UMBRELLA, UmbrellaVariants.YELLOW_WHITE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (stack.is(ModItems.DRINK_UMBRELLA_3)) {
            level.setBlockAndUpdate(pos, state.setValue(UMBRELLA, UmbrellaVariants.BLACK_PURPLE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (stack.is(ModItems.DRINK_UMBRELLA_4)) {
            level.setBlockAndUpdate(pos, state.setValue(UMBRELLA, UmbrellaVariants.ORANGE_WHITE));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        // Each umbrella follows this same logic, duplicate as necessary

        if (stack.is(ModItems.ICE_CUBES)) {
            level.setBlockAndUpdate(pos, state.setValue(ICE_CUBES, true));
            if (!player.getAbilities().instabuild)
                stack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            ItemStack itemStack = new ItemStack(this);
            int drinkLevel = state.getValue(DRINK_LEVEL);
            StrawsVariants straw = state.getValue(STRAW);
            ApplicableFluidsToFluidContainer fluid = state.getValue(FLUID);
            FruitSlices fruitSlice = state.getValue(FRUIT_SLICE);
            UmbrellaVariants umbrella = state.getValue(UMBRELLA);
            boolean iceCubes = state.getValue(ICE_CUBES);

            if (drinkLevel < 2 || straw != StrawsVariants.NONE || fluid != ApplicableFluidsToFluidContainer.NONE || fruitSlice != FruitSlices.NONE || umbrella != UmbrellaVariants.NONE || iceCubes) {
                itemStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY
                        .with(AllBlockStateProperties.DRINK_LEVEL, drinkLevel)
                        .with(AllBlockStateProperties.STRAWS, straw)
                        .with(AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER, fluid)
                        .with(AllBlockStateProperties.FRUIT_SLICES, fruitSlice)
                        .with(AllBlockStateProperties.UMBRELLAS, umbrella)
                        .with(AllBlockStateProperties.ICE_CUBES, iceCubes));
            }

            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (stack.has(DataComponents.BLOCK_STATE))
            tooltipComponents.add(Component.literal("Has Decorations Applied").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, STRAW, FRUIT_SLICE, UMBRELLA, DRINK_LEVEL, ICE_CUBES, FLUID);
    }
}
