package com.jetpacker06.CreateBrokenBad.custom;

import com.jetpacker06.CreateBrokenBad.register.AllBlockEntities;
import com.jetpacker06.CreateBrokenBad.register.AllCustomTriggerAdvancements;
import com.jetpacker06.CreateBrokenBad.register.AllSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TrappedBrassCallBellBlock extends BaseEntityBlock {
    public TrappedBrassCallBellBlock(Properties p_49795_) {
        super(p_49795_);
    }
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(DOWN) ? 15 : 0;
    }

    public static BooleanProperty DOWN = BooleanProperty.create("down");

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(DOWN)) {
            return downShape;
        } else {
            return upShape;
        }
    }

    private static final VoxelShape upShape = Stream.of(
            Block.box(7.5, 2.25, 7.5, 8.5, 4, 8.5),
            Block.box(7, 1, 7, 9, 3, 9),
            Block.box(6, 0, 6, 10, 1, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape downShape = Stream.of(
            Block.box(7.5, 2.25, 7.5, 8.5, 3.25, 8.5),
            Block.box(7, 1, 7, 9, 3, 9),
            Block.box(6, 0, 6, 10, 1, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(DOWN, false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DOWN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer instanceof ServerPlayer) {
            AllCustomTriggerAdvancements.DING.trigger((ServerPlayer) pPlayer);
        }
        pState = pState.setValue(DOWN, true);
        pLevel.setBlock(pPos, pState, 3);
        pLevel.playSound(pPlayer,pPos, AllSoundEvents.BRASS_CALL_BELL_DING.get(), SoundSource.BLOCKS, 2f, 1f);
       // this.isSignalSource
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        pLevel.removeBlockEntity(pPos);
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TrappedBrassCallBellBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, AllBlockEntities.TRAPPED_BRASS_CALL_BELL.get(), pLevel.isClientSide ? TrappedBrassCallBellBlockEntity::clientTick : null);
    }
}
