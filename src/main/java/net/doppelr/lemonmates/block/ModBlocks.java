package net.doppelr.lemonmates.block;

import net.doppelr.lemonmates.AllWoodTypes;
import net.doppelr.lemonmates.LemonMates;
import net.doppelr.lemonmates.item.ModItems;
import net.doppelr.lemonmates.worldgen.tree.ModTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(LemonMates.MOD_ID);

    public static final DeferredBlock<Block> ORANGE_LOG = registerBlock("orange_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> ORANGE_WOOD = registerBlock("orange_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_ORANGE_LOG = registerBlock("stripped_orange_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_ORANGE_WOOD = registerBlock("stripped_orange_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<LeavesBlock> ORANGE_LEAVES = registerBlock("orange_leaves",
        () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<SaplingBlock> ORANGE_SAPLING = registerBlock("orange_sapling",
        () -> new SaplingBlock(ModTreeGrowers.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> ORANGE_PLANKS = registerBlock("orange_planks",
        () -> new ModFlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<StairBlock> ORANGE_STAIRS = registerBlock("orange_stairs",
        () -> new StairBlock(ModBlocks.ORANGE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> ORANGE_SLAB = registerBlock("orange_slab",
        () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> ORANGE_PRESSURE_PLATE = registerBlock("orange_pressure_plate",
        () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<ButtonBlock> ORANGE_BUTTON = registerBlock("orange_button",
        () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<FenceBlock> ORANGE_FENCE = registerBlock("orange_fence",
        () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> ORANGE_FENCE_GATE = registerBlock("orange_fence_gate",
        () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<DoorBlock> ORANGE_DOOR = registerBlock("orange_door",
        () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> ORANGE_TRAPDOOR = registerBlock("orange_trapdoor",
        () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> ORANGE_SIGN = BLOCKS.register("orange_sign",
        () -> new ModStandingSignBlock(AllWoodTypes.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> ORANGE_WALL_SIGN = BLOCKS.register("orange_wall_sign",
        () -> new ModWallSignBlock(AllWoodTypes.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> ORANGE_HANGING_SIGN = BLOCKS.register("orange_hanging_sign",
        () -> new ModHangingSignBlock(AllWoodTypes.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> ORANGE_WALL_HANGING_SIGN = BLOCKS.register("orange_wall_hanging_sign",
        () -> new ModWallHangingSignBlock(AllWoodTypes.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final DeferredBlock<Block> CITRON_LOG = registerBlock("citron_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> CITRON_WOOD = registerBlock("citron_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_CITRON_LOG = registerBlock("stripped_citron_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_CITRON_WOOD = registerBlock("stripped_citron_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<LeavesBlock> CITRON_LEAVES = registerBlock("citron_leaves",
        () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<SaplingBlock> CITRON_SAPLING = registerBlock("citron_sapling",
        () -> new SaplingBlock(ModTreeGrowers.CITRON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> CITRON_PLANKS = registerBlock("citron_planks",
        () -> new ModFlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<StairBlock> CITRON_STAIRS = registerBlock("citron_stairs",
        () -> new StairBlock(ModBlocks.CITRON_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> CITRON_SLAB = registerBlock("citron_slab",
        () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> CITRON_PRESSURE_PLATE = registerBlock("citron_pressure_plate",
        () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<ButtonBlock> CITRON_BUTTON = registerBlock("citron_button",
        () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<FenceBlock> CITRON_FENCE = registerBlock("citron_fence",
        () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> CITRON_FENCE_GATE = registerBlock("citron_fence_gate",
        () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<DoorBlock> CITRON_DOOR = registerBlock("citron_door",
        () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> CITRON_TRAPDOOR = registerBlock("citron_trapdoor",
        () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> CITRON_SIGN = BLOCKS.register("citron_sign",
        () -> new ModStandingSignBlock(AllWoodTypes.CITRON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> CITRON_WALL_SIGN = BLOCKS.register("citron_wall_sign",
        () -> new ModWallSignBlock(AllWoodTypes.CITRON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> CITRON_HANGING_SIGN = BLOCKS.register("citron_hanging_sign",
        () -> new ModHangingSignBlock(AllWoodTypes.CITRON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> CITRON_WALL_HANGING_SIGN = BLOCKS.register("citron_wall_hanging_sign",
        () -> new ModWallHangingSignBlock(AllWoodTypes.CITRON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final DeferredBlock<Block> RASPBERRY_BUSH = registerBlock("raspberry_bush",
        () -> new RaspberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    // Crates
    public static final DeferredBlock<Block> ORANGE_CRATE = registerBlock("orange_crate",
        () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> CITRON_CRATE = registerBlock("citron_crate",
        () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> RASPBERRY_CRATE = registerBlock("raspberry_crate",
        () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredBlock<Block> LEMONADE_GLASS = registerBlock("lemonade_glass",
        () -> new ModDrinkingGlassBlock(BlockBehaviour.Properties.of()
                .sound(SoundType.GLASS)
        ));

    public static final DeferredBlock<Block> TERRACOTTA_LEMONADE_JUG = registerBlockWithoutBlockItem("terracotta_lemonade_jug",
            () -> new ModJugBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA).noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutBlockItem(String name, Supplier <T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier <T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
