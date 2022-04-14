package com.jetpacker06.CreateBrokenBad.block;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.item.AllItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateBrokenBad.MOD_ID);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;}


    public static final RegistryObject<Block> EPHEDRA_CROP_BLOCK = BLOCKS.register("ephedra_crop_block", () -> new EphedraCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS)));


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));}
    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
