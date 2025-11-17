package com.jetpacker06.CreateBrokenBad.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NonConsumedCatalystItem extends Item {
    public NonConsumedCatalystItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("not_consumed_tooltip"));
    }

    @Override
    public @NotNull ItemStack getCraftingRemainingItem(@NotNull ItemStack itemStack) {
        return itemStack;
    }
}
