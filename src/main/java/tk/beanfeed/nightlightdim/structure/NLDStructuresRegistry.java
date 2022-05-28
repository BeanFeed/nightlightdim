package tk.beanfeed.nightlightdim.structure;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.mixin.StructureFeatureAccessor;

public class NLDStructuresRegistry {
    public static StructureFeature<?> NL_WELL = new NL_Well();

    public static void registerStructures() {
        StructureFeatureAccessor.callRegister(NightLightDim.MOD_ID + ":nl_well", NL_WELL, GenerationStep.Feature.SURFACE_STRUCTURES);
    }
}
