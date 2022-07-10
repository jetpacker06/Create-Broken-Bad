package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class  AllBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateBrokenBad.MOD_ID);
    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    public static final RegistryObject<Block> EPHEDRA_CROP_BLOCK = BLOCKS.register("ephedra_crop_block", () -> new EphedraCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS)));
    public static final RegistryObject<Block> BRASS_CALL_BELL = registerBlock("brass_call_bell", () -> new BrassCallBellBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)), ItemGroup.CREATEBB);
    public static final RegistryObject<Block> TRAPPED_BRASS_CALL_BELL = registerBlock("trapped_brass_call_bell", () -> new TrappedBrassCallBellBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)), ItemGroup.CREATEBB);
    public static final RegistryObject<Block> BLUE_METH_TRAY = registerBlock("blue_meth_tray", () -> new TrayBlock.Blue(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(6).explosionResistance(9).requiresCorrectToolForDrops()), ItemGroup.CREATEBB);
    public static final RegistryObject<Block> TRAY = registerBlock("tray", () -> new TrayBlock.Empty(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(6).explosionResistance(9).requiresCorrectToolForDrops()), ItemGroup.CREATEBB);
    public static final RegistryObject<Block> WHITE_METH_TRAY = registerBlock("white_meth_tray", () -> new TrayBlock.White(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(6).explosionResistance(9).requiresCorrectToolForDrops()), ItemGroup.CREATEBB);

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }}
