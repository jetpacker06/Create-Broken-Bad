package com.jetpacker06.CreateBrokenBad;

import com.jetpacker06.CreateBrokenBad.register.*;
import com.tterrag.registrate.Registrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
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
    }
}
