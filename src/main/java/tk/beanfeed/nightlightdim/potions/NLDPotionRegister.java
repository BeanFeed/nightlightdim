package tk.beanfeed.nightlightdim.potions;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.items.NLDItemRegister;
import tk.beanfeed.nightlightdim.mixin.BrewingRecipeRegistryMixin;
import tk.beanfeed.nightlightdim.statuseffects.NLDStatusEffectRegister;

public class NLDPotionRegister {
    public static Potion SOUL_BONDING;
    public static void register(){
        SOUL_BONDING = Registry.register(Registry.POTION, new Identifier(NightLightDim.MOD_ID, "soul_bonding"), new Potion(new StatusEffectInstance(NLDStatusEffectRegister.SOUL_BOND, 12000, 0)));
        registerPotionRecipes();
    }

    private static void registerPotionRecipes(){
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.MUNDANE, NLDItemRegister.SOUL_BERRIES, SOUL_BONDING);
    }
}
