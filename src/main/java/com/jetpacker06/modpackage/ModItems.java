package com.jetpacker06.modpackage;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModMainClass.MOD_ID);
    //begin items

    //end items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}