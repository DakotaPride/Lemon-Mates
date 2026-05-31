package net.doppelr.lemonmates;

import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

public class LemonMatesTooltipUtils {

    public static void createCustomTooltip(String id, boolean hasConditionalBehaviour, List<Component> components, Object... args) {
        createShiftText(components);
        if (Screen.hasShiftDown()) {
            components.add(empty());
            components.addAll(getBasicSummary(id));
            if (hasConditionalBehaviour) {
                createConditionalBehaviourTooltip(id, 1, components, args);
            }
        }
    }

    public static void createShiftText(List<Component> components) {
        if (!Screen.hasShiftDown())
            components.add(Component.translatable("create.tooltip.holdForDescription", Component.translatable("create.tooltip.keyShift").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY));
        else
            components.add(Component.translatable("create.tooltip.holdForDescription", Component.translatable("create.tooltip.keyShift").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.DARK_GRAY));
    }

    public static void add(MutableComponent component, List<Component> components) {
        add(component, components, true, true, false);
    }

    public static void add(MutableComponent component, List<Component> components, boolean hasSpaceAbove, boolean hasSpaceBelow, boolean showWithoutShifting) {
        if (Screen.hasShiftDown() || showWithoutShifting) {
            if (hasSpaceAbove)
                components.add(empty());
            components.addAll(createGeneric(component));
            if (hasSpaceBelow)
                components.add(empty());
        }
    }

    public static List<Component> createGeneric(MutableComponent component) {
        return textComponentIndentHelper(component, FontHelper.Palette.STANDARD_CREATE, 1);
    }

    public static void createAdditionalConditionalBehaviourTooltip(String id, int sequence, List<Component> components, Object... args) {
        if (Screen.hasShiftDown()) {
            components.addAll(getCondition(id, sequence, args));
            components.addAll(getBehaviour(id, sequence, args));
        }
    }

    public static void createConditionalBehaviourTooltip(String id, int sequence, List<Component> components, Object... args) {
        components.add(empty());
        components.addAll(getCondition(id, sequence, args));
        components.addAll(getBehaviour(id, sequence, args));
    }

    public static MutableComponent empty() {
        return Component.literal("");
    }

    public static List<Component> getBasicSummary(String id) {
        return FontHelper.cutTextComponent(Component.translatable(id + ".tooltip.summary"), FontHelper.Palette.STANDARD_CREATE);
    }

    public static List<Component> getCondition(String id, int sequence, Object... args) {
        return FontHelper.cutTextComponent(Component.translatable(id + ".tooltip.condition" + sequence, args), FontHelper.Palette.GRAY);
    }

    public static List<Component> getBehaviour(String id, int sequence, Object... args) {
        return textComponentIndentHelper(Component.translatable(id + ".tooltip.behaviour" + sequence, args), FontHelper.Palette.STANDARD_CREATE, 1);
    }

    public static List<Component> textComponentIndentHelper(Component component, FontHelper.Palette palette, int indent) {
        return FontHelper.cutTextComponent(component, palette.primary(), palette.highlight(), indent);
    }

}
