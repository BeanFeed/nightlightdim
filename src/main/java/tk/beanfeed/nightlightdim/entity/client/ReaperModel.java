package tk.beanfeed.nightlightdim.entity.client;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.entity.custom.ReaperEntity;

public class ReaperModel extends AnimatedGeoModel<ReaperEntity> {
    @Override
    public Identifier getModelLocation(ReaperEntity object) {
        return new Identifier(NightLightDim.MOD_ID, "geo/reaper_model.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ReaperEntity object) {
        return new Identifier(NightLightDim.MOD_ID, "textures/entity/reaper.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ReaperEntity animatable) {
        return new Identifier(NightLightDim.MOD_ID, "animations/reaper.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(ReaperEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("bone9");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
