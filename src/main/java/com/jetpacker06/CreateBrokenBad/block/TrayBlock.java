package com.jetpacker06.CreateBrokenBad.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public abstract class TrayBlock extends HorizontalDirectionalBlock {
    public TrayBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape shape = Stream.of(
            Block.box(2, 0, 12, 14, 1.4, 13),
            Block.box(1, -0.1, 3, 15, 0, 13),
            Block.box(1, 2.7755575615628914e-17, 3, 15, 0.1, 13),
            Block.box(14, 0, 3, 15, 1.4, 13),
            Block.box(1, 0, 3, 2, 1.4, 13),
            Block.box(2, 0, 3, 14, 1.4, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape rotatedShape = Stream.of(
            Block.box(3, 0, 2, 4, 1.4, 14),
            Block.box(3, -0.1, 1, 13, 0, 15),
            Block.box(3, 2.7755575615628914e-17, 1, 13, 0.1, 15),
            Block.box(3, 0, 14, 13, 1.4, 15),
            Block.box(3, 0, 1, 13, 1.4, 2),
            Block.box(12, 0, 2, 13, 1.4, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(FACING) == Direction.NORTH || pState.getValue(FACING) == Direction.SOUTH ? shape : rotatedShape;
    }
    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return super.rotate(state, world, pos, direction);
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(FACING) == Direction.NORTH || pState.getValue(FACING) == Direction.SOUTH ? shape : rotatedShape;
    }
    public static class White extends TrayBlock {
        public White(Properties p_49795_) {
            super(p_49795_);
        }
    }
    public static class Empty extends TrayBlock {
        public Empty(Properties p_49795_) {
            super(p_49795_);
        }
    }
    public static class Blue extends TrayBlock {
        public Blue(Properties p_49795_) {
            super(p_49795_);
        }
    }
}