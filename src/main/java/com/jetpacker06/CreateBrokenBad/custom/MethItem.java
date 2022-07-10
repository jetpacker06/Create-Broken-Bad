package com.jetpacker06.CreateBrokenBad.custom;

import com.jetpacker06.CreateBrokenBad.register.AllBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class MethItem extends Item {
    public MethItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Block clickedBlock = pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock();
        Block newBlock = ((pContext.getItemInHand().getItem() instanceof MethItem.Blue) ? AllBlocks.BLUE_METH_TRAY : AllBlocks.WHITE_METH_TRAY).get();
        if (clickedBlock instanceof TrayBlock.Empty) {
            Direction direction = pContext.getLevel().getBlockState(pContext.getClickedPos()).getValue(TrayBlock.FACING);
            pContext.getLevel().setBlock(
                pContext.getClickedPos(),
                newBlock.defaultBlockState().setValue(TrayBlock.FACING, direction),
                3
            );
            pContext.getItemInHand().shrink(1);
        }
        return InteractionResult.CONSUME;
    }
    public static class Blue extends MethItem {

        public Blue(Properties pProperties) {
            super(pProperties);
        }
    }
    public static class White extends MethItem {

        public White(Properties pProperties) {
            super(pProperties);
        }
    }
}
