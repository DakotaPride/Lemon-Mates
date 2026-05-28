package net.doppelr.lemonmates;

import com.mojang.serialization.Codec;
import net.doppelr.lemonmates.block.properties.ApplicableFluidsToFluidContainer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class AllDataComponents {
    private static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LemonMates.MOD_ID);

    public static final DataComponentType<Integer> JUG_LEVEL = register(
            "jug_level",
            builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT)
    );

    public static final DataComponentType<ApplicableFluidsToFluidContainer> APPLICABLE_FLUID_TO_CONTAINER = register(
            "fluid",
            builder -> builder.persistent(ApplicableFluidsToFluidContainer.CODEC).networkSynchronized(ApplicableFluidsToFluidContainer.STREAM_CODEC)
    );

    public static final DataComponentType<Boolean> CAN_POUR = register(
            "can_pour",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        DataComponentType<T> type = builder.apply(DataComponentType.builder()).build();
        DATA_COMPONENTS.register(name, () -> type);
        return type;
    }

    public static void register(IEventBus modEventBus) {
        DATA_COMPONENTS.register(modEventBus);
    }

}
