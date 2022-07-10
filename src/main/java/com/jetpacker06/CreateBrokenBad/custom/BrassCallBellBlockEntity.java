package com.jetpacker06.CreateBrokenBad.custom;

import com.jetpacker06.CreateBrokenBad.register.AllBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrassCallBellBlockEntity extends BlockEntity {
    private int ticksRemaining = 5;
    public BrassCallBellBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.BRASS_CALL_BELL.get(), pWorldPosition, pBlockState);
    }
    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, BrassCallBellBlockEntity pBlockEntity) {
        if (pState.getValue(BrassCallBellBlock.DOWN)) {
            if (pBlockEntity.ticksRemaining > 0) {
                pBlockEntity.ticksRemaining--;
            } else {
                pLevel.setBlock(pPos, pState.setValue(BrassCallBellBlock.DOWN, false), 3);
                pBlockEntity.ticksRemaining = 5;
            }
        }
    }
}