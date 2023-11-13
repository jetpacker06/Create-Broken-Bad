package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.item.*;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.util.NonNullFunction;

public class CBBItems {
    public static ItemEntry<MatchItem> MATCH;
    public static ItemEntry<Item> PSEUDOPHEDRINE;
    public static ItemEntry<TooltippedItem> SUDAFED;
    public static ItemEntry<TooltippedItem> SUDAFED_BOX;
    public static ItemEntry<EphedraItem> EPHEDRA;
    public static ItemEntry<ItemNameBlockItem> EPHEDRA_SEEDS;
    public static ItemEntry<Item> WHITE_PHOSPHORUS;
    public static ItemEntry<Item> RED_PHOSPHORUS;
    public static ItemEntry<Item> BRINE;
    public static ItemEntry<Item> IODINE;
    public static ItemEntry<MethItem.White> WHITE_METH;
    public static ItemEntry<Item> CRUSHED_COPPER;
    public static ItemEntry<Item> CRUSHED_ZINC;
    public static ItemEntry<NonConsumedCatalystItem> COPPER_ZINC_CATALYST;
    public static ItemEntry<Item> CYANIDE;
    public static ItemEntry<NonConsumedCatalystItem> ALUMINOSILICATE_CATALYST;
    public static ItemEntry<Item> ALUMINOSILICATE_BIT;
    public static ItemEntry<Item> ALUMINOSILICATE_CHUNK;
    public static ItemEntry<Item> NITROGEN;
    public static ItemEntry<MethItem.Blue> BLUE_METH;

    // Buckets are registered in CBBFluids
    public static ItemEntry<BucketItem> LIQUID_BLUE_METHAMPHETAMINE_BUCKET;
    public static ItemEntry<BucketItem> LIQUID_METHAMPHETAMINE_BUCKET;
    public static ItemEntry<BucketItem> METHYLAMINE_BUCKET;
    public static ItemEntry<BucketItem> METHANOL_BUCKET;
    public static ItemEntry<BucketItem> HYDROGEN_BUCKET;
    public static ItemEntry<BucketItem> OXYGEN_BUCKET;
    public static ItemEntry<BucketItem> AMMONIA_BUCKET;
    public static ItemEntry<BucketItem> PHENYLACETIC_ACID_BUCKET;
    public static ItemEntry<BucketItem> ACETIC_ANHYDRIDE_BUCKET;
    public static ItemEntry<BucketItem> PHENYLACETONE_BUCKET;

    public static void register(Registrate REGISTRATE) {
        REGISTRATE.creativeModeTab(() -> Tab.CREATEBB);

        MATCH = REGISTRATE.item("match", MatchItem::new)
                .register();
        PSEUDOPHEDRINE = REGISTRATE.item("pseudophedrine", Item::new)
                .register();
        SUDAFED = REGISTRATE.item("sudafed", p -> new TooltippedItem("sudafed_tooltip", p))
                .register();
        SUDAFED_BOX = REGISTRATE.item("sudafed_box", p -> new TooltippedItem("sudafed_box_tooltip", p))
                .register();
        EPHEDRA = REGISTRATE.item("ephedra", EphedraItem::new)
                .register();
        EPHEDRA_SEEDS = REGISTRATE.item("ephedra_seeds", p -> new ItemNameBlockItem(CBBBlocks.EPHEDRA_CROP_BLOCK.get(), p))
                .register();
        WHITE_PHOSPHORUS = REGISTRATE.item("white_phosphorus", Item::new)
                .register();
        RED_PHOSPHORUS = REGISTRATE.item("red_phosphorus", Item::new)
                .register();
        BRINE = REGISTRATE.item("brine", Item::new)
                .register();
        IODINE = REGISTRATE.item("iodine", Item::new)
                .register();
        WHITE_METH = REGISTRATE.item("white_meth", MethItem.White::new)
                .properties(x -> new Item.Properties().tab(Tab.CREATEBB).food(new FoodProperties.Builder().fast().alwaysEat().nutrition(0).build()))
                .register();
        CRUSHED_COPPER = REGISTRATE.item("crushed_copper", Item::new)
                .register();
        CRUSHED_ZINC = REGISTRATE.item("crushed_zinc", Item::new)
                .register();
        COPPER_ZINC_CATALYST = REGISTRATE.item("copper_zinc_catalyst", NonConsumedCatalystItem::new)
                .register();
        CYANIDE = REGISTRATE.item("cyanide", Item::new)
                .register();
        ALUMINOSILICATE_CATALYST = REGISTRATE.item("aluminosilicate_catalyst", NonConsumedCatalystItem::new)
                .register();
        ALUMINOSILICATE_BIT = REGISTRATE.item("aluminosilicate_bit", Item::new)
                .register();
        ALUMINOSILICATE_CHUNK = REGISTRATE.item("aluminosilicate_chunk", Item::new)
                .register();
        NITROGEN = REGISTRATE.item("nitrogen", Item::new)
                .register();
        BLUE_METH = REGISTRATE.item("blue_meth", MethItem.Blue::new)
                .properties(x -> new Item.Properties().tab(Tab.CREATEBB).food(new FoodProperties.Builder().fast().alwaysEat().nutrition(0).build()))
                .register();

    }



   // CRUSHED_COPPER = ingredient("crushed_copper"),
   // CRUSHED_ZINC = ingredient("crushed_zinc"),
   // COPPER_ZINC_CATALYST = ITEMS.register("copper_zinc_catalyst", () -> new NonConsumedCatalystItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
   // CYANIDE = ingredient("cyanide"),
   // ALUMINOSILICATE_CATALYST = ITEMS.register("aluminosilicate_catalyst", () -> new NonConsumedCatalystItem(new Item.Properties().tab(ItemGroup.CREATEBB))),
   // ALUMINOSILICATE_BIT = ingredient("aluminosilicate_bit"),
   // ALUMINOSILICATE_CHUNK = ingredient("aluminosilicate_chunk"),
   // NITROGEN = ingredient("nitrogen"),
   // BLUE_METH = ITEMS.register("blue_meth", () -> new MethItem.Blue(new Item.Properties().tab(ItemGroup.CREATEBB))),
//
//
//
   // LIQUID_METHAMPHETAMINE_BUCKET = bucket("liquid_methamphetamine_bucket", CBBFluids.LIQUID_METHAMPHETAMINE),
   // LIQUID_BLUE_METHAMPHETAMINE_BUCKET = bucket("liquid_blue_methamphetamine_bucket", CBBFluids.LIQUID_BLUE_METHAMPHETAMINE),
   // METHYLAMINE_BUCKET = bucket("methylamine_bucket", CBBFluids.METHYLAMINE),
   // METHANOL_BUCKET = bucket("methanol_bucket", CBBFluids.METHANOL),
   // HYDROGEN_BUCKET = bucket("hydrogen_bucket", CBBFluids.HYDROGEN),
   // OXYGEN_BUCKET = bucket("oxygen_bucket", CBBFluids.OXYGEN),
   // AMMONIA_BUCKET = bucket("ammonia_bucket", CBBFluids.AMMONIA),
   // PHENYLACETIC_ACID_BUCKET = bucket("phenylacetic_acid_bucket", CBBFluids.PHENYLACETIC_ACID),
   // ACETIC_ANHYRIDE_BUCKET = bucket("acetic_anhydride_bucket", CBBFluids.ACETIC_ANHYDRIDE),
   // PHENYLACETONE_BUCKET = bucket("phenylacetone_bucket", CBBFluids.PHENYLACETONE);
}