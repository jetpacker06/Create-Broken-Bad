package com.jetpacker06.CreateBrokenBad.custom;

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

public class TrayBlock extends Block {
    public TrayBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape shape = Stream.of(
            Block.box(0, 1, 2, 16, 2, 3),
            Block.box(0, 1, 3, 1, 2, 13),
            Block.box(0, 1, 13, 16, 2, 14),
            Block.box(15, 1, 3, 16, 2, 13),
            Block.box(1, 0, 3, 15, 1.5, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape rotatedShape = Stream.of(
            Block.box(3, 0, 1, 13, 1.5, 15),
            Block.box(3, 1, 0, 13, 2, 1),
            Block.box(3, 1, 15, 13, 2, 16),
            Block.box(13, 1, 0, 14, 2, 16),
            Block.box(2, 1, 0, 3, 2, 16)
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