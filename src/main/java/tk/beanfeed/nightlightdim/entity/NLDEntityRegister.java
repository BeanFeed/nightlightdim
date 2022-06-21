package tk.beanfeed.nightlightdim.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.entity.custom.ReaperEntity;


public class NLDEntityRegister {
    public static final EntityType<ReaperEntity> REAPER = Registry.register(Registry.ENTITY_TYPE, new Identifier(NightLightDim.MOD_ID, "reaper"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ReaperEntity::new).dimensions(EntityDimensions.fixed(0.8f,2.4f)).build());


    public static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(REAPER, ReaperEntity.setAttributes());
    }
}
