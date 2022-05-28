package tk.beanfeed.nightlightdim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLPortalBlock extends Block {
    public NLPortalBlock(Settings settings) {
        super(settings);
    }
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    private Vec3d BlockPosToVec3D(BlockPos pos){
        return new Vec3d(pos.getX(), pos.getY(), pos.getZ());

    }
    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld && !entity.hasVehicle() && !entity.hasPassengers() && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getOutlineShape(world, pos), BooleanBiFunction.AND)) {
            RegistryKey<World> registryKey = world.getRegistryKey() == NightLightDim.NIGHTLIGHT ? World.OVERWORLD : NightLightDim.NIGHTLIGHT;
            System.out.println("Portal to " + registryKey);
            ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);
            System.out.println("Portal to " + serverWorld);

            if (serverWorld == null) {
                return;
            }
            Vec3d spawnPos = new Vec3d(BlockPosToVec3D(pos).getX() + 1, 100, BlockPosToVec3D(pos).getZ() + 1);

            if(entity instanceof LivingEntity le){
                le.fallDistance = -20;
            }


            FabricDimensions.teleport(entity, serverWorld, new TeleportTarget(spawnPos, new Vec3d(0.0D, 5.0D, 0.0D), 0.0F, 0.0F));

        }
    }

}
