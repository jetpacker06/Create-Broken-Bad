package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.SudafedItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.lwjgl.system.CallbackI;

public class AllItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateBrokenBad.MOD_ID);
    //begin items
    public static final RegistryObject<Item>
    EPHEDRA_SEEDS = ITEMS.register("ephedra_seeds", () -> new ItemNameBlockItem(AllBlocks.EPHEDRA_CROP_BLOCK.get(), new Item.Properties().tab(ItemGroup.CREATEBB).stacksTo(64))),
    PSEUDOPHEDRINE = ingredient("pseudophedrine"),
    EPHEDRA = ingredient("ephedra"),
    WHITE_PHOSPHORUS = ingredient("white_phosphorus"),
    RED_PHOSPHORUS = ingredient("red_phosphorus"),
    BRINE = ingredient("brine"),
    IODINE = ingredient("iodine"),
    WHITE_METH = ingredient("white_meth"),
    WHITE_METH_TRAY = ingredient("white_meth_tray", new Item.Properties().tab(ItemGroup.CREATEBB).stacksTo(1).craftRemainder(AllItems.TRAY.get())),
    CRACKED_WHITE_METH_TRAY = ingredient("white_meth_tray"),
    SUDAFED = ITEMS.register("sudafed", () -> new SudafedItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
    TRAY = ingredient("tray"),

    COPPER_ZINC_CATALYST = ingredient("copper_zinc_catalyst"),
    CYANIDE = ingredient("cyanide"),
    ALUMINOSILICATE_CATALYST = ingredient("aluminosilicate_catalyst", new Item.Properties().stacksTo(1).tab(ItemGroup.CREATEBB)),
    ALUMINOSILICATE_BIT = ingredient("aluminosilicate_bit"),
    CRUSHED_COPPER = ingredient("crushed_copper"),
    CRUSHED_ZINC = ingredient("crushed_zinc"),
    NITROGENOUS_VEGETABLE_MATTER = ingredient("nitrogenous_vegetable_matter"),
    BLUE_METH_TRAY = ingredient("blue_meth_tray", new Item.Properties().tab(ItemGroup.CREATEBB).stacksTo(1).craftRemainder(AllItems.TRAY.get())),
    BLUE_METH = ingredient("blue_meth", new Item.Properties().tab(ItemGroup.CREATEBB).rarity(Rarity.RARE)),
    CRACKED_BLUE_METH_TRAY = ingredient("cracked_blue_meth_tray"),

    MATCH = ingredient("match");







    //end items
    public static final RegistryObject<Item> ingredient(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(ItemGroup.CREATEBB)));
    }
    public static final RegistryObject<Item> bucket(String name, RegistryObject<FlowingFluid> fluid) {
        return ITEMS.register(name, () -> new BucketItem(fluid, new Item.Properties().tab(ItemGroup.CREATEBB)));
    }
    public static final RegistryObject<Item> ingredient(String name, Item.Properties pProperties) {
        return ITEMS.register(name, () -> new Item(pProperties));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}