package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = CreateBrokenBad.MOD_ID)
public class AllEvents {
    @SubscribeEvent
    public static void addTrade(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        if (event.getType() == VillagerProfession.FARMER) {
            ItemStack inputStack = new ItemStack(Items.EMERALD, 2);
            ItemStack outputStack = new ItemStack(AllItems.EPHEDRA.get(), 8);
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    inputStack,
                    outputStack,
                    10,8,0.02F));
        }
        trades.get(2).add((trader, rand) -> new MerchantOffer(
                new ItemStack(AllItems.WHITE_METH.get()),
                new ItemStack(Items.EMERALD, 2),
                10,8,0.02F));
        trades.get(3).add((trader, rand) -> new MerchantOffer(
                new ItemStack(AllItems.BLUE_METH.get()),
                new ItemStack(Items.EMERALD, 3),
                10,8,0.02F));
    }
}
