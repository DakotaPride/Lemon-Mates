package net.doppelr.lemonmates.block.entity;

import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LemonMates.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> SIGN =
        BLOCK_ENTITIES.register("mod_sign", () ->
            BlockEntityType.Builder.of(ModSignBlockEntity::new,
                ModBlocks.ORANGE_SIGN.get(), ModBlocks.ORANGE_WALL_SIGN.get(),
                ModBlocks.CITRON_SIGN.get(), ModBlocks.CITRON_WALL_SIGN.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> HANGING_SIGN =
        BLOCK_ENTITIES.register("mod_hanging_sign", () ->
            BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                ModBlocks.ORANGE_HANGING_SIGN.get(), ModBlocks.ORANGE_WALL_HANGING_SIGN.get(),
                ModBlocks.CITRON_HANGING_SIGN.get(), ModBlocks.CITRON_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
