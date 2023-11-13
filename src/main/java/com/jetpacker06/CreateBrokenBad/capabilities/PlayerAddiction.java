package com.jetpacker06.CreateBrokenBad.capabilities;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.effects.AddictionEffect;
import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import com.jetpacker06.CreateBrokenBad.register.CBBEffects;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class PlayerAddiction {
    public static final float ADDICTION_THRESHOLD = 0.05f;
    public static final float ADDICTION_DECAY_RATE = 0.0001f;
    public static final float HIGH_DECAY_MULTIPLIER = 0.05f;
    public static final float WITHDRAWAL_BUILDUP_MULTIPLIER = 0.005f;
    public static final float TOLERANCE_DECAY_RATE = 0.0025f;
    public static final float TOXICITY_DECAY_RATE = 0.005f;

    private int updateTicks;
    public float addiction;
    public float withdrawal;
    public float tolerance;
    public long consumptionTicks;
    public float toxicity;

    public PlayerAddiction() {
        reset();
    }

    public void reset() {
        addiction = 0;
        withdrawal = 0;
        tolerance = 0;
        consumptionTicks = 0;
        toxicity = 0;
    }

    public void from(PlayerAddiction other) {
        updateTicks = other.updateTicks;
        addiction = other.addiction;
        withdrawal = 0;
        tolerance = other.tolerance;
        consumptionTicks = other.consumptionTicks;
        toxicity = 0;
    }

    public void serializeNBT(CompoundTag nbt) {
        nbt.putInt("addiction.updateTicks", updateTicks);
        nbt.putFloat("addiction.addiction", addiction);
        nbt.putFloat("addiction.withdrawal", withdrawal);
        nbt.putFloat("addiction.tolerance", tolerance);
        nbt.putLong("addiction.consumptionTicks", consumptionTicks);
        nbt.putFloat("addiction.toxicity", toxicity);
    }

    public void deserializeNBT(CompoundTag nbt) {
        updateTicks = nbt.getInt("addiction.updateTicks");
        addiction = nbt.getFloat("addiction.addiction");
        withdrawal = nbt.getFloat("addiction.withdrawal");
        tolerance = nbt.getFloat("addiction.tolerance");
        consumptionTicks = nbt.getLong("addiction.consumptionTicks");
        toxicity = nbt.getFloat("addiction.toxicity");
    }

    @SubscribeEvent
    public static void addictionTick(TickEvent.PlayerTickEvent event) {
        if (event.side != LogicalSide.SERVER) {
            return;
        }

        AddictionEffect effect = CBBEffects.ADDICTION.get();
        MobEffectInstance effectInstance = event.player.getEffect(effect);

        boolean hasEffect = event.player.hasEffect(effect);
        int duration = effectInstance == null ? 0 : effectInstance.getDuration();
        boolean renewOpportunity = !hasEffect || duration < 20 * 4900;

        event.player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
            if (addiction.updateTicks >= 20) {
                addiction.updateTicks = 0;

                // Apply addiction effect if we have a minimum addiction threshold
                if (addiction.addiction > PlayerAddiction.ADDICTION_THRESHOLD || addiction.withdrawal != 0.0f) {
                    if (renewOpportunity) {
                        event.player.addEffect(new MobEffectInstance(effect, 20 * 5000, 0, false, false));
                    }
                } else {
                    event.player.removeEffect(CBBEffects.ADDICTION.get());
                }

                // Update the addiction status
                addiction.addiction = Math.max(0.0f, addiction.addiction - PlayerAddiction.ADDICTION_DECAY_RATE);

                // The effect wears off faster the higher the tolerance
                if (addiction.withdrawal < 0.0f) {
                    addiction.withdrawal = Math.min(10.0f, addiction.withdrawal + PlayerAddiction.HIGH_DECAY_MULTIPLIER * Math.max(addiction.tolerance, 0.1f));
                } else {
                    // Subtract addiction bias, so that if you fight off the addiction, the withdrawal
                    // will slowly decay over time
                    addiction.withdrawal = Math.max(0.0f, Math.min(10.0f, addiction.withdrawal + PlayerAddiction.WITHDRAWAL_BUILDUP_MULTIPLIER * (addiction.addiction - PlayerAddiction.ADDICTION_THRESHOLD)));
                }

                // 15 minutes after consuming, tolerance starts decaying
                if (addiction.consumptionTicks >= 60 * 15) {
                    addiction.tolerance = Math.max(0.0f, addiction.tolerance - PlayerAddiction.TOLERANCE_DECAY_RATE);
                }
                // 5 after consuming, toxicity starts decaying
                if (addiction.consumptionTicks >= 60 * 5) {
                    addiction.toxicity = Math.max(0.0f, addiction.toxicity - PlayerAddiction.TOXICITY_DECAY_RATE);
                }
                addiction.consumptionTicks++;
            } else {
                addiction.updateTicks++;
            }
        });
    }

    @SubscribeEvent
    public static void attachPlayerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(CBBCapabilities.PLAYER_ADDICTION).isPresent()) {
                event.addCapability(new ResourceLocation(CreateBrokenBad.MOD_ID, "properties"), new PlayerAddictionProvider());
            }
        }
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(old -> {
            event.getEntity().getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(new_ -> {
                new_.from(old);
            });
        });
        event.getOriginal().invalidateCaps();
    }

    static class PlayerAddictionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        private PlayerAddiction capability;
        private final LazyOptional<PlayerAddiction> optional = LazyOptional.of(this::getOrInit);

        private PlayerAddiction getOrInit() {
            if (capability == null) {
                capability = new PlayerAddiction();
            }
            return capability;
        }

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == CBBCapabilities.PLAYER_ADDICTION) {
                return optional.cast();
            }

            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbt = new CompoundTag();
            getOrInit().serializeNBT(nbt);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            getOrInit().deserializeNBT(nbt);
        }
    }
}
