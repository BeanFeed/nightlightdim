package tk.beanfeed.nightlightdim.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import tk.beanfeed.nightlightdim.statuseffects.NLDStatusEffectRegister;

public class NLDFoodComponents {
    public static final FoodComponent SOUL_BERRY = new FoodComponent.Builder().hunger(4).saturationModifier(0.5F).statusEffect(new StatusEffectInstance(NLDStatusEffectRegister.SOUL_BOND, 2400, 255), 100.0f).build();
}
