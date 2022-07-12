package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.AllSoundEvents;
import com.jetpacker06.CreateBrokenBad.register.*;
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
        AllBlocks.register(eventBus);
        AllItems.register(eventBus);
        AllFluids.register(eventBus);
        AllBlockEntities.register(eventBus);
        AllSoundEvents.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.EPHEDRA_CROP_BLOCK.get(), RenderType.cutout());
        FlowingFluid[] allFluids = {
                AllFluids.LIQUID_METHAMPHETAMINE.get(),
                AllFluids.LIQUID_BLUE_METHAMPHETAMINE.get(),
                AllFluids.METHYLAMINE.get(),
                AllFluids.METHANOL.get(),
                AllFluids.HYDROGEN.get(),
                AllFluids.OXYGEN.get(),
                AllFluids.AMMONIA.get(),
                AllFluids.PHENYLACETIC_ACID.get(),
                AllFluids.ACETIC_ANHYDRIDE.get(),
                AllFluids.PHENYLACETONE.get()
        };
        FlowingFluid[] allFlowingFluids = {
                AllFluids.METHAMPHETAMINE_FLOWING.get(),
                AllFluids.BLUE_METHAMPHETAMINE_FLOWING.get(),
                AllFluids.METHYLAMINE_FLOWING.get(),
                AllFluids.METHANOL_FLOWING.get(),
                AllFluids.HYDROGEN_FLOWING.get(),
                AllFluids.OXYGEN_FLOWING.get(),
                AllFluids.AMMONIA_FLOWING.get(),
                AllFluids.PHENYLACETIC_ACID_FLOWING.get(),
                AllFluids.ACETIC_ANHYDRIDE_FLOWING.get(),
                AllFluids.PHENYLACETONE_FLOWING.get()
        };
        LiquidBlock[] allLiquidBlocks = {
                AllFluids.METHAMPHETAMINE_LIQUID_BLOCK.get(),
                AllFluids.BLUE_METHAMPHETAMINE_LIQUID_BLOCK.get(),
                AllFluids.METHYLAMINE_LIQUID_BLOCK.get(),
                AllFluids.METHANOL_LIQUID_BLOCK.get(),
                AllFluids.HYDROGEN_LIQUID_BLOCK.get(),
                AllFluids.OXYGEN_LIQUID_BLOCK.get(),
                AllFluids.AMMONIA_LIQUID_BLOCK.get(),
                AllFluids.PHENYLACETIC_ACID_LIQUID_BLOCK.get(),
                AllFluids.ACETIC_ANHYDRIDE_LIQUID_BLOCK.get(),
                AllFluids.PHENYLACETONE_LIQUID_BLOCK.get()
        };
        for (int i = 0;i < allFluids.length;i++) {
            ItemBlockRenderTypes.setRenderLayer(allFluids[i], RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(allFlowingFluids[i], RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(allLiquidBlocks[i], RenderType.translucent());
        }
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.BRASS_CALL_BELL.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(AllBlocks.BLUE_METH_TRAY.get(), RenderType.cutout());
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(AllItems.EPHEDRA.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(AllItems.EPHEDRA_SEEDS.get(), 0.65f);
        });
        
        AllCustomTriggerAdvancements.register();
    }
}