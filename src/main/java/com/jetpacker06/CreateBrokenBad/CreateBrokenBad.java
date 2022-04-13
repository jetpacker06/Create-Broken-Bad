package com.jetpacker06.CreateBrokenBad;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("createbb")
public class CreateBrokenBad {
    public static final String MOD_ID = "createbb";
    public CreateBrokenBad() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AllItems.register(eventBus);
        AllBlocks.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
