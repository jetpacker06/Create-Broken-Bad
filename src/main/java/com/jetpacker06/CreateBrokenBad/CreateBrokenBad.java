package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.AllBlocks;
import com.jetpacker06.CreateBrokenBad.register.AllFluids;
import com.jetpacker06.CreateBrokenBad.register.AllItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
        AllBlocks.register(eventBus);
        AllItems.register(eventBus);
        AllFluids.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void clientSetup(final FMLCommonSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.EPHEDRA_CROP_BLOCK.get(), RenderType.cutout());
    }
}
