package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.EphedraItem;
import com.jetpacker06.CreateBrokenBad.custom.MatchItem;
import com.jetpacker06.CreateBrokenBad.custom.SudafedBoxItem;
import com.jetpacker06.CreateBrokenBad.custom.SudafedItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateBrokenBad.MOD_ID);
    //begin items
    public static final RegistryObject<Item>
    TRAY = ingredient("tray"),
    MATCH = ITEMS.register("match", () -> new MatchItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
    PSEUDOPHEDRINE = ingredient("pseudophedrine"),
    SUDAFED = ITEMS.register("sudafed", () -> new SudafedItem(new Item.Properties().tab(ItemGroup.CREATEBB).food(new FoodProperties.Builder().alwaysEat().build()))),
    SUDAFED_BOX = ITEMS.register("sudafed_box", () -> new SudafedBoxItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
    EPHEDRA = ITEMS.register("ephedra", () -> new EphedraItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
    EPHEDRA_SEEDS = ITEMS.register("ephedra_seeds", () -> new ItemNameBlockItem(AllBlocks.EPHEDRA_CROP_BLOCK.get(), new Item.Properties().tab(ItemGroup.CREATEBB))),
    WHITE_PHOSPHORUS = ingredient("white_phosphorus"),
    RED_PHOSPHORUS = ingredient("red_phosphorus"),
    BRINE = ingredient("brine"),
    IODINE = ingredient("iodine"),
    WHITE_METH_TRAY = ingredient("white_meth_tray"),
    WHITE_METH = ingredient("white_meth"),

    CRUSHED_COPPER = ingredient("crushed_copper"),
    CRUSHED_ZINC = ingredient("crushed_zinc"),
    COPPER_ZINC_CATALYST = ingredient("copper_zinc_catalyst"),
    CYANIDE = ingredient("cyanide"),
    ALUMINOSILICATE_CATALYST = ingredient("aluminosilicate_catalyst", new Item.Properties().stacksTo(1).tab(ItemGroup.CREATEBB)),
    ALUMINOSILICATE_BIT = ingredient("aluminosilicate_bit"),

    NITROGEN = ingredient("nitrogen"),
    BLUE_METH_TRAY = ingredient("blue_meth_tray"),
    BLUE_METH = ingredient("blue_meth", new Item.Properties().tab(ItemGroup.CREATEBB).rarity(Rarity.RARE)),



    LIQUID_METHAMPHETAMINE_BUCKET = bucket("liquid_methamphetamine_bucket", AllFluids.LIQUID_METHAMPHETAMINE),
    LIQUID_BLUE_METHAMPHETAMINE_BUCKET = bucket("liquid_blue_methamphetamine_bucket", AllFluids.LIQUID_BLUE_METHAMPHETAMINE),
    METHYLAMINE_BUCKET = bucket("methylamine_bucket", AllFluids.METHYLAMINE),
    METHANOL_BUCKET = bucket("methanol_bucket", AllFluids.METHANOL),
    HYDROGEN_BUCKET = bucket("hydrogen_bucket", AllFluids.HYDROGEN),
    OXYGEN_BUCKET = bucket("oxygen_bucket", AllFluids.OXYGEN),
    AMMONIA_BUCKET = bucket("ammonia_bucket", AllFluids.AMMONIA),
    PHENYLACETIC_ACID_BUCKET = bucket("phenylacetic_acid_bucket", AllFluids.PHENYLACETIC_ACID),
    ACETIC_ANHYRIDE_BUCKET = bucket("acetic_anhydride_bucket", AllFluids.ACETIC_ANHYDRIDE),
    PHENYLACETONE_BUCKET = bucket("phenylacetone_bucket", AllFluids.PHENYLACETONE);

    //end items
    public static RegistryObject<Item> ingredient(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(ItemGroup.CREATEBB)));
    }
    public static RegistryObject<Item> bucket(String name, RegistryObject<FlowingFluid> fluid) {
        return ITEMS.register(name, () -> new BucketItem(fluid, new Item.Properties().tab(ItemGroup.CREATEBB).craftRemainder(Items.BUCKET)));
    }
    public static RegistryObject<Item> ingredient(String name, Item.Properties pProperties) {
        return ITEMS.register(name, () -> new Item(pProperties));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}