package com.jetpacker06.CreateBrokenBad.item;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.block.AllBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateBrokenBad.MOD_ID);
    //begin items
    //public static final
    public static final RegistryObject<Item>
            EPHEDRA_SEEDS = ITEMS.register("ephedra_seeds", () -> new ItemNameBlockItem(AllBlocks.EPHEDRA_CROP_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64))),
            EPHEDRA_JUICE = ingredient("ephedra_juice"),
            EPHEDRA = ingredient("ephedra");

    //end items
    public static final RegistryObject<Item> ingredient(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }
    public static final RegistryObject<Item> ingredient(String name, Item.Properties pProperties) {
        return ITEMS.register(name, () -> new Item(pProperties));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}