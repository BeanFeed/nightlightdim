package tk.beanfeed.nightlightdim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.entity.custom.ReaperEntity;

public class ReaperRenderer extends GeoEntityRenderer<ReaperEntity> {
    public ReaperRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ReaperModel());
    }

    @Override
    public Identifier getTextureLocation(ReaperEntity instance){
        return new Identifier(NightLightDim.MOD_ID, "textures/entity/reaper.png");
    }
}
