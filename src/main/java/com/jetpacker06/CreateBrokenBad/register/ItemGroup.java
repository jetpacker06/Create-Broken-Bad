package com.jetpacker06.CreateBrokenBad.register;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroup {
    public static final CreativeModeTab CREATEBB = new CreativeModeTab("CreateBB") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AllItems.WHITE_METH.get());
        }
    };
}