package com.jetpacker06.CreateBrokenBad.custom;

import com.jetpacker06.CreateBrokenBad.register.AllBlockEntities;
import com.jetpacker06.CreateBrokenBad.register.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TrappedBrassCallBellBlockEntity extends BlockEntity {
    private int ticksRemaining = 5;
    public TrappedBrassCallBellBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.TRAPPED_BRASS_CALL_BELL.get(), pWorldPosition, pBlockState);
    }
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, TrappedBrassCallBellBlockEntity pBlockEntity) {
        if (pState.getValue(BrassCallBellBlock.DOWN)) {
            if (pBlockEntity.ticksRemaining > 0) {
                pBlockEntity.ticksRemaining--;
            } else {
                pLevel.setBlock(pPos, pState.setValue(BrassCallBellBlock.DOWN, false), 3);
                pBlockEntity.ticksRemaining = 5;
            }
        }
        if (pLevel instanceof ServerLevel) {
            if (pLevel.getBlockState(pPos).getValue(TrappedBrassCallBellBlock.DOWN)) {
                pLevel.setBlock(pPos, AllBlocks.TRAPPED_BRASS_CALL_BELL.get().defaultBlockState(), 3);
                pLevel.updateNeighbourForOutputSignal(pPos, AllBlocks.TRAPPED_BRASS_CALL_BELL.get());
            }
        }
    }
}