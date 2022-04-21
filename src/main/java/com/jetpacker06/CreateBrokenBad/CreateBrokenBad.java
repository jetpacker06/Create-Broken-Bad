package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.custom.AllSoundEvents;
import com.jetpacker06.CreateBrokenBad.register.AllBlockEntities;
import com.jetpacker06.CreateBrokenBad.register.AllBlocks;
import com.jetpacker06.CreateBrokenBad.register.AllFluids;
import com.jetpacker06.CreateBrokenBad.register.AllItems;
import com.mojang.datafixers.TypeRewriteRule;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.Flow;


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
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void clientSetup(final FMLCommonSetupEvent event) {
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
    }
}
