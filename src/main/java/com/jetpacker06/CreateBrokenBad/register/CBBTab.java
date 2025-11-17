package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CBBTab {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBrokenBad.MOD_ID);

    public static final Supplier<CreativeModeTab> CREATEBB = TABS.register("createbb", () -> CreativeModeTab.builder()
            .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getId())
            .icon(CBBItems.BLUE_METH.get()::getDefaultInstance)
            .title(Component.translatable("creativetab.createbb.createbb"))
            .displayItems(((itemDisplayParameters, output) -> {
                for (Item item : net.minecraft.core.registries.BuiltInRegistries.ITEM) {
                    if (item.getDescriptionId().contains("createbb") && !item.getDescriptionId().contains("fluid"))
                        output.accept(item);
                }
            }))
            .build());


    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
    public static void log(Object msg) {
        CreateBrokenBad.log(msg);
    }
}
