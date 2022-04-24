package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.BrassCallBellBlockItem;
import com.jetpacker06.CreateBrokenBad.custom.BrassCallBellBlock;
import com.jetpacker06.CreateBrokenBad.custom.EphedraCropBlock;
import com.jetpacker06.CreateBrokenBad.custom.TrappedBrassCallBellBlock;
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

public class AllBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateBrokenBad.MOD_ID);
    public static final Item.Properties defaultProperties = new Item.Properties().tab(ItemGroup.CREATEBB);
    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <TYPE extends Block> RegistryObject<TYPE> registerCallBellBlock(String name, Supplier<TYPE> block, Item.Properties pProperties) {
        RegistryObject<TYPE> toReturn = BLOCKS.register(name, block);
        registerCallBellBlockItem(name, toReturn, pProperties);
        return toReturn;
    }
    private static <TYPE extends Block> RegistryObject<TYPE> registerTrappedCallBellBlock(String name, Supplier<TYPE> block, Item.Properties pProperties) {
        RegistryObject<TYPE> toReturn = BLOCKS.register(name, block);
        registerTrappedCallBellBlockItem(name, toReturn, pProperties);
        return toReturn;
    }

    public static final RegistryObject<Block> EPHEDRA_CROP_BLOCK = BLOCKS.register("ephedra_crop_block", () -> new EphedraCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS)));
    public static final RegistryObject<Block> BRASS_CALL_BELL = registerCallBellBlock("brass_call_bell", () -> new BrassCallBellBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)), defaultProperties);
    public static final RegistryObject<Block> TRAPPED_BRASS_CALL_BELL = registerTrappedCallBellBlock("trapped_brass_call_bell", () -> new TrappedBrassCallBellBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)), defaultProperties);

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));}
    private static <T extends Block> RegistryObject<Item> registerCallBellBlockItem(String name, RegistryObject<T> block, Item.Properties pProperties) {return AllItems.ITEMS.register(name, () -> new BrassCallBellBlockItem(block.get(), pProperties));}
    private static <T extends Block> RegistryObject<Item> registerTrappedCallBellBlockItem(String name, RegistryObject<T> block, Item.Properties pProperties) {return AllItems.ITEMS.register(name, () -> new BrassCallBellBlockItem(block.get(), pProperties));}
}
