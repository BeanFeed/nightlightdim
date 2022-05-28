package tk.beanfeed.nightlightdim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class NLPortalInitiator extends Block {
    public NLPortalInitiator(Settings settings) {
        super(settings);
    }
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.D, 16.0D);

    public void ignite(World world, BlockPos pos){
        /*
        Vec3d playerOldPos = player.getPos();
        Vec3d playerNewPos = new Vec3d(playerOldPos.x, playerOldPos.y + 5, playerOldPos.z);
        player.setPosition(playerNewPos);
         */

        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), NLDBlockRegister.PORTAL.getDefaultState());
        if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()+1), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()+1, pos.getY()+1, pos.getZ()-1), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()-1, pos.getY()+1, pos.getZ()), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()-1, pos.getY()+1, pos.getZ()+1), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX()-1, pos.getY()+1, pos.getZ()-1), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()-1), NLDBlockRegister.PORTAL.getDefaultState());
        }
        if(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)).getBlock() == NLDBlockRegister.PORTAL_INITIATOR){
            world.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()+1), NLDBlockRegister.PORTAL.getDefaultState());
        }
    }
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }
    private Vec3d BlockPosToVec3D(BlockPos pos){
        return new Vec3d(pos.getX(), pos.getY(), pos.getZ());

    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity){
        if(world instanceof ServerWorld && !entity.hasVehicle() && !entity.hasPassengers() && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getOutlineShape(world, pos), BooleanBiFunction.AND)){
            RegistryKey<World> registryKey = world.getRegistryKey();
            if(registryKey == World.OVERWORLD){
                if(entity instanceof PlayerEntity){
                    entity.kill();
                    ignite(world, pos);
                }
            }else{
                ignite(world, pos);
            }

        }
    }

}
