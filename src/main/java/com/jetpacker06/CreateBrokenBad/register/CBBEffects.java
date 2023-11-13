package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.effects.AddictionEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class CBBEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CreateBrokenBad.MOD_ID);
    public static RegistryObject<AddictionEffect> ADDICTION = EFFECTS.register("addiction", () ->
        new AddictionEffect(MobEffectCategory.HARMFUL, 2134687)
    );

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
