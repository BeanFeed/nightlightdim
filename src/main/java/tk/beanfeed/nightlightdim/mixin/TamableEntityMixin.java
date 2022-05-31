package tk.beanfeed.nightlightdim.mixin;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.beanfeed.nightlightdim.Interfaces.TameableEntityExt;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;
import tk.beanfeed.nightlightdim.items.NLDItemRegister;
import tk.beanfeed.nightlightdim.tool.NLDToolRegister;

import java.util.UUID;

@Mixin(TameableEntity.class)
public abstract class TamableEntityMixin extends AnimalEntity implements TameableEntityExt {
    boolean isRevived = false;

    protected TamableEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean isTamed();

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/passive/TameableEntity;writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", cancellable = true)
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("isRevived", isRevived);
    }
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/passive/TameableEntity;onDeath(Lnet/minecraft/entity/damage/DamageSource;)V", cancellable = true)
    public void onDeath(DamageSource source, CallbackInfo ci) {
        TameableEntity pet = (TameableEntity) (Object) this;
        ServerWorld petWorld = (ServerWorld) pet.getWorld();

        LivingEntity Owner = ((TameableEntity)(Object)this).getOwner();
        if(Owner instanceof ServerPlayerEntity player && player.getMainHandStack() != NLDToolRegister.REVIVING_ROD.getDefaultStack() && petWorld.getRegistryKey() != NightLightDim.NIGHTLIGHT && !this.isRevived()) {
            Revive(pet, player);
        }
        ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/passive/TameableEntity;readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", cancellable = true)
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        isRevived = nbt.getBoolean("isRevived");

    }
    public void tick(){
        if(!this.world.isClient && this.isAlive() && !this.isAiDisabled()){
            RegistryKey<World> world = this.getWorld().getRegistryKey();
            if(world != NightLightDim.NIGHTLIGHT && this.isRevived()){
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 1, 10));
            }
        }
        super.tick();
    }

    private void Revive(TameableEntity pet, ServerPlayerEntity player){
        pet.setHealth(pet.getMaxHealth());
        isRevived = true;
        ServerWorld world = (ServerWorld) pet.getWorld();
        Vec3d petPos = pet.getPos();
        ServerWorld serverWorld = world.getServer().getWorld(NightLightDim.NIGHTLIGHT);
        if (serverWorld == null) {
            return;
        }
        double yPos = getSurfaceY(serverWorld, pet.getBlockPos());
        Vec3d spawnPos = new Vec3d(pet.getX(), yPos + 1, pet.getZ());
        player.sendMessage(Text.of("Reviving At§a [" + (int)Math.round(spawnPos.x) + ", ~, " + (int)Math.round(spawnPos.z) + "]"), false);
        FabricDimensions.teleport(pet, serverWorld, new TeleportTarget(spawnPos, new Vec3d(0.0D, 0.0D, 0.0D), 0.0F, 0.0F));

        LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
        lightningEntity.refreshPositionAfterTeleport(petPos);
        world.spawnEntity(lightningEntity);
    }
    private double getSurfaceY(ServerWorld serverWorld, BlockPos pos){
        double yPos = 0;
        for(int i = 319; i > -64; i--){
            BlockState block = serverWorld.getBlockState(new BlockPos(pos.getX(), i, pos.getZ()));
            if(block.getBlock() != Blocks.AIR){
                yPos = i;
                return yPos;
            }

        }
        return 100.0;
    }
    public boolean isRevived(){return isRevived;}
}
