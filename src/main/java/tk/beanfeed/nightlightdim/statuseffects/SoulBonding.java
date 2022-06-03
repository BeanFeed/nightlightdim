package tk.beanfeed.nightlightdim.statuseffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SoulBonding extends StatusEffect {
    protected SoulBonding(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        /*
        if (entity instanceof TameableEntity Te && ((TameableEntityExt)Te).isRevived()) {

        }
        */
    }
}
