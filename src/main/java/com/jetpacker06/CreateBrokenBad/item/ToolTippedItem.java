package com.jetpacker06.CreateBrokenBad.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ToolTippedItem extends Item {
    private final String tooltipKey;

    public ToolTippedItem(String tooltipKey, Properties pProperties) {
        super(pProperties);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable(tooltipKey));
    }
}
