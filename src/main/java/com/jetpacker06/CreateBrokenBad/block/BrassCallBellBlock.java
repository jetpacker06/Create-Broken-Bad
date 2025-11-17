package com.jetpacker06.CreateBrokenBad.block;

import com.jetpacker06.CreateBrokenBad.register.AllSoundEvents;
import com.jetpacker06.CreateBrokenBad.register.CBBAdvancements;
import com.jetpacker06.CreateBrokenBad.register.CBBBlockEntityTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BrassCallBellBlock extends BaseEntityBlock {
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public static final MapCodec<BrassCallBellBlock> CODEC = simpleCodec(BrassCallBellBlock::new);

    public BrassCallBellBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DOWN, false));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(6, 1, 6, 10, 3, 10),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 3, 7, 9, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(DOWN, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DOWN);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hit) {
        if (player instanceof ServerPlayer serverPlayer) {
            CBBAdvancements.DING.trigger(serverPlayer);
        }
        state = state.setValue(DOWN, true);
        level.setBlock(pos, state, 3);
        level.playSound(player, pos, AllSoundEvents.BRASS_CALL_BELL_DING.get(), SoundSource.BLOCKS, 2f, 1f);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BrassCallBellBlockEntity(CBBBlockEntityTypes.BRASS_CALL_BELL.get(), pos, state);
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                         @NotNull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type) {
        return createTickerHelper(type, CBBBlockEntityTypes.BRASS_CALL_BELL.get(), BrassCallBellBlockEntity::tick);
    }

    public static class Trapped extends BrassCallBellBlock {
        public Trapped(Properties properties) {
            super(properties);
        }

        @Override
        public int getSignal(@NotNull BlockState state, @NotNull BlockGetter level,
                             @NotNull BlockPos pos, @NotNull Direction side) {
            return state.getValue(DOWN) ? 15 : 0;
        }
    }
}
