package tk.beanfeed.nightlightdim.items.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;

public class RevivingRodMat implements ToolMaterial {
    public static final RevivingRodMat INSTANCE = new RevivingRodMat();
    @Override
    public int getDurability() {
        return 500;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return 1.0F;
    }
    @Override
    public float getAttackDamage() {
        return 1.0F;
    }
    @Override
    public int getMiningLevel() {
        return 0;
    }
    @Override
    public int getEnchantability() {
        return 0;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(NLDBlockRegister.EXTRA_DEAD_BUSH);
    }
}
