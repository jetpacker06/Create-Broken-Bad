package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.item.MatchItem;
import com.jetpacker06.CreateBrokenBad.item.MethItem;
import com.jetpacker06.CreateBrokenBad.item.NonConsumedCatalystItem;
import com.jetpacker06.CreateBrokenBad.item.ToolTippedItem;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

public class CBBItems {

    public static ItemEntry<MatchItem> MATCH;
    public static ItemEntry<Item> PACKED_FLOUR;
    public static ItemEntry<Item> POPCORNERS;
    public static ItemEntry<Item> PSEUDOPHEDRINE;
    public static ItemEntry<ToolTippedItem> SUDAFED;
    public static ItemEntry<ToolTippedItem> SUDAFED_BOX;
    public static ItemEntry<ToolTippedItem> EPHEDRA;
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
    public static ItemEntry<Item> ALUMINOSILICATE_CHUNK;
    public static ItemEntry<Item> ALUMINOSILICATE_BIT;
    public static ItemEntry<Item> NITROGEN;
    public static ItemEntry<MethItem.Blue> BLUE_METH;

    public static void register(Registrate REGISTRATE) {

        POPCORNERS = REGISTRATE.item("popcorners", Item::new)
                .properties(p -> p.food(new FoodProperties.Builder().nutrition(2).saturationModifier(1f).fast().build()))
                .register();
        PACKED_FLOUR = REGISTRATE.item("packed_flour", Item::new)
                .register();
        PSEUDOPHEDRINE = REGISTRATE.item("pseudophedrine", Item::new)
                .register();
        MATCH = REGISTRATE.item("match", MatchItem::new)
                .register();
        SUDAFED = REGISTRATE.item("sudafed", p -> new ToolTippedItem("sudafed_tooltip", p))
                .register();
        SUDAFED_BOX = REGISTRATE.item("sudafed_box", p -> new ToolTippedItem("sudafed_box_tooltip", p))
                .register();
        EPHEDRA = REGISTRATE.item("ephedra", p -> new ToolTippedItem("ephedra_tooltip", p))
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
                .register();

        CRUSHED_COPPER = REGISTRATE.item("crushed_copper", Item::new)
                .register();
        CRUSHED_ZINC = REGISTRATE.item("crushed_zinc", Item::new)
                .register();
        COPPER_ZINC_CATALYST = REGISTRATE.item("copper_zinc_catalyst", NonConsumedCatalystItem::new)
                .register();
        CYANIDE = REGISTRATE.item("cyanide", Item::new)
                .properties(p -> p.food(new FoodProperties.Builder()
                        .alwaysEdible()
                        .nutrition(1)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 60, 4), 1)
                        .build()
                ))
                .register();
        ALUMINOSILICATE_CATALYST = REGISTRATE.item("aluminosilicate_catalyst", NonConsumedCatalystItem::new)
                .register();
        ALUMINOSILICATE_CHUNK = REGISTRATE.item("aluminosilicate_chunk", Item::new)
                .register();
        ALUMINOSILICATE_BIT = REGISTRATE.item("aluminosilicate_bit", Item::new)
                .register();
        NITROGEN = REGISTRATE.item("nitrogen", Item::new)
                .register();
        BLUE_METH = REGISTRATE.item("blue_meth", MethItem.Blue::new)
                .register();
    }
}
