package net.doppelr.lemonmates;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.createmod.catnip.lang.FontHelper;
import net.doppelr.lemonmates.block.ModBlocks;
import net.doppelr.lemonmates.block.entity.ModBlockEntities;
import net.doppelr.lemonmates.datagen.DataGenerators;
import net.doppelr.lemonmates.entity.ModEntities;
import net.doppelr.lemonmates.fluid.ModFluids;
import net.doppelr.lemonmates.item.ModItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.w3c.dom.DOMImplementationList;

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

    public static final NonNullSupplier<CreateRegistrate> REGISTRATE =
            NonNullSupplier.lazy(() -> CreateRegistrate.create(MOD_ID));


    static {
        REGISTRATE.get().setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE));
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE.get();
    }

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }


}
