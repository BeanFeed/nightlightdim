package tk.beanfeed.nightlightdim.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import tk.beanfeed.nightlightdim.entity.goals.ReaperAttackGoal;
import tk.beanfeed.nightlightdim.sounds.NLDSoundRegister;

public class ReaperEntity extends HostileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public ReaperEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        AnimationBuilder walkingBuild = new AnimationBuilder();
        walkingBuild.addAnimation("animation.reaper.idle",true);
        event.getController().setAnimation(walkingBuild);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }



    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.75f, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, false));
        this.targetSelector.add(3, new ReaperAttackGoal(this, 1.3f, true ));
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 120.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }
    @Override
    protected SoundEvent getAmbientSound(){return NLDSoundRegister.REAPER_AMBIENT;}
    @Override
    protected SoundEvent getHurtSound(DamageSource source){return SoundEvents.ENTITY_PHANTOM_HURT;}
    @Override
    protected SoundEvent getDeathSound(){return NLDSoundRegister.REAPER_GROAN;}

}
