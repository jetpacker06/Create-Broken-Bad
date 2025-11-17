package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.*;
import com.tterrag.registrate.Registrate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;


@Mod(CreateBrokenBad.MOD_ID)
public class CreateBrokenBad {
    public static final String MOD_ID = "createbb";

    public static void log(Object msg) {
        LogManager.getLogger(MOD_ID).info(msg);
    }

    public static Registrate REGISTRATE;


    public CreateBrokenBad(IEventBus eventBus) {
        REGISTRATE = Registrate.create(MOD_ID);
        REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

        CBBTab.register(eventBus);

        AllSoundEvents.register(eventBus);
        CBBItems.register(REGISTRATE);
        CBBBlocks.register(REGISTRATE);
        CBBFluids.register(REGISTRATE);
        CBBBlockEntityTypes.register(REGISTRATE);

        CBBAdvancements.register();


        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::onRegister);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        //    ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA.get(), 0.3f);
          //  ComposterBlock.COMPOSTABLES.put(CBBItems.EPHEDRA_SEEDS.get(), 0.65f);
        });

    }
    private void onRegister(final RegisterEvent event) {
        if (event.getRegistry() == BuiltInRegistries.TRIGGER_TYPES) {
            AllCustomTriggerAdvancements.register();
        }
    }
}
