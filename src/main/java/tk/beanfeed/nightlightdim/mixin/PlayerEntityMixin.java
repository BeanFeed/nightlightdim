package tk.beanfeed.nightlightdim.mixin;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.beanfeed.nightlightdim.Interfaces.PlayerEntityMixinExt;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.items.armor.NLDArmorRegister;
import tk.beanfeed.nightlightdim.items.tool.NLDToolRegister;
import tk.beanfeed.nightlightdim.statuseffects.NLDStatusEffectRegister;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityMixinExt {
    boolean isRevived = false;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/player/PlayerEntity;writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", cancellable = true)
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("isRevived", isRevived);
    }
    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/player/PlayerEntity;readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", cancellable = true)
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {isRevived = nbt.getBoolean("isRevived");}

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerEntity;onDeath(Lnet/minecraft/entity/damage/DamageSource;)V", cancellable = true)
    public void onDeath(DamageSource source, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        boolean isWearingDamnedArmor = getWearingDamnedArmor(player);
        if(source.getAttacker() instanceof PlayerEntity Pe && Pe.getMainHandStack().isOf(NLDToolRegister.SOUL_SWORD) && !isWearingDamnedArmor) {
            Revive((ServerPlayerEntity) player);
        }
        ci.cancel();
    }

    private boolean getWearingDamnedArmor(PlayerEntity player) {
        if(player.getEquippedStack(EquipmentSlot.HEAD).isOf(NLDArmorRegister.DAMNED_HELMET)){ return true; }
        if(player.getEquippedStack(EquipmentSlot.CHEST).isOf(NLDArmorRegister.DAMNED_CHESTPLATE)){ return true; }
        if(player.getEquippedStack(EquipmentSlot.LEGS).isOf(NLDArmorRegister.DAMNED_LEGS)){ return true; }
        if(player.getEquippedStack(EquipmentSlot.FEET).isOf(NLDArmorRegister.DAMNED_BOOTS)){ return true; }
        return false;
    }


    private void Revive(ServerPlayerEntity player){
        player.setHealth(player.getMaxHealth());
        isRevived = true;
        ServerWorld world = player.getWorld();
        Vec3d petPos = player.getPos();
        ServerWorld serverWorld = world.getServer().getWorld(NightLightDim.NIGHTLIGHT);
        if (serverWorld == null) {
            return;
        }
        double yPos = getSurfaceY(serverWorld, player.getBlockPos());
        Vec3d spawnPos = new Vec3d(player.getX(), yPos + 1, player.getZ());
        player.sendMessage(Text.of("Reviving At§a [" + (int)Math.round(spawnPos.x) + ", ~, " + (int)Math.round(spawnPos.z) + "]"), false);
        player.fallDistance = 0.0f;
        FabricDimensions.teleport(player, serverWorld, new TeleportTarget(spawnPos, new Vec3d(0.0D, 0.0D, 0.0D), 0.0F, 0.0F));

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
    private void Cure(){this.isRevived = false;}

    public boolean isRevived(){return this.isRevived;}
    @Inject(at= @At("HEAD"), method = "Lnet/minecraft/entity/player/PlayerEntity;tick()V")
    public void tick(CallbackInfo ci){
        if(!this.world.isClient && this.isAlive()){
            RegistryKey<World> world = this.getWorld().getRegistryKey();
            if(world != NightLightDim.NIGHTLIGHT && this.isRevived() && !this.hasStatusEffect(NLDStatusEffectRegister.SOUL_BOND)){
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 1, 10));
            }
            if(this.isRevived() && this.hasStatusEffect(NLDStatusEffectRegister.SOUL_BOND) && this.hasStatusEffect(StatusEffects.REGENERATION)){
                this.Cure();
                this.removeStatusEffect(NLDStatusEffectRegister.SOUL_BOND);
            }
        }
    }
}
