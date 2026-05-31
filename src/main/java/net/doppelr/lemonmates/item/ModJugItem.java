package net.doppelr.lemonmates.item;

import net.doppelr.lemonmates.AllBlockStateProperties;
import net.doppelr.lemonmates.AllDataComponents;
import net.doppelr.lemonmates.LemonMatesTooltipUtils;
import net.doppelr.lemonmates.block.ModDrinkingGlassBlock;
import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class ModJugItem extends BlockItem {
    public ModJugItem(Block block, Properties properties) {
        super(block, properties);

    }

    public void setPourAbility(ItemStack stack, boolean pour) {
        stack.set(AllDataComponents.CAN_POUR, pour);
    }

    public void setContainedFluid(ItemStack stack, ApplicableFluidsToFluidContainer fluid) {
        stack.set(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER, fluid);
    }

    public void setJugLevel(ItemStack stack, int jugLevel) {
        stack.set(AllDataComponents.JUG_LEVEL, jugLevel);
    }

    public void addToJugLevel(ItemStack stack, int increment) {
        if (stack.get(AllDataComponents.JUG_LEVEL) != null)
            stack.set(AllDataComponents.JUG_LEVEL, stack.get(AllDataComponents.JUG_LEVEL) + increment);
    }

    public void removeFromJugLevel(ItemStack stack, Player player, int decrement) {
        if (!player.getAbilities().instabuild)
            if (stack.get(AllDataComponents.JUG_LEVEL) != null) {
                int f = stack.get(AllDataComponents.JUG_LEVEL) - decrement;
                if (f >= 0 && f <= 8)
                    stack.set(AllDataComponents.JUG_LEVEL, stack.get(AllDataComponents.JUG_LEVEL) - decrement);
            }
    }

    public void jugLevelHandling(ItemStack stack, Player player, ApplicableFluidsToFluidContainer fluid) {
        Level level = player.level();
        if (stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) != fluid)
            this.setContainedFluid(stack, fluid);

        if (stack.get(AllDataComponents.JUG_LEVEL) == null || stack.get(AllDataComponents.JUG_LEVEL) == 0) {
            this.setJugLevel(stack, 4);
            level.playSound(player, player.blockPosition(), SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild)
                player.setItemInHand(InteractionHand.OFF_HAND, ModItems.BOTTLE_EMPTY.toStack());
        } else if (stack.get(AllDataComponents.JUG_LEVEL) <= 4) {
            this.addToJugLevel(stack, 4);
            level.playSound(player, player.blockPosition(), SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild)
                player.setItemInHand(InteractionHand.OFF_HAND, ModItems.BOTTLE_EMPTY.toStack());
        } else if (stack.get(AllDataComponents.JUG_LEVEL) < 8) {
            int incrementJugLevel = 4;
            if (stack.get(AllDataComponents.JUG_LEVEL) > 4)
                incrementJugLevel = 8 - stack.get(AllDataComponents.JUG_LEVEL);
            this.addToJugLevel(stack, incrementJugLevel);
            level.playSound(player, player.blockPosition(), SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild)
                player.setItemInHand(InteractionHand.OFF_HAND, ModItems.BOTTLE_EMPTY.toStack());
        }
    }

    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        BlockState blockstate = this.getBlock().defaultBlockState();
        ApplicableFluidsToFluidContainer fluid = stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) != null ? stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) : ApplicableFluidsToFluidContainer.NONE;
        int jugLevel = stack.get(AllDataComponents.JUG_LEVEL) != null ? stack.get(AllDataComponents.JUG_LEVEL) : 0;
        boolean canPour = stack.get(AllDataComponents.CAN_POUR) != null ? stack.get(AllDataComponents.CAN_POUR) : false;
        return this.canPlace(context, blockstate) ? blockstate
                .setValue(AllBlockStateProperties.APPLICABLE_FLUID_TO_CONTAINER, fluid)
                .setValue(AllBlockStateProperties.JUG_LEVEL, jugLevel)
                .setValue(AllBlockStateProperties.CAN_POUR, canPour) : null;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (stack.get(AllDataComponents.CAN_POUR) != null && stack.get(AllDataComponents.CAN_POUR) && state.getBlock() instanceof ModDrinkingGlassBlock)
            return InteractionResult.SUCCESS;
        return super.useOn(context);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            ItemStack offHandStack = player.getOffhandItem();

            if (player.isShiftKeyDown() && offHandStack.isEmpty()) {
                this.setPourAbility(stack, !Boolean.TRUE.equals(stack.get(AllDataComponents.CAN_POUR)));
            } else if (Boolean.FALSE.equals(stack.get(AllDataComponents.CAN_POUR))) {
                if (stack.get(AllDataComponents.JUG_LEVEL) == null || stack.get(AllDataComponents.JUG_LEVEL) <= 4) {
                    if (offHandStack.is(ModItems.SUMMERMIX_LEMONADE_BOTTLE))
                        this.jugLevelHandling(stack, player, ApplicableFluidsToFluidContainer.SUMMERMIX_LEMONADE);
                    if (offHandStack.is(ModItems.CITRON_LEMONADE_BOTTLE))
                        this.jugLevelHandling(stack, player, ApplicableFluidsToFluidContainer.CITRON_LEMONADE);
                    if (offHandStack.is(ModItems.ORANGE_LEMONADE_BOTTLE))
                        this.jugLevelHandling(stack, player, ApplicableFluidsToFluidContainer.ORANGE_LEMONADE);
                    if (offHandStack.is(ModItems.RASPBERRY_LEMONADE_BOTTLE))
                        this.jugLevelHandling(stack, player, ApplicableFluidsToFluidContainer.RASPBERRY_LEMONADE);
                    if (offHandStack.is(ModItems.WATERMELON_LEMONADE_BOTTLE))
                        this.jugLevelHandling(stack, player, ApplicableFluidsToFluidContainer.WATERMELON_LEMONADE);
                }
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        String id = "block.lemonmates.lemonade_jug_terracotta";
        boolean pour = stack.get(AllDataComponents.CAN_POUR) != null ? stack.get(AllDataComponents.CAN_POUR) : false;
        ApplicableFluidsToFluidContainer applicableFluid = stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) != null ? stack.get(AllDataComponents.APPLICABLE_FLUID_TO_CONTAINER) : ApplicableFluidsToFluidContainer.NONE;
        int level = stack.get(AllDataComponents.JUG_LEVEL) != null ? stack.get(AllDataComponents.JUG_LEVEL): 0;
        Component fluid = Component.translatable("jugFluid.lemonmates." + applicableFluid.getSerializedName());
        LemonMatesTooltipUtils.createCustomTooltip(id, true, tooltipComponents, pour, fluid);
        LemonMatesTooltipUtils.createAdditionalConditionalBehaviourTooltip(id, 2, tooltipComponents, pour, fluid, level);
        LemonMatesTooltipUtils.createAdditionalConditionalBehaviourTooltip(id, 3, tooltipComponents, fluid, level);
    }
}
