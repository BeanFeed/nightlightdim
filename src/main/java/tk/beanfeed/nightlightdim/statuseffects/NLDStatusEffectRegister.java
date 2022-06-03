package tk.beanfeed.nightlightdim.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLDStatusEffectRegister {
    public static final StatusEffect SOUL_BOND = new SoulBonding(StatusEffectCategory.BENEFICIAL, 0);

    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(NightLightDim.MOD_ID, "soul_bonding"), SOUL_BOND);
    }
}
