package net.doppelr.lemonmates.block;

import net.doppelr.lemonmates.AllBlockStateProperties;
import net.doppelr.lemonmates.AllDataComponents;
import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModJugBlock extends Block {
    private static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 10, 11);

    public static final IntegerProperty JUG_LEVEL = AllBlockStateProperties.JUG_LEVEL;
    public static final EnumProperty<ApplicableFluidsToFluidContainer> FLUID = AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER;
    public static final BooleanProperty CAN_POUR = AllBlockStateProperties.CAN_POUR;
    public ModJugBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(JUG_LEVEL, 0).setValue(FLUID, ApplicableFluidsToFluidContainer.NONE).setValue(CAN_POUR, false));
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            ItemStack itemStack = new ItemStack(this);
            int jugLevel = state.getValue(JUG_LEVEL);
            ApplicableFluidsToFluidContainer fluid = state.getValue(FLUID);
            boolean canPour = state.getValue(CAN_POUR);

            if (jugLevel > 0 || fluid != ApplicableFluidsToFluidContainer.NONE || canPour) {
                DataComponentMap.Builder map = DataComponentMap.builder();
                map.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY
                                .with(AllBlockStateProperties.JUG_LEVEL, jugLevel)
                                .with(AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER, fluid)
                                .with(AllBlockStateProperties.CAN_POUR, canPour))
                        .set(AllDataComponents.JUG_LEVEL, jugLevel)
                        .set(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER, fluid)
                        .set(AllDataComponents.CAN_POUR, canPour);

                itemStack.applyComponents(map.build());
            }

            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(JUG_LEVEL, FLUID, CAN_POUR);
    }
}
