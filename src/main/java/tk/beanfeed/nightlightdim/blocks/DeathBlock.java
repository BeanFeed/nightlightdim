package tk.beanfeed.nightlightdim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tk.beanfeed.nightlightdim.NightLightDim;

public class DeathBlock extends Block {
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.9D, 16.0D);
    public DeathBlock(Settings settings) {
        super(settings);
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

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity){
        if(!world.isClient){
            if (entity instanceof LivingEntity && !(entity instanceof WitherSkeletonEntity)) {
                ItemStack inv = ((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET);
                if (!inv.isOf(Items.NETHERITE_BOOTS)) {
                    if(!(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())){
                        ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 1, 10));
                    }

                }
            }
        }
    }
}
