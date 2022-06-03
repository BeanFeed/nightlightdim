package tk.beanfeed.nightlightdim.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import tk.beanfeed.nightlightdim.items.NLDItemRegister;

public class SoulfulWeed extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
    };
    public SoulfulWeed(Settings settings) {
        super(settings);
    }
    @Override
    public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND);
    }
    @Override
    public ItemConvertible getSeedsItem() {
        return NLDItemRegister.SOUL_BERRIES;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[(Integer)state.get(this.getAgeProperty())];
    }

    @Override
    public int getMaxAge() {
        return 3;
    }
}
