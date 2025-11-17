package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = CreateBrokenBad.MOD_ID)
public class AllEvents {
    @SubscribeEvent
    public static void addTrade(VillagerTradesEvent event) {
        var trades = event.getTrades();
        if (event.getType() == VillagerProfession.NITWIT) return;
        if (event.getType() == VillagerProfession.FARMER) {
            ItemCost inputStack = new ItemCost(Items.EMERALD, 2);
            ItemStack outputStack = new ItemStack(CBBItems.EPHEDRA.get(), 8);
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    inputStack,
                    outputStack,
                    10,8,0.02F));
        }
        trades.get(2).add((trader, rand) -> new MerchantOffer(
                new ItemCost(CBBItems.WHITE_METH.get()),
                new ItemStack(Items.EMERALD, 2),
                10,8,0.02F));
        trades.get(3).add((trader, rand) -> new MerchantOffer(
                new ItemCost(CBBItems.BLUE_METH.get()),
                new ItemStack(Items.EMERALD, 3),
                10,8,0.02F));
    }
}
