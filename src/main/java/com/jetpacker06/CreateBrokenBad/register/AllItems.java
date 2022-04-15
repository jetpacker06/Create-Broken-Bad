package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.SudafedItem;
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
            PSEUDOPHEDRINE = ingredient("pseudophedrine"),
            EPHEDRA = ingredient("ephedra"),
            WHITE_PHOSPHORUS = ingredient("white_phosphorus"),
            RED_PHOSPHORUS = ingredient("red_phosphorus"),
            BRINE = ingredient("brine"),
            IODINE = ingredient("iodine"),
            WHITE_METH = ingredient("white_meth"),
            SUDAFED = ITEMS.register("sudafed", () -> new SudafedItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

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