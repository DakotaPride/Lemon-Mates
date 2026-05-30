package net.doppelr.lemonmates.block.properties;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.createmod.catnip.codecs.stream.CatnipStreamCodecBuilders;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.food.FoodProperties;

import java.util.Locale;

public enum ApplicableFluidsToFluidContainer implements StringRepresentable {
    NONE,
    SUMMERMIX_LEMONADE(new FoodProperties.Builder().build()),
    ORANGE_LEMONADE(new FoodProperties.Builder().build()),
    RASPBERRY_LEMONADE(new FoodProperties.Builder().build()),
    WATERMELON_LEMONADE(new FoodProperties.Builder().build()),
    CITRON_LEMONADE(new FoodProperties.Builder().build()),;

    final FoodProperties properties;
    public static final Codec<ApplicableFluidsToFluidContainer> CODEC = StringRepresentable.fromEnum(ApplicableFluidsToFluidContainer::values);
    public static final StreamCodec<ByteBuf, ApplicableFluidsToFluidContainer> STREAM_CODEC = CatnipStreamCodecBuilders.ofEnum(ApplicableFluidsToFluidContainer.class);

    ApplicableFluidsToFluidContainer() {
        this.properties = new FoodProperties.Builder().build();
    }

    ApplicableFluidsToFluidContainer(FoodProperties properties) {
        this.properties = properties;
    }

    public FoodProperties getProperties() {
        return properties;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
