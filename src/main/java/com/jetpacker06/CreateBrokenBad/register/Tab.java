package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;

//@EventBusSubscriber
public class Tab {
    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBrokenBad.MOD_ID);

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> CREATEBB;

    private static Set<Item> alreadyAdded = new HashSet<>();
    //public static void register(Registrate REGISTRATE, IEventBus eventBus) {
    //    CREATEBB = TAB_REGISTER.register("createbb",
    //            () -> CreativeModeTab.builder()
    //                    .title(Component.translatable("itemGroup.CreateBB"))
    //                    .icon(CBBItems.BLUE_METH::asStack)
    //                    .displayItems((b, output) -> {
    //                        CreateBrokenBad.log("displaying items");
    //                        for (RegistryEntry<Item, Item> item : REGISTRATE.getAll(Registries.ITEM)) {
    //                            if (!alreadyAdded.contains(item.get())) {
    //                                CreateBrokenBad.log("hhhh " + item.get());
    //                                output.accept(item.get());
    //                                alreadyAdded.add(item.get());
    //                            }
    //                        }
    //                    })
    //                    .build());
    //    TAB_REGISTER.register(eventBus);
    //}

    public static RegistryEntry<CreativeModeTab, CreativeModeTab> TAB;
}