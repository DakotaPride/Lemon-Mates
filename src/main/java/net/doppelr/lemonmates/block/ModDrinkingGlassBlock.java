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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModDrinkingGlassBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<ModDrinkingGlassBlock> CODEC = simpleCodec(ModDrinkingGlassBlock::new);
    private static final VoxelShape SHAPE = Block.box(5.0, 0, 5, 11, 7, 11);

    public static final IntegerProperty DRINK_LEVEL = AllBlockStateProperties.DRINK_LEVEL;
    public static final EnumProperty<ApplicableFluidsToFluidContainer> FLUID = AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER;
    public static final EnumProperty<StrawsVariants> STRAW = AllBlockStateProperties.STRAWS;
    public static final EnumProperty<FruitSlices> FRUIT_SLICE = AllBlockStateProperties.FRUIT_SLICES;
    public static final EnumProperty<UmbrellaVariants> UMBRELLA = AllBlockStateProperties.UMBRELLAS;
    public static final BooleanProperty ICE_CUBES = AllBlockStateProperties.ICE_CUBES;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected ModDrinkingGlassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.SOUTH)
                .setValue(DRINK_LEVEL, 0)
                .setValue(FLUID, ApplicableFluidsToFluidContainer.NONE)
                .setValue(STRAW, StrawsVariants.NONE)
                .setValue(FRUIT_SLICE, FruitSlices.NONE)
                .setValue(UMBRELLA, UmbrellaVariants.NONE)
                .setValue(ICE_CUBES, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
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

    private void getDroppedItem(Item item, BlockPos pos, Player player, Level level) {
        this.getDroppedItem(item, pos, level);
        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOW_GOLEM_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    private void getDroppedItem(Item item, BlockPos pos, Level level) {
        ItemStack itemStack = new ItemStack(item, 1);
        popResource(level, pos, itemStack);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof ModJugItem jugItem && Boolean.TRUE.equals(stack.get(AllDataComponents.CAN_POUR))) {
            if (state.getValue(DRINK_LEVEL) != 2) {
                if (stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) != null || (stack.get(AllDataComponents.JUG_LEVEL) != null && stack.get(AllDataComponents.JUG_LEVEL) != 0)) {
                    int newDrinkLevel = stack.get(AllDataComponents.JUG_LEVEL) > 0 ? 2 - state.getValue(DRINK_LEVEL) : 0;
                    if (stack.get(AllDataComponents.JUG_LEVEL) == 1 || state.getValue(DRINK_LEVEL) == 1)
                        newDrinkLevel = 1;
                    int blockDrinkLevel = state.getValue(DRINK_LEVEL) == 1 ? 2 : newDrinkLevel;
                    ApplicableFluidsToFluidContainer pouredFluid = stack.get(AllDataComponents.JUG_LEVEL) > 0 ? stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) : state.getValue(FLUID);
                    if (state.getValue(FLUID) == ApplicableFluidsToFluidContainer.NONE || state.getValue(DRINK_LEVEL) == 0) {
                        level.setBlockAndUpdate(pos, state.setValue(FLUID, pouredFluid).setValue(DRINK_LEVEL, blockDrinkLevel));
                        jugItem.removeFromJugLevel(stack, player, newDrinkLevel);
                        if (stack.get(AllDataComponents.JUG_LEVEL) > 0)
                            level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            } else {
                return ItemInteractionResult.FAIL;
            }
        }

        if (stack.is(Items.SHEARS)) {
            //BlockPos popPosition = pos.offset(0, 1, 0);
            if (state.getValue(AllBlockStateProperties.STRAWS).getValue() != 0) {
                this.getDroppedItem(state.getValue(AllBlockStateProperties.STRAWS).getRegisteredItem().get(), pos, player, level);
                level.setBlockAndUpdate(pos, state.setValue(AllBlockStateProperties.STRAWS, StrawsVariants.NONE));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            if (state.getValue(AllBlockStateProperties.UMBRELLAS).getValue() != 0) {
                this.getDroppedItem(state.getValue(AllBlockStateProperties.UMBRELLAS).getRegisteredItem().get(), pos, player, level);
                level.setBlockAndUpdate(pos, state.setValue(AllBlockStateProperties.UMBRELLAS, UmbrellaVariants.NONE));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            FruitSlices sliceValue = state.getValue(AllBlockStateProperties.FRUIT_SLICES);
            if (sliceValue != FruitSlices.NONE) {
                if (sliceValue == FruitSlices.CITRON)
                    this.getDroppedItem(ModItems.CITRON_SLICE.get(), pos, player, level);
                if (sliceValue == FruitSlices.ORANGE)
                    this.getDroppedItem(ModItems.ORANGE_SLICE.get(), pos, player, level);
                level.setBlockAndUpdate(pos, state.setValue(AllBlockStateProperties.FRUIT_SLICES, FruitSlices.NONE));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            if (state.getValue(ICE_CUBES)) {
                this.getDroppedItem(ModItems.ICE_CUBES.get(), pos, player, level);
                level.setBlockAndUpdate(pos, state.setValue(AllBlockStateProperties.ICE_CUBES, false));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        for (StrawsVariants variants : StrawsVariants.values())
            if (variants.getValue() != 0 && stack.getItem() == StrawsVariants.byValue(variants.getValue()).getRegisteredItem().get()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                    if (state.getValue(AllBlockStateProperties.STRAWS).getValue() != 0)
                        this.getDroppedItem(state.getValue(AllBlockStateProperties.STRAWS).getRegisteredItem().get(), pos, level);
                }
                level.setBlockAndUpdate(pos, state.setValue(STRAW, StrawsVariants.byValue(variants.getValue())));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

        for (UmbrellaVariants variants : UmbrellaVariants.values())
            if (variants.getValue() != 0 && stack.getItem() == UmbrellaVariants.byValue(variants.getValue()).getRegisteredItem().get()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                    if (state.getValue(AllBlockStateProperties.UMBRELLAS).getValue() != 0)
                        this.getDroppedItem(state.getValue(AllBlockStateProperties.UMBRELLAS).getRegisteredItem().get(), pos, level);
                }
                level.setBlockAndUpdate(pos, state.setValue(UMBRELLA, UmbrellaVariants.byValue(variants.getValue())));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

        // Will need adjusting if more slice types are added - suggested approach: Enum registration similar to straws and umbrellas
        if (stack.is(ModItems.CITRON_SLICE)) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (state.getValue(AllBlockStateProperties.FRUIT_SLICES) != FruitSlices.NONE)
                    this.getDroppedItem(ModItems.ORANGE_SLICE.get(), pos, level);
            }
            level.setBlockAndUpdate(pos, state.setValue(FRUIT_SLICE, FruitSlices.CITRON));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.is(ModItems.ORANGE_SLICE)) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (state.getValue(AllBlockStateProperties.FRUIT_SLICES) != FruitSlices.NONE)
                    this.getDroppedItem(ModItems.CITRON_SLICE.get(), pos, level);
            }
            level.setBlockAndUpdate(pos, state.setValue(FRUIT_SLICE, FruitSlices.ORANGE));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        // Each slice follows this same logic, duplicate as necessary

        if (stack.is(ModItems.ICE_CUBES)) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (state.getValue(AllBlockStateProperties.ICE_CUBES))
                    this.getDroppedItem(ModItems.ICE_CUBES.get(), pos, level);
            }
            level.setBlockAndUpdate(pos, state.setValue(ICE_CUBES, true));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public void destroyReaction(Level level, BlockPos pos, BlockState state) {
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
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        this.destroyReaction(level, pos, state);
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        BlockItemStateProperties dataComponentType = stack.get(DataComponents.BLOCK_STATE);
        if (dataComponentType != null)
            if ((dataComponentType.get(AllBlockStateProperties.STRAWS) != null && dataComponentType.get(AllBlockStateProperties.STRAWS) != StrawsVariants.NONE)
                    || (dataComponentType.get(AllBlockStateProperties.FRUIT_SLICES) != null && dataComponentType.get(AllBlockStateProperties.FRUIT_SLICES) != FruitSlices.NONE)
                    || (dataComponentType.get(AllBlockStateProperties.UMBRELLAS) != null  && dataComponentType.get(AllBlockStateProperties.UMBRELLAS) != UmbrellaVariants.NONE))
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
        builder.add(FACING, STRAW, FRUIT_SLICE, UMBRELLA, DRINK_LEVEL, ICE_CUBES, FLUID, WATERLOGGED);
    }
}
