package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.*;
import com.tterrag.registrate.Registrate;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod("createbb")
public class CreateBrokenBad {
    public static final String MOD_ID = "createbb";
    public CreateBrokenBad() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registrate registrate = Registrate.create(MOD_ID);

        CBBBlocks.register(registrate);
        CBBItems.register(registrate);
        CBBFluids.register(registrate);
        CBBBlockEntities.register(registrate);

        AllSoundEvents.register(eventBus);
        CBBEffects.register(eventBus);
        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA_SEEDS.get(), 0.65f);
        });
        
        AllCustomTriggerAdvancements.register();
    }
}