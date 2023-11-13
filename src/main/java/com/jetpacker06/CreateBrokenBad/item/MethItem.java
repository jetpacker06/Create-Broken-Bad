package com.jetpacker06.CreateBrokenBad.item;

import com.jetpacker06.CreateBrokenBad.block.TrayBlock;
import com.jetpacker06.CreateBrokenBad.register.CBBBlocks;
import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public abstract class MethItem extends Item {
    public MethItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public abstract float getAddictionRate();
    public abstract float getWithdrawalReduction();
    public abstract float getToleranceBuildupRate();

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Block clickedBlock = pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock();
        Block newBlock = ((pContext.getItemInHand().getItem() instanceof MethItem.Blue) ? CBBBlocks.BLUE_METH_TRAY : CBBBlocks.WHITE_METH_TRAY).get();
        if (clickedBlock instanceof TrayBlock.Empty) {
            Direction direction = pContext.getLevel().getBlockState(pContext.getClickedPos()).getValue(TrayBlock.FACING);
            pContext.getLevel().setBlock(
                pContext.getClickedPos(),
                newBlock.defaultBlockState().setValue(TrayBlock.FACING, direction),
                3
            );
            pContext.getLevel().playSound(pContext.getPlayer(),pContext.getClickedPos(), SoundEvents.SAND_HIT, SoundSource.BLOCKS, 2f, 1f);
            pContext.getItemInHand().shrink(1);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @SubscribeEvent
    public static void methEat(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getItem().getItem() instanceof MethItem meth)) {
            return;

        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
            addiction.consumptionTicks = 0;
            addiction.addiction = Math.min(1.0f, addiction.addiction + meth.getAddictionRate());
            addiction.withdrawal = Math.max(-10.0f, Math.min(0.0f, addiction.withdrawal) - meth.getWithdrawalReduction());
            addiction.tolerance = Math.min(1.0f, addiction.tolerance + meth.getToleranceBuildupRate());
            addiction.toxicity = Math.min(1.0f, addiction.toxicity + 0.15f);
        });
    }

    public static class Blue extends MethItem {
        public Blue(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public float getAddictionRate() {
            return 0.08f;
        }

        @Override
        public float getWithdrawalReduction() {
            return 3.0f;
        }

        @Override
        public float getToleranceBuildupRate() {
            return 0.05f;
        }
    }
    public static class White extends MethItem {
        public White(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public float getAddictionRate() {
            return 0.06f;
        }

        @Override
        public float getWithdrawalReduction() {
            return 2.0f;
        }

        @Override
        public float getToleranceBuildupRate() {
            return 0.05f;
        }
    }
}
