package net.doppelr.lemonmates;

import com.mojang.logging.LogUtils;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.block.entity.ModBlockEntities;
import net.doppelr.lemonmates.datagen.DataGenerators;
import net.doppelr.lemonmates.entity.ModEntities;
import net.doppelr.lemonmates.fluid.ModFluids;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(LemonMates.MOD_ID)
public class LemonMates {
    public static final String MOD_ID = "lemonmates";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LemonMates(IEventBus modEventBus, ModContainer modContainer) {
        AllCreativeModeTabs.register(modEventBus);
        AllDataComponents.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModEntities.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);
    }

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
