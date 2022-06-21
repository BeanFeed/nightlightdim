package tk.beanfeed.nightlightdim;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;
import tk.beanfeed.nightlightdim.entity.NLDEntityRegister;
import tk.beanfeed.nightlightdim.entity.client.ReaperRenderer;

public class NightLightDimClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(NLDBlockRegister.EXTRA_DEAD_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(NLDBlockRegister.DEAD_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NLDBlockRegister.SOUL_WEED);
        BlockRenderLayerMap.INSTANCE.putBlock(NLDBlockRegister.DEAD_SAPLING, RenderLayer.getCutout());
        EntityRendererRegistry.register(NLDEntityRegister.REAPER, ReaperRenderer::new);
    }
}
