package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.custom.BrassCallBellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CreateBrokenBad.MOD_ID);
    public static final RegistryObject<BlockEntityType<BrassCallBellBlockEntity>> BRASS_CALL_BELL =
            BLOCK_ENTITIES.register("brass_call_bell", () ->
                    BlockEntityType.Builder.of(BrassCallBellBlockEntity::new,
                            AllBlocks.BRASS_CALL_BELL.get()).build(null));
    public static void register(IEventBus eventBus) {BLOCK_ENTITIES.register(eventBus);}
}