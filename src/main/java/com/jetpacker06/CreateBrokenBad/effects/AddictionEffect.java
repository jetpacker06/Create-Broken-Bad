package com.jetpacker06.CreateBrokenBad.effects;

import com.jetpacker06.CreateBrokenBad.capabilities.PlayerAddiction;
import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import com.jetpacker06.CreateBrokenBad.register.CBBEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddictionEffect extends MobEffect {

    private enum AddictionStage {
        OVERDOSE,
        HIGH,
        GRACE,
        WITHDRAWAL,
        DEATH
    }

    public AddictionEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide()) {
            pLivingEntity.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                float withdrawal = addiction.withdrawal;

                if (addiction.toxicity >= 0.9f) {
                    applyEffects(pLivingEntity, AddictionStage.OVERDOSE, 0);
                    addiction.toxicity = 1;
                } else if (withdrawal < 0.0f) {
                    final float numStages = 4.0f;
                    int amplifier = Math.min((int) ((-withdrawal) / (10.0f / numStages)), (int) (numStages - 1.0f));
                    int debuff = (int) (addiction.tolerance * 3);
                    applyEffects(pLivingEntity, AddictionStage.HIGH, Math.max(0, amplifier - debuff));
                } else if (withdrawal < 1.0f) {
                    applyEffects(pLivingEntity, AddictionStage.GRACE, 0);
                } else if (withdrawal < 9.0f) {
                    final float numStages = 4.0f;
                    int amplifier = Math.min((int) ((withdrawal - 1) / (8.0f / numStages)), (int) (numStages - 1.0f));
                    applyEffects(pLivingEntity, AddictionStage.WITHDRAWAL, amplifier);
                } else {
                    applyEffects(pLivingEntity, AddictionStage.DEATH, 0);
                }
            });
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    private void applyEffectTo(LivingEntity entity, MobEffect effect, int amplifier) {
        MobEffectInstance effectInstance = entity.getEffect(effect);

        boolean hasEffect = entity.hasEffect(effect);
        int duration = effectInstance == null ? 0 : effectInstance.getDuration();
        boolean renewOpportunity = !hasEffect || duration < 20 * 15;

        if (renewOpportunity) {
            entity.addEffect(new MobEffectInstance(effect, 20 * 20, amplifier, false, false));
        }
    }

    private void applyEffects(LivingEntity e, AddictionStage stage, int amplifier) {
        switch (stage) {
            case DEATH, OVERDOSE -> {
                e.removeEffect(MobEffects.MOVEMENT_SPEED);
                e.removeEffect(MobEffects.REGENERATION);
                e.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                e.removeEffect(MobEffects.DIG_SPEED);
                e.removeEffect(MobEffects.FIRE_RESISTANCE);
                e.removeEffect(MobEffects.DAMAGE_BOOST);
                e.removeEffect(MobEffects.LUCK);

                applyEffectTo(e, MobEffects.MOVEMENT_SLOWDOWN, 5);
                applyEffectTo(e, MobEffects.DIG_SLOWDOWN, 5);
                applyEffectTo(e, MobEffects.WEAKNESS, 5);
                applyEffectTo(e, MobEffects.BLINDNESS, 5);
                applyEffectTo(e, MobEffects.CONFUSION, 5);

                applyEffectTo(e, MobEffects.WITHER, 50);
                applyEffectTo(e, MobEffects.POISON, 50);
            }
            case HIGH -> {
                e.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                e.removeEffect(MobEffects.WEAKNESS);
                e.removeEffect(MobEffects.BLINDNESS);
                e.removeEffect(MobEffects.CONFUSION);
                e.removeEffect(MobEffects.WITHER);
                e.removeEffect(MobEffects.POISON);
                e.removeEffect(MobEffects.DIG_SLOWDOWN);
                e.removeEffect(MobEffects.HUNGER);
                e.removeEffect(MobEffects.UNLUCK);
                e.removeEffect(MobEffects.BAD_OMEN);

                switch (amplifier) {
                    case 0 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SPEED, 0);
                        applyEffectTo(e, MobEffects.REGENERATION, 0);
                        applyEffectTo(e, MobEffects.DAMAGE_RESISTANCE, 0);
                        applyEffectTo(e, MobEffects.DIG_SPEED, 1);
                        applyEffectTo(e, MobEffects.LUCK, 0);
                    }
                    case 1 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SPEED, 1);
                        applyEffectTo(e, MobEffects.REGENERATION, 1);
                        applyEffectTo(e, MobEffects.DAMAGE_RESISTANCE, 2);
                        applyEffectTo(e, MobEffects.FIRE_RESISTANCE, 0);
                        applyEffectTo(e, MobEffects.DAMAGE_BOOST, 5);
                        applyEffectTo(e, MobEffects.DIG_SPEED, 1);
                        applyEffectTo(e, MobEffects.LUCK, 3);
                    }
                    case 2 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SPEED, 2);
                        applyEffectTo(e, MobEffects.REGENERATION, 2);
                        applyEffectTo(e, MobEffects.DAMAGE_RESISTANCE, 3);
                        applyEffectTo(e, MobEffects.FIRE_RESISTANCE, 0);
                        applyEffectTo(e, MobEffects.DAMAGE_BOOST, 10);
                        applyEffectTo(e, MobEffects.DIG_SPEED, 2);
                        applyEffectTo(e, MobEffects.LUCK, 6);
                    }
                    case 3 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SPEED, 2);
                        applyEffectTo(e, MobEffects.REGENERATION, 3);
                        applyEffectTo(e, MobEffects.DAMAGE_RESISTANCE, 4);
                        applyEffectTo(e, MobEffects.FIRE_RESISTANCE, 0);
                        applyEffectTo(e, MobEffects.DAMAGE_BOOST, 15);
                        applyEffectTo(e, MobEffects.DIG_SPEED, 3);
                        applyEffectTo(e, MobEffects.LUCK, 9);
                    }
                }
            }
            case GRACE -> {

            }
            case WITHDRAWAL -> {
                e.removeEffect(MobEffects.MOVEMENT_SPEED);
                e.removeEffect(MobEffects.REGENERATION);
                e.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                e.removeEffect(MobEffects.DIG_SPEED);
                e.removeEffect(MobEffects.FIRE_RESISTANCE);
                e.removeEffect(MobEffects.DAMAGE_BOOST);
                e.removeEffect(MobEffects.LUCK);

                switch (amplifier) {
                    case 0 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SLOWDOWN, 0);
                        applyEffectTo(e, MobEffects.WEAKNESS, 0);
                    }
                    case 1 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SLOWDOWN, 1);
                        applyEffectTo(e, MobEffects.WEAKNESS, 2);
                        applyEffectTo(e, MobEffects.DIG_SLOWDOWN, 1);
                    }
                    case 2 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SLOWDOWN, 2);
                        applyEffectTo(e, MobEffects.WEAKNESS, 3);
                        applyEffectTo(e, MobEffects.DIG_SLOWDOWN, 2);
                        applyEffectTo(e, MobEffects.BLINDNESS, 0);
                    }
                    case 3 -> {
                        applyEffectTo(e, MobEffects.MOVEMENT_SLOWDOWN, 3);
                        applyEffectTo(e, MobEffects.WEAKNESS, 4);
                        applyEffectTo(e, MobEffects.DIG_SLOWDOWN, 3);
                        applyEffectTo(e, MobEffects.BLINDNESS, 0);
                        applyEffectTo(e, MobEffects.WITHER, 0);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}